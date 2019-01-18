package com.avorobyev.poorchild.Parent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avorobyev.poorchild.Adapters.ParentListOfTasksAdapter;
import com.avorobyev.poorchild.Model.Work;
import com.avorobyev.poorchild.R;

import java.util.ArrayList;

public class ListOfTasksActivity extends AppCompatActivity {

    protected RecyclerView listOfTasksRecyclerView;
    private RecyclerView.Adapter listOfTasksAdapter;
    private RecyclerView.LayoutManager listOfTasksLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_list_of_tasks);

        listOfTasksRecyclerView = findViewById(R.id.listOfTasksRecyclerView);
        listOfTasksLayoutManager = new LinearLayoutManager(this);
        listOfTasksRecyclerView.setLayoutManager(listOfTasksLayoutManager);

        DisplayTasks();
    }

    protected void DisplayTasks() {
    }
}
