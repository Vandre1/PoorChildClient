package com.avorobyev.poorchild.Child;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avorobyev.poorchild.ActionDialogFragment;
import com.avorobyev.poorchild.ActionDialogResultListener;
import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.Networking.LoadItemResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

public class ViewDeviceCodeForAddToParentActivity extends BaseActivity implements ActionDialogResultListener {

    private TextView codeToAddDeviceTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_view_device_code_for_add_to_parent);

        this.progressBar = this.findViewById(R.id.progressBar);
        this.codeToAddDeviceTextView = findViewById(R.id.codeToAddDeviceTextView);

        String childrenId = Hawk.get(PreferenceHelper.ChildDeviceId).toString();

        this.MainRepository.GetdChildrenCode(childrenId, this.progressBar, this, new LoadItemResultListener<String>() {
            @Override
            public void LoadSuccess(String code) {
                codeToAddDeviceTextView.setText(code);
            }

            @Override
            public void LoadError(Exception exception) {
                Log.e("GET_CHILD_CODE", exception.toString());

                // Выводим диалог с ошибкой
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не получить код добавления устройства ребенка. Возможно нет доступа в интернет.");
                errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            }

            @Override
            public void LoadCompleted() {
            }
        });

    }

    public void readyButtonClicked(View v) {
        ActionDialogFragment actionDialogFragment = ActionDialogFragment.newInstance("Вы запомнили код?"
                , "Подтвердите, что вы запомнили код для добавления устройства в список детских устройств. Если нет, вы всегда сможете посмотреть его в Меню.");
        actionDialogFragment.show(getSupportFragmentManager(), "ActionDialogFragment");
    }

    @Override
    public void ActionDialogResult(int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            Intent listOfTasksActivity = new Intent(this, ListOfTasksActivity.class);
            startActivity(listOfTasksActivity);
        } else if (resultCode == Activity.RESULT_CANCELED){
        }
    }
}
