package com.avorobyev.poorchild.Parent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avorobyev.poorchild.Adapters.ParentListOfTasksAdapter;
import com.avorobyev.poorchild.Adapters.ParentListOfTasksSchedulesAdapter;
import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.Networking.LoadCollectionResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class ListOfTasksSchedulesActivity extends BaseActivity {

    private RecyclerView listOfTasksSchedulesRecyclerView;
    private RecyclerView.Adapter listOfTasksSchedulesAdapter;
    private RecyclerView.LayoutManager listOfTasksSchedulesLayoutManager;
    private ProgressBar progressBar;
    private Button addTaskScheduleEmptyDataButton;
    private TextView addTaskScheduleEmptyDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_list_of_tasks_schedules);

        this.addTaskScheduleEmptyDataButton = findViewById(R.id.addTaskScheduleEmptyDataButton);
        this.addTaskScheduleEmptyDataTextView = findViewById(R.id.addTaskScheduleEmptyDataTextView);
        this.listOfTasksSchedulesRecyclerView = findViewById(R.id.listOfTasksSchedulesRecyclerView);
        this.listOfTasksSchedulesLayoutManager = new LinearLayoutManager(this);
        this.listOfTasksSchedulesRecyclerView.setLayoutManager(this.listOfTasksSchedulesLayoutManager);

        progressBar = findViewById(R.id.progressBar);

        Hawk.init(this).build();
    }

    public void AddTaskSchedulesEmptyDataButtonClicked(View sender) {
        Intent inputIntent = new Intent(this, com.avorobyev.poorchild.Parent.AddTaskActivity.class);
        this.startActivity(inputIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.DisplayTasksSchedules();
    }

    protected void DisplayTasksSchedules() {
        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        this.MainRepository.GetTaskSchedules(Hawk.get(PreferenceHelper.ParentDeviceId).toString(), progressBar, this, new LoadCollectionResultListener<TaskSchedule>() {
            @Override
            public void LoadSuccess(ArrayList<TaskSchedule> items) {

                if (items.isEmpty()) {
                    // Если не скрывать этот элемент, то из за особенностей верстки он перекрывает кнопку addChildEmptyDataButton и не дает на нее нажимать
                    listOfTasksSchedulesRecyclerView.setVisibility(View.GONE);

                    addTaskScheduleEmptyDataButton.setVisibility(View.VISIBLE);
                    addTaskScheduleEmptyDataTextView.setVisibility(View.VISIBLE);
                }
                else {
                    // Элемент мог быть скрыт в случае если у пользователя в этом экземпляре активити ранее не было ни одного ребенка
                    listOfTasksSchedulesRecyclerView.setVisibility(View.VISIBLE);

                    listOfTasksSchedulesAdapter = new ParentListOfTasksSchedulesAdapter(items, ListOfTasksSchedulesActivity.this.getApplicationContext());
                    listOfTasksSchedulesRecyclerView.setAdapter(listOfTasksSchedulesAdapter);
                }
            }

            @Override
            public void LoadError(Exception exception) {
                // Выводим диалог с ошибкой
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Возможно нет доступа в интернет.");
                errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            }

            @Override
            public void LoadCompleted() {
            }
        });
    }

    public void ListOfTaskSchedulesImageButtonClicked(View sender) {
        Intent inputIntent = new Intent(this, com.avorobyev.poorchild.Parent.ListOfTasksSchedulesActivity.class);
        startActivity(inputIntent);
    }

    public void AddChildImageButtonClicked(View sender) {
        Intent inputIntent = new Intent(this, com.avorobyev.poorchild.Parent.AddChildDeviceActivity.class);
        startActivity(inputIntent);
    }

    public void AddTaskImageButtonClicked(View sender) {
        Intent inputIntent = new Intent(this, com.avorobyev.poorchild.Parent.AddTaskActivity.class);
        startActivity(inputIntent);
    }

    public void ListOfChildsImageButtonClicked(View sender) {
        Intent inputIntent = new Intent(this, ListOfChildsActivity.class);
        startActivity(inputIntent);
    }
}
