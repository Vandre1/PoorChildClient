package com.avorobyev.poorchild;

import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Dao.Parent;
import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.Repository.LocalDataRepository;
import com.orhanobut.hawk.Hawk;

import java.util.Arrays;
import java.util.Date;

public class StateManager {
    public static void ClearState() {
        Hawk.put(PreferenceHelper.IsCurrentDeviceChild, null);
        Hawk.put(PreferenceHelper.IsCurrentDeviceParent, null);
        Hawk.put(PreferenceHelper.ChildDeviceId, null);
        Hawk.put(PreferenceHelper.ParentDeviceId, null);
    }

    public static void SetChildState(String childrenId) {
        ClearState();
        Hawk.put(PreferenceHelper.IsCurrentDeviceChild, true);
        Hawk.put(PreferenceHelper.ChildDeviceId, childrenId);
    }

    public static void SetParentState(String parentId) {
        ClearState();
        Hawk.put(PreferenceHelper.IsCurrentDeviceParent, true);
        Hawk.put(PreferenceHelper.ParentDeviceId, parentId);
    }

    public static void GenerateState() {
        ClearState();

        // Старые данные
        // SetParentState("22222");
        // generateData();

        /* Экран выбора роли */
        // Чистая система
        // generateData1_1();

        // Роль ребенка
        // generateData1_2(); SetChildState("11111");

        // Роль родитель
        // generateData1_3(); SetParentState("22222");

        /* Экран списка задач у родителя */
        // Нет детей, нет задач (Будет экран добавления ребенка)
        // generateData2_1(); SetParentState("22222");

        // 1 ребенок, 2 задачи
        // generateData2_2(); SetParentState("22222");

        // 2 ребенка, 2 задачи
        // generateData2_3(); SetParentState("22222");

        // 2 ребенка, 2 + 1 задачи
        generateData2_4(); SetParentState("22222");

        /* Экран списка детей у родителя */
        // Нет детей
        // generateData3_1(); SetParentState("22222");

        // 1 ребенок
        // generateData3_2(); SetParentState("22222");

        // 2 ребенка
        // generateData3_3(); SetParentState("22222");

        /* Экран добавления ребенка */
        // Свободные 2 ребенка для добавления
        // generateData4_1(); SetParentState("22222");

        /* Экран списка задач у ребенка */
        // Нет задач
        // generateData5_1(); SetChildState("11111");

        // 2 выполненые задачи, 1 ожидает подтверждения выполнения
        // generateData5_2(); SetChildState("11111");

        // 2 активные задачи
        // generateData5_3(); SetChildState("11111");

        // 1 активная и 1 запланированная
        // generateData5_4(); SetChildState("11111");

        // 2 выполненые, 1 активная и 1 запланированная
        // generateData5_5(); SetChildState("11111");
    }

    // Старые данные
    private static void generateData() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        Children children2 = new Children("22222", "Иван", "Петров", new Date());
        Children children3 = new Children("33333", "Сергей", "Иванов", new Date());
        Children children4 = new Children("44444", "Михаил", "Зуев", new Date());
        Children children5 = new Children("55555", "Андрей", "Дудин", new Date());
        LocalDataRepository.Childrens.add(children1);
        LocalDataRepository.Childrens.add(children2);
        LocalDataRepository.Childrens.add(children3);
        LocalDataRepository.Childrens.add(children4);
        LocalDataRepository.Childrens.add(children5);

        Children childrenEmpty1 = new Children("66666", "Фигвам", "Иванович", new Date());
        Children childrenEmpty2 = new Children("77777", "Фигагут", "Дубинович", new Date());
        LocalDataRepository.EmptyChildrensWithoutAnyDependencies.add(childrenEmpty1);
        LocalDataRepository.EmptyChildrensWithoutAnyDependencies.add(childrenEmpty2);

