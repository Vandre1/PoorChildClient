package com.avorobyev.poorchild.Parent;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.Networking.LoadItemResultListener;
import com.avorobyev.poorchild.R;

public class ViewTaskActivity extends BaseActivity {

    public static String VIEW_TASK_ID = "VIEW_TASK_ID";

    private String taskId;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_view_task);

        this.progressBar = this.findViewById(R.id.progressBar);

        // Получаем id задачи которую необходимо отобразить.
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null && bundle.containsKey(VIEW_TASK_ID)) {
            taskId = bundle.getString(VIEW_TASK_ID);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.MainRepository.GetTaskScheduleByTask(this.taskId, this.progressBar, this, new LoadItemResultListener<TaskSchedule>() {
            @Override
            public void LoadSuccess(TaskSchedule item) {
                Log.i("LOAD_TASK_TO_VIEW", "Loaded.");

                ViewTaskActivity.this.setTitle(item.Name);
                TextView descriptionTextView = findViewById(R.id.taskScheduleDescriptionTextView);
                TextView statusTextView = findViewById(R.id.statusTextView);

                descriptionTextView.setText(item.Description);
            }

            @Override
            public void LoadError(Exception exception) {
                Log.e("LOAD_TASK_TO_VIEW", exception.toString());

                // Выводим диалог с ошибкой
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось получить данные о задание. Возможно нет доступа в интернет.");
                errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            }

            @Override
            public void LoadCompleted() {

            }
        });
    }
}
