package com.fitness.fitnessTrackerServer.services.stats;

import com.fitness.fitnessTrackerServer.dto.GraphDTO;
import com.fitness.fitnessTrackerServer.dto.StatsDTO;

public interface StatsService {

    StatsDTO getStats();

    GraphDTO getGraphStats();
}
