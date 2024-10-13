package com.fitness.fitnessTrackerServer.services.stats;

import com.fitness.fitnessTrackerServer.dto.GraphDTO;
import com.fitness.fitnessTrackerServer.dto.StatsDTO;
import com.fitness.fitnessTrackerServer.entity.Activity;
import com.fitness.fitnessTrackerServer.entity.Workout;
import com.fitness.fitnessTrackerServer.repository.ActivityRepository;
import com.fitness.fitnessTrackerServer.repository.GoalRepository;
import com.fitness.fitnessTrackerServer.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final GoalRepository goalRepository;

    private final ActivityRepository activityRepository;

    private final WorkoutRepository workoutRepository;

    public StatsDTO getStats() {
        Long achievedGoals = goalRepository.countAchievedGoals();
        Long notAchievedGoals = goalRepository.countNotAchievedGoals();

        Integer totalSteps = activityRepository.getTotalSteps();
        Double totalDistance = activityRepository.getTotalDistance();
        Integer totalActivityCaloriesBurned = activityRepository.getTotalActivityCalories();

        Integer totalWorkoutDuration = workoutRepository.getTotalDuration();
        Integer totalWorkoutCaloriesBurned = workoutRepository.getTotalWorkoutCalories();

        int totalCaloriesBurned = (totalActivityCaloriesBurned != null ? totalActivityCaloriesBurned : 0) +
                (totalWorkoutCaloriesBurned != null ? totalWorkoutCaloriesBurned : 0);

        StatsDTO dto = new StatsDTO();
        dto.setAchievedGoals(achievedGoals != null ? achievedGoals : 0);
        dto.setNotAchievedGoals(notAchievedGoals != null ? notAchievedGoals : 0);

        dto.setSteps(totalSteps != null ? totalSteps : 0);
        dto.setDistance(totalDistance != null ? totalDistance : 0.0);
        dto.setTotalCaloriesBurned(totalCaloriesBurned);
        dto.setDuration(totalWorkoutDuration != null ? totalWorkoutDuration : 0);

        return dto;
    }


    public GraphDTO getGraphStats(){
        Pageable pageable = PageRequest.of(0, 7);

        List<Workout> workouts = workoutRepository.findLast7Workouts(pageable);
        List<Activity> activities = activityRepository.findLast7Activities(pageable);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setWorkouts(workouts.stream().map(Workout::getWorkoutDto).collect(Collectors.toList()));
        graphDTO.setActivities(activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList()));

        return graphDTO;
    }

}