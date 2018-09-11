package com.avorobyev.poorchild.Parent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avorobyev.poorchild.Adapters.ParentListOfWorksAdapter;
import com.avorobyev.poorchild.Model.Work;
import com.avorobyev.poorchild.R;

import java.util.ArrayList;

public class ListOfWorksActivity extends AppCompatActivity {

    protected RecyclerView listOfWorksRecyclerView;
    private RecyclerView.Adapter listOfWorksAdapter;
    private RecyclerView.LayoutManager listOfWorksLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_list_of_works);

        listOfWorksRecyclerView = findViewById(R.id.listOfWorksRecyclerView);
        listOfWorksLayoutManager = new LinearLayoutManager(this);
        listOfWorksRecyclerView.setLayoutManager(listOfWorksLayoutManager);

        DisplayWorks();
    }

    protected void DisplayWorks() {
        ArrayList<com.avorobyev.poorchild.Model.Work> works = new ArrayList<>();
        works.add(new Work("Помыть посуду", "Хорошо помыть посуду"));
        works.add(new Work("Подмести пол", "Перед этим обязательно подмести"));

        listOfWorksAdapter = new ParentListOfWorksAdapter(works);
        listOfWorksRecyclerView.setAdapter(listOfWorksAdapter);
    }
}
