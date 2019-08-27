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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class LocalDataRepository implements IRepository {

    @Override
    public void GetChildrens(final String parentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Children> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Parent foundParent = null;

        for (Parent parent : Parents) {
            if (parentId.equals(parent.Id)) {
                foundParent = parent;
                break;
            }
        }

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadSuccess(new ArrayList<>(foundParent.Childrens));
    }

    @Override
    public void GetChildren(String childrenId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Children> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Children result = null;

        for (Children children : this.Childrens) {
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

        this.Childrens.add(children);

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

        for (Children currentChildren : this.Childrens) {
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

            for (Parent currentParent : this.Parents) {
                if (currentParent.Id.equals(parentId)) {
                    parent = currentParent;
                    break;
                }
            }

            resultListener.RequestCompleted();

            // Скрываем ProgressBar
            progressBar.setVisibility(View.INVISIBLE);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            if (childrenCode.equals("011111")) {
                Children children = EmptyChildrensWithoutAnyDependencies.get(0);
                parent.Childrens.add(children);
                resultListener.RequestSuccess();

            } else if (childrenCode.equals("022222")) {
                Children children = EmptyChildrensWithoutAnyDependencies.get(1);
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
    public void GetdChildrenCode(String childrenId, ProgressBar progressBar, Activity activity, LoadItemResultListener<String> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadCompleted();

        // Скрываем ProgressBar
        progressBar.setVisibility(View.INVISIBLE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        resultListener.LoadSuccess("128767");
    }

    @Override
    public void GetParent(String parentId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Parent> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Parent result = null;

        for (Parent parent : this.Parents) {
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

        this.Parents.add(parent);

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

        for (Parent currentParent : this.Parents) {
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

        for (Parent parent : this.Parents) {
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

        for (TaskSchedule taskSchedule : this.TaskSchedules) {
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
    public void GetTaskScheduleByTask(String taskId, ProgressBar progressBar, Activity activity, LoadItemResultListener<TaskSchedule> resultListener) {
        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        TaskSchedule result = this.TaskSchedules.get(0);

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

        for (Parent parent : this.Parents) {
            if (parent.Id.equals(parentId)) {
                this.TaskSchedules.add(taskSchedule);
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

        for (TaskSchedule currentTaskSchedule : this.TaskSchedules) {
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

        for (TaskSchedule currentTaskSchedule : this.TaskSchedules) {
            if (currentTaskSchedule.Id.equals(taskScheduleId)) {
                taskSchedule = currentTaskSchedule;

                break;
            }
        }

        for (Parent currentParent : this.Parents) {
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

        this.TaskSchedules.remove(taskSchedule);
        parent.TaskSchedules.remove(taskSchedule);

        resultListener.RequestSuccess();
    }

    @Override
    public void GetChildrenTasks(String childrenId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Task> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Children children : this.Childrens) {
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

        for (Parent parent : this.Parents) {
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

        for (Task task : this.Tasks) {
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

        for (Task task : this.Tasks) {
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

        for (Comment comment : this.Comments) {
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

        for (Comment currentComment : this.Comments) {
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

        for (Comment currentComment : this.Comments) {
            if (comment.Id.equals(commentId)) {
                comment = currentComment;

                break;
            }
        }

        for (Task currentTask : this.Tasks) {
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

        this.Comments.remove(comment);
        task.Comments.remove(comment);

        resultListener.RequestSuccess();
    }

    @Override
    public void GetPhotos(String commentId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Photo> resultListener) {

        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        for (Comment comment : this.Comments) {
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

        for (Photo photo : this.Photos) {
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

        for (Photo currentComment : this.Photos) {
            if (photo.Id.equals(photoId)) {
                photo = currentComment;

                break;
            }
        }

        for (Comment currentTask : this.Comments) {
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

        this.Comments.remove(photo);
        comment.Photos.remove(photo);

        resultListener.RequestSuccess();
    }

    public static ArrayList<Children> EmptyChildrensWithoutAnyDependencies = new ArrayList<>();
    public static ArrayList<Children> Childrens = new ArrayList<>();
    public static ArrayList<Parent> Parents = new ArrayList<>();
    public static ArrayList<TaskSchedule> TaskSchedules = new ArrayList<>();
    public static ArrayList<Task> Tasks = new ArrayList<>();
    public static ArrayList<Photo> Photos = new ArrayList<>();
    public static ArrayList<Comment> Comments = new ArrayList<>();

    public LocalDataRepository() {
    }
}
