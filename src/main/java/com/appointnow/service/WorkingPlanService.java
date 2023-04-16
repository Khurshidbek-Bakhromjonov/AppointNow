package com.appointnow.service;

import com.appointnow.entity.WorkingPlan;
import com.appointnow.model.TimePeriod;

public interface WorkingPlanService {

    void updateWorkingPlan(WorkingPlan workingPlan);
    void addBreakToWorkingPlan(TimePeriod breakToAdd, int planId, String dayOfWeek);
    void deleteBreakFromWorkingPlan(TimePeriod breakToDelete, int planId, String dayOfWeek);
    WorkingPlan getWorkingPlanByProviderId(int providerId);
}
