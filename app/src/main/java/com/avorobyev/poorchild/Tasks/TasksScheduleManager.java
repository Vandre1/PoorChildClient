package com.avorobyev.poorchild.Tasks;

import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.Tasks.Filters.TaskSchedulesFilter;

import java.util.List;

public class TasksScheduleManager {

    public static List<TaskSchedule> ApplyFilters(List<TaskSchedule> taskSchedules, List<TaskSchedulesFilter> filters) {
        for (TaskSchedulesFilter filter : filters) {
            taskSchedules = filter.Apply(taskSchedules);
        }

        return taskSchedules;
    }
}
