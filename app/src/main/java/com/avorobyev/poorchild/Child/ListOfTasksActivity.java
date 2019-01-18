package com.avorobyev.poorchild.Child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.avorobyev.poorchild.Adapters.ChildListOfTasksAdapter;
import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.Networking.LoadCollectionResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class ListOfTasksActivity extends BaseActivity {

    protected RecyclerView listOfTasksRecyclerView;
    private RecyclerView.Adapter listOfTasksAdapter;
    private RecyclerView.LayoutManager listOfTasksLayoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity_list_of_tasks);

        listOfTasksRecyclerView = findViewById(R.id.listOfTasksRecyclerView);
        listOfTasksLayoutManager = new LinearLayoutManager(this);
        listOfTasksRecyclerView.setLayoutManager(listOfTasksLayoutManager);

        progressBar = findViewById(R.id.progressBar);

        DisplayTasks();
    }

    protected void DisplayTasks() {
        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        this.MainRepository.GetChildrenTasks(Hawk.get(PreferenceHelper.ChildDeviceId).toString(), progressBar, this, new LoadCollectionResultListener<Task>() {
            @Override
            public void LoadSuccess(ArrayList<Task> items) {
                listOfTasksAdapter = new ChildListOfTasksAdapter(items);
                listOfTasksRecyclerView.setAdapter(listOfTasksAdapter);
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