        Parent parent1 = new Parent("21212", "Елена", "Воробьева", new Date());
        Parent parent2 = new Parent("32323", "Ольга", "Зубкова", new Date());
        Parent parent3 = new Parent("43434", "Анна", "Бизюкова", new Date());
        Parent parent4 = new Parent("54545", "Ольга", "Бизюкова", new Date());
        LocalDataRepository.Parents.add(parent1);
        LocalDataRepository.Parents.add(parent2);
        LocalDataRepository.Parents.add(parent3);
        LocalDataRepository.Parents.add(parent4);
        parent1.Childrens.add(children1);
        parent1.Childrens.add(children2);
        parent1.Childrens.add(children3);
        parent2.Childrens.add(children1);
        parent2.Childrens.add(children2);
        parent2.Childrens.add(children3);
        parent3.Childrens.add(children4);
        parent3.Childrens.add(children5);

        TaskSchedule taskSchedule1 = new TaskSchedule("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children2.Id, children3.Id));
        TaskSchedule taskSchedule2 = new TaskSchedule("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id, children2.Id, children3.Id));
        TaskSchedule taskSchedule3 = new TaskSchedule("33333", "Убрать игрушки", "Хорошо, чтобы комар носа не подточил", -1, -1, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id, children2.Id, children3.Id));
        LocalDataRepository.TaskSchedules.add(taskSchedule1);
        LocalDataRepository.TaskSchedules.add(taskSchedule2);
        LocalDataRepository.TaskSchedules.add(taskSchedule3);
        parent1.TaskSchedules.add(taskSchedule1);
        parent1.TaskSchedules.add(taskSchedule2);
        parent1.TaskSchedules.add(taskSchedule2);

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), null, null);
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), null, null);
        Task task3 = new Task("33333", "Убрать игрушки", "Хорошо, чтобы комар носа не подточил", -1, -1, new Date(), null, null);
        LocalDataRepository.Tasks.add(task1);
        LocalDataRepository.Tasks.add(task2);
        LocalDataRepository.Tasks.add(task3);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
        children1.Tasks.add(task3);
    }

    /* Экран выбора роли */
    // Чистая система
    private static void generateData1_1() {
    }

    // Роль ребенка
    private static void generateData1_2() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        SetChildState("11111");
    }

    // Роль родитель
    private static void generateData1_3() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");
    }

    /* Экран списка задач у родителя */
    // Нет детей, нет задач
    private static void generateData2_1() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");
    }

    // 1 ребенок, 2 задачи
    private static void generateData2_2() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");

        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        parent1.Childrens.add(children1);

        TaskSchedule taskSchedule1 = new TaskSchedule("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id));
        TaskSchedule taskSchedule2 = new TaskSchedule("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id));
        LocalDataRepository.TaskSchedules.add(taskSchedule1);
        LocalDataRepository.TaskSchedules.add(taskSchedule2);
        parent1.TaskSchedules.add(taskSchedule1);
        parent1.TaskSchedules.add(taskSchedule2);
    }

    // 2 ребенка, 2 задачи
    private static void generateData2_3() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");

        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        parent1.Childrens.add(children1);

        Children children2 = new Children("22222", "Глеб", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children2);
        parent1.Childrens.add(children2);

        TaskSchedule taskSchedule1 = new TaskSchedule("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id, children2.Id));
        TaskSchedule taskSchedule2 = new TaskSchedule("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id, children2.Id));
        LocalDataRepository.TaskSchedules.add(taskSchedule1);
        LocalDataRepository.TaskSchedules.add(taskSchedule2);
        parent1.TaskSchedules.add(taskSchedule1);
        parent1.TaskSchedules.add(taskSchedule2);

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), null, null);
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), null, null);
        LocalDataRepository.Tasks.add(task1);
        LocalDataRepository.Tasks.add(task2);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
        children2.Tasks.add(task1);
        children2.Tasks.add(task2);
    }

    // 2 ребенка, 2 + 1 задачи
    private static void generateData2_4() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");

        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        parent1.Childrens.add(children1);

        Children children2 = new Children("22222", "Глеб", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children2);
        parent1.Childrens.add(children2);

        TaskSchedule taskSchedule1 = new TaskSchedule("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id, children2.Id));
        TaskSchedule taskSchedule2 = new TaskSchedule("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children1.Id, children2.Id));
        TaskSchedule taskSchedule3 = new TaskSchedule("33333", "Убрать игрушки", "Хорошо, чтобы комар носа не подточил", -1, -1, new Date(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), Arrays.asList(children2.Id));
        LocalDataRepository.TaskSchedules.add(taskSchedule1);
        LocalDataRepository.TaskSchedules.add(taskSchedule2);
        LocalDataRepository.TaskSchedules.add(taskSchedule3);
        parent1.TaskSchedules.add(taskSchedule1);
        parent1.TaskSchedules.add(taskSchedule2);
        parent1.TaskSchedules.add(taskSchedule3);

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(),null, null);
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), null, null);
        Task task3 = new Task("33333", "Убрать игрушки", "Хорошо, чтобы комар носа не подточил", -1, -1, new Date(), null, null);
        LocalDataRepository.Tasks.add(task1);
        LocalDataRepository.Tasks.add(task2);
        LocalDataRepository.Tasks.add(task3);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
        children2.Tasks.add(task1);
        children2.Tasks.add(task2);
        children2.Tasks.add(task3);
    }

    /* Экран списка детей у родителя */
    // Нет детей
    private static void generateData3_1() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");
    }

    // 1 ребенок
    private static void generateData3_2() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");

        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        parent1.Childrens.add(children1);
    }

    // 2 ребенка
    private static void generateData3_3() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");

        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        parent1.Childrens.add(children1);

        Children children2 = new Children("22222", "Глеб", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children2);
        parent1.Childrens.add(children2);
    }

    /* Экран добавления ребенка */
    // Свободные 2 ребенка для добавления
    private static void generateData4_1() {
        Parent parent1 = new Parent("22222", "Андрей", "Воробьев", new Date());
        LocalDataRepository.Parents.add(parent1);
        SetParentState("22222");

        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);

        Children children2 = new Children("22222", "Глеб", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children2);
    }

    /* Экран списка задач у ребенка */
    // Нет задач
    private static void generateData5_1() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        SetChildState("11111");
    }

    // 2 выполненые задачи, 1 ожидает подтверждения выполнения
    private static void generateData5_2() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        SetChildState("11111");

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), new Date(), new Date());
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), new Date(), new Date());
        Task task3 = new Task("33333", "Убрать игрушки", "Хорошо, чтобы комар носа не подточил", -1, -1, new Date(), new Date(), null);
        LocalDataRepository.Tasks.add(task1);
        LocalDataRepository.Tasks.add(task2);
        LocalDataRepository.Tasks.add(task3);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
        children1.Tasks.add(task3);
    }

    // 2 активные задачи
    private static void generateData5_3() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        SetChildState("11111");

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", -1, -1, new Date(), null, null);
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(),null, null);
        LocalDataRepository.Tasks.add(task1);
        LocalDataRepository.Tasks.add(task2);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
    }

    // 1 активная и 1 запланированная
    private static void generateData5_4() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        SetChildState("11111");

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", 20000, 30000, new Date(), null, null);
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(),null, null);
        LocalDataRepository.Tasks.add(task1);
        LocalDataRepository.Tasks.add(task2);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
    }

    // 2 выполненые, 1 активная и 1 запланированная
    private static void generateData5_5() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        LocalDataRepository.Childrens.add(children1);
        SetChildState("11111");

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), new Date(), new Date());
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), new Date(), new Date());
        Task task3 = new Task("33333", "Помыть пол", "Хорошо, так чтобы блестел", 20000, 30000, new Date(), null, null);
        Task task4 = new Task("44444", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(),null, null);
        LocalDataRepository.Tasks.add(task1);
        LocalDataRepository.Tasks.add(task2);
        LocalDataRepository.Tasks.add(task3);
        LocalDataRepository.Tasks.add(task4);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
        children1.Tasks.add(task3);
        children1.Tasks.add(task4);
    }
}
