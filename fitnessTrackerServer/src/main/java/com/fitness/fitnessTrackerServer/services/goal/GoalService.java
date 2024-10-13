package com.fitness.fitnessTrackerServer.services.goal;

import com.fitness.fitnessTrackerServer.dto.GoalDTO;

import java.util.List;

public interface GoalService {

    GoalDTO postGoal(GoalDTO goalDTO);

    List<GoalDTO> getGoals();

    GoalDTO updateStatus(Long id);
}
