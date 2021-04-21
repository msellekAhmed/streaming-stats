package com.server.stats.repository;

import com.server.stats.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * JPA Repository Interface that expose different method for CRUD our entity,
 * In our case as we need only getAll Stats and save stats we don't have to declare other methods and their implementation
 */
@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {

}
