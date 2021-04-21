package com.server.stats.service;

import com.server.stats.dto.StatsDTO;
import com.server.stats.entity.Stats;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * Service Interface Used to intercat with the controller Layer and the Repository Layer,
 * Provides different methods for aggregating and groupping json payloads received
 */
public interface IStatsService {

    CompletableFuture<List<Stats>> getAllStats();

    List<Stats> aggregatePayloadsAndSaveStatsToDB() ;

    List<StatsDTO> addPayloadToList(StatsDTO jsonPayLoad);

    CompletableFuture<Stats> saveStatsToDB(Stats aggregatedStats);

}
