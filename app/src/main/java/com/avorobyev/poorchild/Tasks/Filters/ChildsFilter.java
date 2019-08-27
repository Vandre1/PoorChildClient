package com.avorobyev.poorchild.Tasks.Filters;

import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Dao.TaskSchedule;

import java.util.ArrayList;
import java.util.List;

public class ChildsFilter extends TaskSchedulesFilter {

    protected List<String> childrensId;

    public ChildsFilter(List<String> childrensId) {
        this.childrensId = childrensId;
    }

    @Override
    public ArrayList<TaskSchedule> Apply(List<TaskSchedule> taskSchedules) {
        taskSchedules = super.Apply(taskSchedules);
        ArrayList<TaskSchedule> result = new ArrayList<TaskSchedule>();

        // Получаем только те TaskSchedules у которых ChildrensId содержит id childs фильтра
        for (TaskSchedule taskSchedule : taskSchedules) {
            beginTaskScheduleLoop:
            // перебираем всех детей фильтра
            for (String currentChildrenId : this.childrensId) {

                // Перебираем идентификаторы детей текущего taskSchedule
                for (String childrenId : taskSchedule.ChildrensId) {

                    // Если ребенок есть в taskSchedule, то добавляем его в результирующий набор
                    if (childrenId.equals(currentChildrenId)) {
                        result.add(taskSchedule);

                        // taskSchedule добавлен в результирующий набор, переходим к следующему taskSchedule
                        break beginTaskScheduleLoop;
                    }
                }
            }
        }
        return result;
    }
}
