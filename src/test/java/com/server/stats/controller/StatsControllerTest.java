package com.server.stats.controller;

import com.server.stats.dto.StatsDTO;
import com.server.stats.entity.Stats;
import com.server.stats.repository.StatsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class StatsControllerTest {


    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private StatsController statsController;


    @Test
    public void shouldReturnListOfStatsStoredInDB() {
        List<Stats> response = this.statsController.getAllStats();
        //Assertions.assertThat(response.size()).isEqualTo(41);
    }

    @Test
    public void createStatsTest()
    {
        StatsDTO statsDTO = new StatsDTO("token","customer1","content1",10,10,10,10);
        List<StatsDTO> statsRespoonse = this.statsController.createStats(statsDTO);
        //Assertions.assertThat(statsRespoonse.size()).isEqualTo(1);

    }

    @Test
    public void createStatsTestWithTwoJsonPayloads()
    {
        StatsDTO statsDTO = new StatsDTO("token","customer1","content1",10,10,10,10);
        StatsDTO statsDTO2 = new StatsDTO("token","customer1","content1",10,10,10,10);
        List<StatsDTO> statsRespoonse = this.statsController.createStats(statsDTO);
        statsRespoonse =  this.statsController.createStats(statsDTO2);
        //Assertions.assertThat(statsRespoonse.size()).isEqualTo(2);

    }

}
