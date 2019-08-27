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

import com.avorobyev.poorchild.Adapters.ListOfChildsAdapter;
import com.avorobyev.poorchild.Adapters.ParentListOfTasksAdapter;
import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.Networking.LoadCollectionResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.avorobyev.poorchild.Tasks.Filters.ChildsFilter;
import com.avorobyev.poorchild.Tasks.Filters.TaskSchedulesFilter;
import com.avorobyev.poorchild.Tasks.TasksScheduleManager;
import com.orhanobut.hawk.Hawk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfTasksActivity extends BaseActivity {

    public static String CHILDREN_ID = "CHILDREN_ID";

    private RecyclerView listOfTasksRecyclerView;
    private RecyclerView.Adapter listOfTasksAdapter;
    private RecyclerView.LayoutManager listOfTasksLayoutManager;
    private ProgressBar progressBar;
    private Button addTaskEmptyDataButton;
    private TextView addTaskEmptyDataTextView;

    private String childrenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_list_of_tasks);

        addTaskEmptyDataButton = findViewById(R.id.addTaskEmptyDataButton);
        addTaskEmptyDataTextView = findViewById(R.id.addTaskEmptyDataTextView);
        listOfTasksRecyclerView = findViewById(R.id.listOfTasksRecyclerView);
        listOfTasksLayoutManager = new LinearLayoutManager(this);
        listOfTasksRecyclerView.setLayoutManager(listOfTasksLayoutManager);

        progressBar = findViewById(R.id.progressBar);

        Bundle bundle = this.getIntent().getExtras();
        childrenId = bundle.getString(CHILDREN_ID);

        Hawk.init(this).build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.DisplayTasks();
    }

    public void AddTaskEmptyDataButtonClicked(View sender) {
        Intent inputIntent = new Intent(this, com.avorobyev.poorchild.Parent.AddTaskActivity.class);
        this.startActivity(inputIntent);
    }

    protected void DisplayTasks() {
        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        this.MainRepository.GetChildrenTasks(childrenId, progressBar, this, new LoadCollectionResultListener<Task>() {
            @Override
            public void LoadSuccess(ArrayList<Task> items) {

                if (items.isEmpty()) {
                    // Если не скрывать этот элемент, то из за особенностей верстки он перекрывает кнопку addChildEmptyDataButton и не дает на нее нажимать
                    listOfTasksRecyclerView.setVisibility(View.GONE);

                    addTaskEmptyDataButton.setVisibility(View.VISIBLE);
                    addTaskEmptyDataTextView.setVisibility(View.VISIBLE);
                }
                else {
                    // Элемент мог быть скрыт в случае если у пользователя в этом экземпляре активити ранее не было ни одного ребенка
                    listOfTasksRecyclerView.setVisibility(View.VISIBLE);

                    listOfTasksAdapter = new ParentListOfTasksAdapter(items, ListOfTasksActivity.this.getApplicationContext());
                    listOfTasksRecyclerView.setAdapter(listOfTasksAdapter);
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
}
