package com.avorobyev.poorchild.Child;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.avorobyev.poorchild.Adapters.ChildListOfWorksAdapter;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.Model.Work;
import com.avorobyev.poorchild.Networking.LoadWorksResultListener;
import com.avorobyev.poorchild.Networking.ServerCommunication;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListOfWorksActivity extends AppCompatActivity {

    protected RecyclerView listOfWorksRecyclerView;
    private RecyclerView.Adapter listOfWorksAdapter;
    private RecyclerView.LayoutManager listOfWorksLayoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity_list_of_works);

        listOfWorksRecyclerView = findViewById(R.id.listOfWorksRecyclerView);
        listOfWorksLayoutManager = new LinearLayoutManager(this);
        listOfWorksRecyclerView.setLayoutManager(listOfWorksLayoutManager);

        progressBar = findViewById(R.id.progressBar);

        DisplayWorks();
    }

    protected void DisplayWorks() {
        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ServerCommunication.LoadWorks(this, Hawk.get(PreferenceHelper.ChildDeviceId).toString(), progressBar, new LoadWorksResultListener() {
            @Override
            public void LoadWorksSuccess(ArrayList<Work> works) {
                listOfWorksAdapter = new ChildListOfWorksAdapter(works);
                listOfWorksRecyclerView.setAdapter(listOfWorksAdapter);
            }

            @Override
            public void LoadWorksError(Exception exception) {
                // Выводим диалог с ошибкой
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Возможно нет доступа в интернет.");
                errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            }

            @Override
            public void LoadWorksCompleted() {
                // Скрываем ProgressBar
                progressBar.setVisibility(View.INVISIBLE);
                ListOfWorksActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });



    }
}
