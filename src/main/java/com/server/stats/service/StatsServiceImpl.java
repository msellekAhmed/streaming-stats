package com.server.stats.service;

import com.server.stats.controller.StatsController;
import com.server.stats.dto.CustomerContentDTO;
import com.server.stats.dto.StatsDTO;
import com.server.stats.entity.Stats;
import com.server.stats.repository.StatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


/**
 * Service Interface Implementation Used to intercat with the controller Layer and the Repository Layer,
 * Provides different implementation of methods for aggregating and groupping json payloads received
 */
@Service
public class StatsServiceImpl implements IStatsService {

    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

    // Declare a thread safe CopyOnWriteArrayList ArrayList to put all incomming json payloads
    private List<StatsDTO> jsonPayloads = new CopyOnWriteArrayList();

    @Autowired
    private StatsRepository statsRepository;



    /**
     * Given a payload received by the server, we add it to our list of jsonPayloads
     * this list is used to store all payloads received during 5 minute interval for further processing
     * @param jsonPayload json payload received by the server
     * @return the this.jsonPayloads updated by adding the new payload
     */
    @Override
    public List<StatsDTO> addPayloadToList(StatsDTO jsonPayload) {

        logger.info("adding jsonPayload to the list of payloads :"+jsonPayload.toString());
        synchronized(this.jsonPayloads) {
            this.jsonPayloads.add(jsonPayload);
        }
        return this.jsonPayloads;

    }


    /**
     * Given a stats that was calculed after 5 minute interval, we store it to the database using the stats repository
     * @param aggStats stats object calculated after aggregation
     * @Async is used mainly for making the function asynchronous for performance
     * @return the stats that was saved to database
     */
    @Override
    @Async
    public CompletableFuture<Stats> saveStatsToDB(Stats aggStats) {
        logger.info("saving the following stats to the database :"+aggStats.toString());
        return CompletableFuture.completedFuture(this.statsRepository.save(aggStats));
    }

    /**
     * I Added this methods for the purpose of testing and debbuging
     * we store it to the database using the stats repository
     * @Async is used mainly for making the function asynchronous for performance
     * @return all stats that was stored in the database
     */
    @Override
    @Async
    public CompletableFuture<List<Stats>> getAllStats() {
        logger.info("returning all the stats in the database.");
        return CompletableFuture.completedFuture(this.statsRepository.findAll());
    }


    /**
     * Given a the list of payloads declared above, this function starts by aggregating the list of payload to
     * produce a Map<CustomerContentDTO, List<StatsDTO>> which denotes the list of payload for each pair of customer and content
     * Then for each CustomerContentDTO received during the 5 minute interval we create a stats
     * Which will be stored in the Database
     */
    @Override
    @Scheduled(fixedRate = 300000)
    public List<Stats> aggregatePayloadsAndSaveStatsToDB()  {
        logger.info("starting to aggregate json payloads");
        ConcurrentMap<CustomerContentDTO, List<StatsDTO>> grouppedPayload = getAggregatedPayloads(this.jsonPayloads);
        logger.info("starting to aggregate the stats of each customer and content");
        List<Stats> aggregatedStatsList = getStatsFromGrouppedPayloads(grouppedPayload);
        // We Empty our jsonPayload list because the 5 minute time has passed
        this.jsonPayloads =  new CopyOnWriteArrayList();
        logger.info("starting to save the stats of each customer and content in the database");
        if (aggregatedStatsList != null) {
            aggregatedStatsList.forEach(sl -> this.saveStatsToDB(sl));
        }
        return aggregatedStatsList;
    }



    private List<Stats> getStatsFromGrouppedPayloads(ConcurrentMap<CustomerContentDTO, List<StatsDTO>> grouppedPayload) {

        List<Stats> statsList = new ArrayList<>();
        logger.info("loop through the map of (CustomerContentDTO, List<StatsDTO>) to aggregate cdn and p2p for each customer and content");
        for ( CustomerContentDTO customerContentPair : grouppedPayload.keySet() ) {
                String content = customerContentPair.getContent();
                String customer = customerContentPair.getCustomer();
                int sumCDN = grouppedPayload.get(customerContentPair).stream().mapToInt(stats -> stats.getCdn()).sum();
                int sumP2P = grouppedPayload.get(customerContentPair).stream().mapToInt(stats -> stats.getP2p()).sum();
                // Implementation to the Question 2 -> since we have the list of statsDTO of each pair customer content we can get count the distinct token of each payload
                int nbrOfSession = (int) grouppedPayload.get(customerContentPair).stream().map( statsDto -> statsDto.getToken() ).distinct().count();
                Stats aggresgatedStats = new Stats( new Date(), customer, content, sumCDN, sumP2P, nbrOfSession);
                logger.info("adding the aggregated stats to list of stats to insert in the database :"+aggresgatedStats.toString());
                statsList.add(aggresgatedStats);
        }
        return statsList;
    }




    private ConcurrentMap<CustomerContentDTO,  List<StatsDTO>> getAggregatedPayloads(List<StatsDTO> jsonPayloads) {

        logger.info("create a groupped Map of ( CustomerContentDTP, List<StatsDTO>) from List of json paylods");
        ConcurrentMap<CustomerContentDTO, List<StatsDTO>> concurentContentStatsDTOQueueMAP = jsonPayloads.stream().collect(Collectors.groupingByConcurrent(sdto -> new CustomerContentDTO(sdto.getCustomer(), sdto.getContent())));
        return concurentContentStatsDTOQueueMAP;
    }



}
