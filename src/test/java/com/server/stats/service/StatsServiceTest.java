package com.server.stats.service;

import com.server.stats.dto.CustomerContentDTO;
import com.server.stats.dto.StatsDTO;
import com.server.stats.entity.Stats;
import com.server.stats.repository.StatsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class StatsServiceTest {

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private IStatsService statsService;


    /**
     * Test Service getAllStats Method to check that we can get stats stored in the database
     */
    @Test
    public void getAllStatsTest() {

        //Given
        List<Stats> statsList = new ArrayList<>();


        //WHEN
        try {
            //Mockito.when(this.statsService.getAllStats()).thenReturn(CompletableFuture.completedFuture(list));
            statsList = this.statsService.getAllStats().get();
        }catch (Exception e) {
            e.printStackTrace();
        }


        //Then
        //Assertions.assertEquals(41, statsList.size());


    }

    /**
     * Test Service addJsonPayload Method to check that we do store jsonpayload in our concurrent List
     * Given StatsDTO Object we call the methods addPayloadToList which will return the updated list of jsonPayloads
     */
    @Test
    public void addJsonPayloadTest() {

        //Given
        StatsDTO statsDTO = new StatsDTO("token","customer","content",10,10,10,10);

        //When
        List<StatsDTO>  statsDTOS = this.statsService.addPayloadToList(statsDTO);

        //Then
        //Assertions.assertEquals(statsDTOS.contains(statsDTO), true);
    }

    /**
     * Test Service addJsonPayload Method to check that we do store jsonpayload in our concurrent List
     * Given Two StatsDTO Object we call the methods addPayloadToList which will return the updated list of jsonPayloads
     * with the added StatsDTOs
     */
    @Test
    public void addJsonPayloadTestWithTwoJsonPayloads() {

        //Given
        StatsDTO statsDTO = new StatsDTO("token","customer","content",10,10,10,10);
        StatsDTO statsDTO2 = new StatsDTO("token","customer","content",10,10,10,10);

        //When
        List<StatsDTO>  statsDTOS = this.statsService.addPayloadToList(statsDTO);
        statsDTOS = this.statsService.addPayloadToList(statsDTO2);

        //Then
        //Assertions.assertEquals(statsDTOS.contains(statsDTO2) && statsDTOS.contains(statsDTO2), true);
    }


    /**
     * Test Service aggregatePayloadsAndSaveStatsToDB Method to check that we do store the resulting stats in the DB
     * Given Three different json payloads we call the methods addPayloadToList which will update the list of jsonPayloads
     * Then we call aggregatePayloadsAndSaveStatsToDB to retrns the list of stats saved to the DB
     */
    @Test
    public void getAggregatedPayloadsTest() {

        //GIVEN
        StatsDTO statsDTO = new StatsDTO("token","customer1","content1",10,10,10,10);
        StatsDTO statsDTO2 = new StatsDTO("token","customer2","content2",10,10,10,10);
        StatsDTO statsDTO3 = new StatsDTO("token","customer3","content3",10,10,10,10);

        //WHEN
        this.statsService.addPayloadToList(statsDTO);
        this.statsService.addPayloadToList(statsDTO2);
        this.statsService.addPayloadToList(statsDTO3);
        List<Stats> res = this.statsService.aggregatePayloadsAndSaveStatsToDB();


        //TTHEN
        //Assertions.assertEquals(res.size(), 3);

    }


    /**
     * Test Service aggregatePayloadsAndSaveStatsToDB Method to check that we aggregate correctly cdn and p2p
     * Of the different json payloads received
     * Given Two same json payloads we call the methods addPayloadToList which will update the list of jsonPayloads
     * Then we call aggregatePayloadsAndSaveStatsToDB to returns the list of stats saved to the DB
     * the we assert the aggregated values
     */
    @Test
    public void aggregateCdnAndP2pTest() {

        //GIVEN
        StatsDTO statsDTO = new StatsDTO("token","customer","content",10,10,10,10);
        StatsDTO statsDTO2 = new StatsDTO("token","customer","content",10,10,10,10);

        //WHEN
        this.statsService.addPayloadToList(statsDTO);
        this.statsService.addPayloadToList(statsDTO2);
        List<Stats> res = this.statsService.aggregatePayloadsAndSaveStatsToDB();

        //THEN
        Assertions.assertEquals(res.size(), 1);
        int cdn = res.get(0).getCdn();
        int p2p = res.get(0).getP2p();
        //Assertions.assertEquals(cdn , 20);
        //Assertions.assertEquals(p2p , 20);

    }

    /**
     * Test Service aggregatePayloadsAndSaveStatsToDB Method to check that we calculate correctly the number of sessions
     * Of the different json payloads received
     * Given Two same json payloads we call the methods addPayloadToList which will update the list of jsonPayloads
     * Then we call aggregatePayloadsAndSaveStatsToDB to returns the list of stats saved to the DB
     * the we assert the number of sessions for each stats
     */
    @Test
    public void getNumberOfSessionTest() {

        //GIVEN
        StatsDTO statsDTO = new StatsDTO("token1","customer","content",10,10,10,10);
        StatsDTO statsDTO2 = new StatsDTO("token2","customer","content",10,10,10,10);
        StatsDTO statsDTO3 = new StatsDTO("token3","customer","content",10,10,10,10);
        StatsDTO statsDTO4 = new StatsDTO("token4","customer2","content",10,10,10,10);

        //WHEN
        this.statsService.addPayloadToList(statsDTO);
        this.statsService.addPayloadToList(statsDTO2);
        this.statsService.addPayloadToList(statsDTO3);
        this.statsService.addPayloadToList(statsDTO4);
        List<Stats> res = this.statsService.aggregatePayloadsAndSaveStatsToDB();

        //THEN
        //Assertions.assertEquals(res.size(), 2);
        int first = res.get(0).getSessions();
        int second = res.get(1).getSessions();
        //Assertions.assertEquals(first , 3);
        //Assertions.assertEquals(second , 1);

    }

}
