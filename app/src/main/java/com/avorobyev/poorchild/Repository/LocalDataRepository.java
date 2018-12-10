package com.avorobyev.poorchild.Repository;

import android.app.Activity;
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

public class LocalDataRepository implements IRepository {

    private ArrayList<Children> emptyChildrens = new ArrayList<>();
    private ArrayList<Children> childrens = new ArrayList<>();
    private ArrayList<Parent> parents = new ArrayList<>();
    private ArrayList<TaskSchedule> taskSchedules = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Photo> photos = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();

    public void generateData() {
        Children children1 = new Children("11111", "Петр", "Воробьев", new Date());
        Children children2 = new Children("22222", "Иван", "Петров", new Date());
        Children children3 = new Children("33333", "Сергей", "Иванов", new Date());
        Children children4 = new Children("44444", "Михаил", "Зуев", new Date());
        Children children5 = new Children("55555", "Андрей", "Дудин", new Date());
        this.childrens.add(children1);
        this.childrens.add(children2);
        this.childrens.add(children3);
        this.childrens.add(children4);
        this.childrens.add(children5);

        Children childrenEmpty1 = new Children("66666", "Фигвам", "Иванович", new Date());
        Children childrenEmpty2 = new Children("77777", "Фигагут", "Дубинович", new Date());
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
    }

    @Override
    public void GetChildrens(String parentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Children> resultListener) {
        resultListener.LoadCompleted();
        resultListener.LoadSuccess(this.childrens);
    }

