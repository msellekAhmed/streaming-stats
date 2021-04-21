package com.server.stats.controller;


import com.server.stats.entity.Stats;
import com.server.stats.service.IStatsService;
import com.server.stats.dto.StatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Rest Controller that will receive different HTTP requests with bqse URL /server.
 * Exposes two endpoints :
 * GET /sever/stats -> to get all stats stored in the database (I added this endpoint for testing purpose)
 * POST /server/stats -> to psh a new jsonpayload to the server
 */
@RestController
@RequestMapping("/server")
public class StatsController {


    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

    @Autowired
    public IStatsService statsService;

    /**
     * Endpoint to get all stats stored in the database (I added this endpoint for testing purpose)
     * @return List</Stats>
     */
    @GetMapping("/stats")
    public List<Stats> getAllStats() {
        logger.info("Get all the stats...");
        List<Stats> stats = new ArrayList<>();
        try {
            stats = this.statsService.getAllStats().get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }

    /**
     * Endpoint to post new json payloads
     * @param stats a json payload sent by the device
     * @return jsonPayloads which represents all son pauloads received din the 5 minutes interval
     */
    @PostMapping("/stats")
    public List<StatsDTO> createStats(@RequestBody StatsDTO stats) {
        List<StatsDTO> jsonPayloads = new ArrayList<>();
        logger.info("Calling Post Stats Endpoint ..." + stats.toString());
        jsonPayloads = this.statsService.addPayloadToList(stats);
        return jsonPayloads;
    }

}
