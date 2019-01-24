package com.avorobyev.poorchild.Repository;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Dao.Comment;
import com.avorobyev.poorchild.Dao.Parent;
import com.avorobyev.poorchild.Dao.Photo;
import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.Networking.LoadCollectionResultListener;
import com.avorobyev.poorchild.Networking.LoadItemResultListener;
import com.avorobyev.poorchild.Networking.RequestResultListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class LocalDataRepository implements IRepository {

    private static ArrayList<Children> emptyChildrens = new ArrayList<>();
    private static ArrayList<Children> childrens = new ArrayList<>();
    private static ArrayList<Parent> parents = new ArrayList<>();
    private static ArrayList<TaskSchedule> taskSchedules = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Photo> photos = new ArrayList<>();
    private static ArrayList<Comment> comments = new ArrayList<>();

    public LocalDataRepository() {
        this.generateData();
    }

    public void generateData() {
        Children children1 = new Children("11111", "Петр", "Воробьев", "011111", new Date());
        Children children2 = new Children("22222", "Иван", "Петров","022222", new Date());
        Children children3 = new Children("33333", "Сергей", "Иванов","033333", new Date());
        Children children4 = new Children("44444", "Михаил", "Зуев","044444", new Date());
        Children children5 = new Children("55555", "Андрей", "Дудин","055555", new Date());
        this.childrens.add(children1);
        this.childrens.add(children2);
        this.childrens.add(children3);
        this.childrens.add(children4);
        this.childrens.add(children5);

        Children childrenEmpty1 = new Children("66666", "Фигвам", "Иванович", "066666", new Date());
        Children childrenEmpty2 = new Children("77777", "Фигагут", "Дубинович","077777", new Date());
        this.emptyChildrens.add(childrenEmpty1);
        this.emptyChildrens.add(childrenEmpty2);

        Parent parent1 = new Parent("21212", "Елена", "Воробьева", new Date());
        Parent parent2 = new Parent("32323", "Ольга", "Зубкова", new Date());
        Parent parent3 = new Parent("43434", "Анна", "Бизюкова", new Date());
        this.parents.add(parent1);
        this.parents.add(parent2);
        this.parents.add(parent3);
        parent1.Childrens.add(children1);
        parent1.Childrens.add(children2);
        parent1.Childrens.add(children3);
        parent2.Childrens.add(children1);
        parent2.Childrens.add(children2);
        parent2.Childrens.add(children3);
        parent3.Childrens.add(children4);
        parent3.Childrens.add(children5);

        TaskSchedule taskSchedule1 = new TaskSchedule("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date());
        TaskSchedule taskSchedule2 = new TaskSchedule("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date());
        TaskSchedule taskSchedule3 = new TaskSchedule("33333", "Убрать игрушки", "Хорошо, чтобы комар носа не подточил", -1, -1, new Date());
        this.taskSchedules.add(taskSchedule1);
        this.taskSchedules.add(taskSchedule2);
        this.taskSchedules.add(taskSchedule3);
        parent1.TaskSchedules.add(taskSchedule1);
        parent1.TaskSchedules.add(taskSchedule2);
        parent1.TaskSchedules.add(taskSchedule2);

        Task task1 = new Task("11111", "Помыть пол", "Хорошо, так чтобы блестел", 60000, 120000, new Date(), null, null);
        Task task2 = new Task("22222", "Помыть посуду", "Хорошо, так чтобы блестела", -1, -1, new Date(), null, null);
        Task task3 = new Task("33333", "Убрать игрушки", "Хорошо, чтобы комар носа не подточил", -1, -1, new Date(), null, null);
        this.tasks.add(task1);
        this.tasks.add(task2);
        this.tasks.add(task3);
        children1.Tasks.add(task1);
        children1.Tasks.add(task2);
        children1.Tasks.add(task3);
    }

    @Override
    public void GetChildrens(String parentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Children> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadSuccess(this.childrens);
    }

    @Override
    public void GetChildren(String childrenId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Children> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Children result = null;

        for (Children children : this.childrens) {
            if (childrenId.equals(children.Id)) {
                result = children;
                break;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void CreateChildren(Children children, ProgressBar progressBar, Activity activity, LoadItemResultListener<Children> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        children.Id = "00000000-0000-0000-0000-000000000000";
        children.RegistrationCode = "123456";

        this.childrens.add(children);

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadSuccess(children);
    }

    @Override
    public void EditChildren(Children children, ProgressBar progressBar, Activity activity, LoadItemResultListener<Children> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Children currentChildren : this.childrens) {
            if (currentChildren.Id.equals(children.Id)) {
                currentChildren.FirstName = children.FirstName;
                currentChildren.LastName = children.LastName;

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(currentChildren);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void AddChildren(String parentId, String childrenCode, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        try {
            Parent parent = null;

            for (Parent currentParent : this.parents) {
                if (currentParent.Id.equals(parentId)) {
                    parent = currentParent;
                    break;
                }
            }

            resultListener.RequestCompleted();

            // Скрываем ProgressBar
            progressBar.setVisibility(View.INVISIBLE);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            if (childrenCode.equals("code1")) {
                Children children = emptyChildrens.get(0);
                parent.Childrens.add(children);
                resultListener.RequestSuccess();

            } else if (childrenCode.equals("code2")) {
                Children children = emptyChildrens.get(1);
                parent.Childrens.add(children);
                resultListener.RequestSuccess();

            } else {
                resultListener.RequestNotFound();
            }
        }
        catch (Exception e)
        {
            resultListener.RequestError(e);
        }
    }

    @Override
    public void GetParent(String parentId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Parent> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Parent result = null;

        for (Parent parent : this.parents) {
            if (parent.Id.equals(parentId)) {
                result = parent;
                break;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void CreateParent(Parent parent, ProgressBar progressBar, Activity activity, LoadItemResultListener<Parent> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        parent.Id = "00000000-0000-0000-0000-000000000000";

        this.parents.add(parent);

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadSuccess(parent);
    }

    @Override
    public void EditParent(Parent parent, ProgressBar progressBar, Activity activity, LoadItemResultListener<Parent> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Parent currentParent : this.parents) {
            if (currentParent.Id.equals(parent.Id)) {
                currentParent.FirstName = parent.FirstName;
                currentParent.LastName = parent.LastName;

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(currentParent);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetTaskSchedules(String parentId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<TaskSchedule> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ArrayList<TaskSchedule> result = null;

        for (Parent parent : this.parents) {
            if (parent.Id.equals(parentId)) {
                result = new ArrayList<>(parent.TaskSchedules.size());
                result.addAll(parent.TaskSchedules);
                break;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void GetTaskSchedule(String taskScheduleId, ProgressBar progressBar, Activity activity, LoadItemResultListener<TaskSchedule> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        TaskSchedule result = null;

        for (TaskSchedule taskSchedule : this.taskSchedules) {
            if (taskSchedule.Id.equals(taskScheduleId)) {
                result = taskSchedule;
                break;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void CreateTaskSchedule(TaskSchedule taskSchedule, String parentId, ProgressBar progressBar, Activity activity, LoadItemResultListener<TaskSchedule> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        taskSchedule.Id = "00000000-0000-0000-0000-000000000000";

        for (Parent parent : this.parents) {
            if (parent.Id.equals(parentId)) {
                this.taskSchedules.add(taskSchedule);
                parent.TaskSchedules.add(taskSchedule);

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(taskSchedule);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void EditTaskSchedule(TaskSchedule taskSchedule, ProgressBar progressBar, Activity activity, LoadItemResultListener<TaskSchedule> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (TaskSchedule currentTaskSchedule : this.taskSchedules) {
            if (currentTaskSchedule.Id.equals(taskSchedule.Id)) {
                currentTaskSchedule.DaysOfWeek = taskSchedule.DaysOfWeek;
                currentTaskSchedule.Description = taskSchedule.Description;
                currentTaskSchedule.Name = taskSchedule.Name;
                currentTaskSchedule.TimeToEnd = taskSchedule.TimeToEnd;
                currentTaskSchedule.TimeToStart = taskSchedule.TimeToStart;

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(currentTaskSchedule);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void DeleteTaskSchedule(String taskScheduleId, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        TaskSchedule taskSchedule = null;
        Parent parent = null;

        for (TaskSchedule currentTaskSchedule : this.taskSchedules) {
            if (currentTaskSchedule.Id.equals(taskScheduleId)) {
                taskSchedule = currentTaskSchedule;

                break;
            }
        }

        for (Parent currentParent : this.parents) {
            for (TaskSchedule currentTaskSchedule : parent.TaskSchedules) {
                if (currentTaskSchedule.Id.equals(taskScheduleId)) {
                    parent = currentParent;

                    break;
                }
            }
        }

        resultListener.RequestCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if (taskSchedule == null || parent == null) {
            resultListener.RequestNotFound();
            return;
        }

        this.taskSchedules.remove(taskSchedule);
        parent.TaskSchedules.remove(taskSchedule);

        resultListener.RequestSuccess();
    }

    @Override
    public void GetChildrenTasks(String childrenId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Task> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Children children : this.childrens) {
            if (childrenId.equals(children.Id)) {
                ArrayList tasks = new ArrayList(children.Tasks);

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(tasks);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetParentTasks(String parentId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Task> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Parent parent : this.parents) {
            if (parent.Id.equals(parentId)) {
                ArrayList tasks = new ArrayList(parent.Tasks);

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(tasks);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetTask(String taskId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Task> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Task task : this.tasks) {
            if (task.Id.equals(taskId)) {
                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(task);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetComments(String taskId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Comment> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Task task : this.tasks) {
            if (task.Id.equals(taskId)) {
                ArrayList<Comment> comments = new ArrayList<>(task.Comments);

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(comments);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetComment(String commentId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Comment> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Comment comment : this.comments) {
            if (comment.Id.equals(commentId)) {

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(comment);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void EditComment(Comment comment, ProgressBar progressBar, Activity activity, LoadItemResultListener<Comment> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Comment currentComment : this.comments) {
            if (currentComment.Id.equals(comment.Id)) {
                currentComment.Text = comment.Text;

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(currentComment);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void DeleteComment(String commentId, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Comment comment = null;
        Task task = null;

        for (Comment currentComment : this.comments) {
            if (comment.Id.equals(commentId)) {
                comment = currentComment;

                break;
            }
        }

        for (Task currentTask : this.tasks) {
            for (Comment currentComment : currentTask.Comments) {
                if (currentComment.Id.equals(commentId)) {
                    task = currentTask;

                    break;
                }
            }
        }

        resultListener.RequestCompleted();

        if (task == null || comment == null) {
            resultListener.RequestNotFound();
            return;
        }

        this.comments.remove(comment);
        task.Comments.remove(comment);

        resultListener.RequestSuccess();
    }

    @Override
    public void GetPhotos(String commentId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Photo> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Comment comment : this.comments) {
            if (comment.Id.equals(commentId)) {
                ArrayList<Photo> photos = new ArrayList<>(comment.Photos);

                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(photos);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetPhoto(String photoId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Photo> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Photo photo : this.photos) {
            if (photo.Id.equals(photoId)) {
                resultListener.LoadCompleted();

                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                resultListener.LoadSuccess(photo);

                return;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadError(new Exception());
    }

    @Override
    public void DeletePhoto(String photoId, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Photo photo = null;
        Comment comment = null;

        for (Photo currentComment : this.photos) {
            if (photo.Id.equals(photoId)) {
                photo = currentComment;

                break;
            }
        }

        for (Comment currentTask : this.comments) {
            for (Photo currentComment : currentTask.Photos) {
                if (currentComment.Id.equals(photoId)) {
                    comment = currentTask;

                    break;
                }
            }
        }

        resultListener.RequestCompleted();

        if (comment == null || photo == null) {
            resultListener.RequestNotFound();
            return;
        }

        this.comments.remove(photo);
        comment.Photos.remove(photo);

        resultListener.RequestSuccess();
    }
}