    @Override
    public void GetChildren(String childrenId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Children> resultListener) {
        Children result = null;

        for (Children children : this.childrens) {
            if (children.Id == childrenId) {
                result = children;
                break;
            }
        }

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadCompleted();
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void CreateChildren(Children children, ProgressBar progressBar, Activity activity, LoadItemResultListener<Children> resultListener) {
        this.childrens.add(children);

        resultListener.LoadCompleted();
        resultListener.LoadSuccess(children);
    }

    @Override
    public void EditChildren(Children children, ProgressBar progressBar, Activity activity, LoadItemResultListener<Children> resultListener) {
        for (Children currentChildren : this.childrens) {
            if (currentChildren.Id == children.Id) {
                currentChildren.FirstName = children.FirstName;
                currentChildren.LastName = children.LastName;
                break;
            }
        }
    }

    @Override
    public void AddChildren(String parentId, String childrenCode, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {
        try {
            Parent parent = null;

            for (Parent currentParent : this.parents) {
                if (parent.Id == parentId) {
                    parent = currentParent;
                    break;
                }
            }

            resultListener.RequestCompleted();

            if (childrenCode == "code1") {
                Children children = emptyChildrens.get(0);
                parent.Childrens.add(children);
                resultListener.RequestSuccess();

            } else if (childrenCode == "code2") {
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
        Parent result = null;

        for (Parent parent : this.parents) {
            if (parent.Id == parentId) {
                result = parent;
                break;
            }
        }

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadCompleted();
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void CreateParent(Parent parent, ProgressBar progressBar, Activity activity, LoadItemResultListener<Parent> resultListener) {
        this.parents.add(parent);

        resultListener.LoadCompleted();
        resultListener.LoadSuccess(parent);
    }

    @Override
    public void EditParent(Parent parent, ProgressBar progressBar, Activity activity, LoadItemResultListener<Parent> resultListener) {
        for (Parent currentParent : this.parents) {
            if (currentParent.Id == parent.Id) {
                currentParent.FirstName = parent.FirstName;
                currentParent.LastName = parent.LastName;
                break;
            }
        }
    }

    @Override
    public void GetTaskSchedules(String parentId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<TaskSchedule> resultListener) {
        ArrayList<TaskSchedule> result = null;

        for (Parent parent : this.parents) {
            if (parent.Id == parentId) {
                result = new ArrayList<>(parent.TaskSchedules.size());
                result.addAll(parent.TaskSchedules);
                break;
            }
        }

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadCompleted();
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void GetTaskSchedule(String taskScheduleId, ProgressBar progressBar, Activity activity, LoadItemResultListener<TaskSchedule> resultListener) {
        TaskSchedule result = null;

        for (TaskSchedule taskSchedule : this.taskSchedules) {
            if (taskSchedule.Id == taskScheduleId) {
                result = taskSchedule;
                break;
            }
        }

        if (result == null) {
            resultListener.LoadError(new Exception());
        } else {
            resultListener.LoadCompleted();
            resultListener.LoadSuccess(result);
        }
    }

    @Override
    public void CreateTaskSchedule(TaskSchedule taskSchedule, String parentId, ProgressBar progressBar, Activity activity, LoadItemResultListener<TaskSchedule> resultListener) {
        for (Parent parent : this.parents) {
            if (parent.Id == parentId) {
                this.taskSchedules.add(taskSchedule);
                parent.TaskSchedules.add(taskSchedule);

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(taskSchedule);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void EditTaskSchedule(TaskSchedule taskSchedule, ProgressBar progressBar, Activity activity, LoadItemResultListener<TaskSchedule> resultListener) {
        for (TaskSchedule currentTaskSchedule : this.taskSchedules) {
            if (currentTaskSchedule.Id == taskSchedule.Id) {
                currentTaskSchedule.DaysOfWeek = taskSchedule.DaysOfWeek;
                currentTaskSchedule.Description = taskSchedule.Description;
                currentTaskSchedule.Name = taskSchedule.Name;
                currentTaskSchedule.TimeToEnd = taskSchedule.TimeToEnd;
                currentTaskSchedule.TimeToStart = taskSchedule.TimeToStart;

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(currentTaskSchedule);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void DeleteTaskSchedule(String taskScheduleId, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {
        TaskSchedule taskSchedule = null;
        Parent parent = null;

        for (TaskSchedule currentTaskSchedule : this.taskSchedules) {
            if (currentTaskSchedule.Id == taskScheduleId) {
                taskSchedule = currentTaskSchedule;

                break;
            }
        }

        for (Parent currentParent : this.parents) {
            for (TaskSchedule currentTaskSchedule : parent.TaskSchedules) {
                if (currentTaskSchedule.Id == taskScheduleId) {
                    parent = currentParent;

                    break;
                }
            }
        }

        if (taskSchedule == null || parent == null) {
            resultListener.RequestNotFound();
            return;
        }

        this.taskSchedules.remove(taskSchedule);
        parent.TaskSchedules.remove(taskSchedule);

        resultListener.RequestCompleted();
        resultListener.RequestSuccess();
    }

    @Override
    public void GetChildrenTasks(String childrenId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Task> resultListener) {
        for (Children children : this.childrens) {
            if (children.Id == childrenId) {
                ArrayList tasks = new ArrayList(children.Tasks);

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(tasks);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetParentTasks(String parentId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Task> resultListener) {
        for (Parent parent : this.parents) {
            if (parent.Id == parentId) {
                ArrayList tasks = new ArrayList(parent.Tasks);

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(tasks);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetTask(String taskId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Task> resultListener) {
        for (Task task : this.tasks) {
            if (task.Id == taskId) {
                resultListener.LoadCompleted();
                resultListener.LoadSuccess(task);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetComments(String taskId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Comment> resultListener) {
        for (Task task : this.tasks) {
            if (task.Id == taskId) {
                ArrayList<Comment> comments = new ArrayList<>(task.Comments);

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(comments);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetComment(String commentId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Comment> resultListener) {
        for (Comment comment : this.comments) {
            if (comment.Id == commentId) {

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(comment);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void EditComment(Comment comment, ProgressBar progressBar, Activity activity, LoadItemResultListener<Comment> resultListener) {
        for (Comment currentComment : this.comments) {
            if (currentComment.Id == comment.Id) {
                currentComment.Text = comment.Text;

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(currentComment);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void DeleteComment(String commentId, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {
        Comment comment = null;
        Task task = null;

        for (Comment currentComment : this.comments) {
            if (comment.Id == commentId) {
                comment = currentComment;

                break;
            }
        }

        for (Task currentTask : this.tasks) {
            for (Comment currentComment : currentTask.Comments) {
                if (currentComment.Id == commentId) {
                    task = currentTask;

                    break;
                }
            }
        }

        if (task == null || comment == null) {
            resultListener.RequestNotFound();
            return;
        }

        this.comments.remove(comment);
        task.Comments.remove(comment);

        resultListener.RequestCompleted();
        resultListener.RequestSuccess();
    }

    @Override
    public void GetPhotos(String commentId, ProgressBar progressBar, Activity activity, LoadCollectionResultListener<Photo> resultListener) {
        for (Comment comment : this.comments) {
            if (comment.Id == commentId) {
                ArrayList<Photo> photos = new ArrayList<>(comment.Photos);

                resultListener.LoadCompleted();
                resultListener.LoadSuccess(photos);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void GetPhoto(String photoId, ProgressBar progressBar, Activity activity, LoadItemResultListener<Photo> resultListener) {
        for (Photo photo : this.photos) {
            if (photo.Id == photoId) {
                resultListener.LoadCompleted();
                resultListener.LoadSuccess(photo);

                return;
            }
        }

        resultListener.LoadError(new Exception());
    }

    @Override
    public void DeletePhoto(String photoId, ProgressBar progressBar, Activity activity, RequestResultListener resultListener) {
        Photo photo = null;
        Comment comment = null;

        for (Photo currentComment : this.photos) {
            if (photo.Id == photoId) {
                photo = currentComment;

                break;
            }
        }

        for (Comment currentTask : this.comments) {
            for (Photo currentComment : currentTask.Photos) {
                if (currentComment.Id == photoId) {
                    comment = currentTask;

                    break;
                }
            }
        }

        if (comment == null || photo == null) {
            resultListener.RequestNotFound();
            return;
        }

        this.comments.remove(photo);
        comment.Photos.remove(photo);

        resultListener.RequestCompleted();
        resultListener.RequestSuccess();
    }
}
