package com.fitness.fitnessTrackerServer.services.goal;

import com.fitness.fitnessTrackerServer.dto.GoalDTO;
import com.fitness.fitnessTrackerServer.entity.Goal;
import com.fitness.fitnessTrackerServer.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    public GoalDTO postGoal(GoalDTO goalDTO) {
        Goal goal = new Goal();

        goal.setId(goalDTO.getId());
        goal.setDescription(goalDTO.getDescription());
        goal.setStartDate(goalDTO.getStartDate());
        goal.setEndDate(goalDTO.getEndDate());
        goal.setAchieved(false);

        return goalRepository.save(goal).getGoalDTO();
    }

    public List<GoalDTO> getGoals() {
        List<Goal> goals = goalRepository.findAll();

        return goals.stream().map(Goal::getGoalDTO).collect(Collectors.toList());
    }

    public GoalDTO updateStatus(Long id){

        Optional<Goal> optionalGoal = goalRepository.findById(id);

        if(optionalGoal.isPresent()){
            Goal existingGoal = optionalGoal.get();

            existingGoal.setAchieved(true);
            return goalRepository.save(existingGoal).getGoalDTO();
        }
        throw new EntityNotFoundException("Goal not found");
    }

}
