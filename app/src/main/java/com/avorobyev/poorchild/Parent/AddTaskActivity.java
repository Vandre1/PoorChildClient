package com.avorobyev.poorchild.Parent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.Networking.LoadItemResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

public class AddTaskActivity extends BaseActivity {

    ProgressBar progressBar;
    EditText workNameEditText;
    EditText workDescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_add_task);

        Hawk.init(this).build();

        this.progressBar = this.findViewById(R.id.progressBar);
        this.workNameEditText = this.findViewById(R.id.workNameEditText);
        this.workDescriptionEditText = this.findViewById(R.id.workDescriptionEditText);
    }

    public void AddTaskToChildrenClicked(View sender) {
        String taskName = this.workNameEditText.getText().toString().trim();
        String taskDescription = this.workDescriptionEditText.getText().toString().trim();
        String parentId = Hawk.get(PreferenceHelper.ParentDeviceId);
        Integer timeToStart = -1;
        Integer timeToEnd = -1;

        if (taskName.length() == 0) {
            this.workNameEditText.setError("Необходимо указать название задачи");
            return;
        }

        TaskSchedule taskScheduleToCreate = new TaskSchedule(taskName, taskDescription, timeToStart, timeToEnd);

        this.MainRepository.CreateTaskSchedule(null, parentId, this.progressBar, this, new LoadItemResultListener<TaskSchedule>() {
            @Override
            public void LoadSuccess(TaskSchedule item) {

            }

            @Override
            public void LoadError(Exception exception) {

            }

            @Override
            public void LoadCompleted() {

            }
        });
    }
}
