package com.avorobyev.poorchild.Parent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import com.avorobyev.poorchild.Adapters.ListOfChildsAdapter;
import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.MainActivity;
import com.avorobyev.poorchild.Networking.LoadCollectionResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class ListOfChildsActivity extends BaseActivity {

    private RecyclerView listOfChildsRecyclerView;
    private RecyclerView.Adapter listOfChildsAdapter;
    private RecyclerView.LayoutManager listOfChildsLayoutManager;
    private ProgressBar progressBar;
    private Button addChildEmptyDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_list_of_childs);

        addChildEmptyDataButton = findViewById(R.id.addChildEmptyDataButton);
        listOfChildsRecyclerView = findViewById(R.id.listOfChildsRecyclerView);
        listOfChildsLayoutManager = new LinearLayoutManager(this);
        listOfChildsRecyclerView.setLayoutManager(listOfChildsLayoutManager);

        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DisplayChilds();
    }

    public void AddChildEmptyDataButtonClicked(View sender) {
        Intent inputIntent = new Intent(ListOfChildsActivity.this, com.avorobyev.poorchild.Parent.AddChildDeviceActivity.class);
        startActivity(inputIntent);
    }

    protected void DisplayChilds() {
        // Перед отправкой на сервер отображаем ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        this.MainRepository.GetChildrens(Hawk.get(PreferenceHelper.ParentDeviceId).toString(), progressBar, this, new LoadCollectionResultListener<Children>() {
            @Override
            public void LoadSuccess(ArrayList<Children> items) {
                if (items.isEmpty()) {
                    // Если не скрывать этот элемент, то из за особенностей верстки он перекрывает кнопку addChildEmptyDataButton и не дает на нее нажимать
                    listOfChildsRecyclerView.setVisibility(View.GONE);

                    addChildEmptyDataButton.setVisibility(View.VISIBLE);
                }
                else {
                    // Элемент мог быть скрыт в случае если у пользователя в этом экземпляре активити ранее не было ни одного ребенка
                    listOfChildsRecyclerView.setVisibility(View.VISIBLE);

                    listOfChildsAdapter = new ListOfChildsAdapter(items, ListOfChildsActivity.this.getApplicationContext());
                    listOfChildsRecyclerView.setAdapter(listOfChildsAdapter);
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
