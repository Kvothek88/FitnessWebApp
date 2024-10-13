package com.fitness.fitnessTrackerServer.services.workout;


import com.fitness.fitnessTrackerServer.dto.WorkoutDTO;

import java.util.List;

public interface WorkoutService {

    WorkoutDTO postWorkout(WorkoutDTO workoutDTO);

    List<WorkoutDTO> getWorkouts();
}
