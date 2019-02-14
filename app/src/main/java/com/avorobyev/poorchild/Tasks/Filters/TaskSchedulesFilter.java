package com.avorobyev.poorchild.Tasks.Filters;

import com.avorobyev.poorchild.Dao.TaskSchedule;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskSchedulesFilter {

    public List<TaskSchedule> Apply(List<TaskSchedule> taskSchedules) {
        return taskSchedules;
    }
}
