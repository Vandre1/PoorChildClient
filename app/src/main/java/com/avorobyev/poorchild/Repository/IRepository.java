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

import java.util.Date;

public interface IRepository {
    void GetChildrens(String parentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Children> resultListener);
    void GetChildren(String childrenId, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Children> resultListener);
    void CreateChildren(Children children, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Children> resultListener);
    void EditChildren(Children children, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Children> resultListener);
    void AddChildren(String parentId, String childrenCode, final ProgressBar progressBar, final Activity activity, final RequestResultListener resultListener);

    void GetParent(String parentId, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Parent> resultListener);
    void CreateParent(Parent parent, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Parent> resultListener);
    void EditParent(Parent parent, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Parent> resultListener);

    void GetTaskSchedules(String parentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<TaskSchedule> resultListener);
    void GetTaskSchedule(String taskScheduleId, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<TaskSchedule> resultListener);
    void CreateTaskSchedule(TaskSchedule taskSchedule, String parentId, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<TaskSchedule> resultListener);
    void EditTaskSchedule(TaskSchedule taskSchedule, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<TaskSchedule> resultListener);
    void DeleteTaskSchedule(String taskScheduleId, final ProgressBar progressBar, final Activity activity, final RequestResultListener resultListener);

    void GetChildrenTasks(String childrenId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Task> resultListener);
    void GetParentTasks(String parentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Task> resultListener);
    void GetTask(String taskId, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Task> resultListener);

    void GetComments(String taskId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Comment> resultListener);
    void GetComment(String commentId, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Comment> resultListener);
    void EditComment(Comment comment, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Comment> resultListener);
    void DeleteComment(String commentId, final ProgressBar progressBar, final Activity activity, final RequestResultListener resultListener);

    void GetPhotos(String commentId, final ProgressBar progressBar, final Activity activity, final LoadCollectionResultListener<Photo> resultListener);
    void GetPhoto(String photoId, final ProgressBar progressBar, final Activity activity, final LoadItemResultListener<Photo> resultListener);
    void DeletePhoto(String photoId, final ProgressBar progressBar, final Activity activity, final RequestResultListener resultListener);
}
