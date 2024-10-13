package com.fitness.fitnessTrackerServer.services.activity;

import com.fitness.fitnessTrackerServer.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {

    ActivityDTO postActivity(ActivityDTO dto);

    List<ActivityDTO> getActivities();
}
