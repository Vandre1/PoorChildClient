package com.avorobyev.poorchild.Parent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.Child.ViewDeviceCodeForAddToParentActivity;
import com.avorobyev.poorchild.ErrorDialogFragment;
import com.avorobyev.poorchild.MainActivity;
import com.avorobyev.poorchild.Networking.RequestResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

public class AddChildDeviceActivity extends BaseActivity {

    ProgressBar progressBar;
    EditText registrationCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_add_child_device);

        progressBar = findViewById(R.id.progressBar);
        registrationCodeEditText = findViewById(R.id.registrationCodeEditText);

        Hawk.init(this).build();
    }


    /**
     * Отправляем код регистрации на сервер, чтобы привязать устройство ребенка к родителю,
     * после чего переходим на экран списка детей
     * @param sender
     */
    public void AddChildrenToParentClicked(View sender) {
        String registrationCode = this.registrationCodeEditText.getText().toString().trim();
        String parentId = Hawk.get(PreferenceHelper.ParentId);

        this.MainRepository.AddChildren(parentId, registrationCode, this.progressBar, this, new RequestResultListener() {
            @Override
            public void RequestSuccess() {
                Log.i("ADD_CHILD_TO_PARENT", "Children added to parent.");

                Intent viewListOfChildsIntent = new Intent(AddChildDeviceActivity.this, ListOfChildsActivity.class);
                startActivity(viewListOfChildsIntent);
            }

            @Override
            public void RequestNotFound() {
                Log.i("ADD_CHILD_TO_PARENT", "Registration code not found.");

                // Выводим диалог с сообщением о неверном коде регистрации
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Указанный вами код не найден. Пожалуйста, проверьте его еще раз или получите новый на устройстве ребенка.");
                errorDialogFragment.show(getSupportFragmentManager(), "NotFoundDialogFragment");
            }

            @Override
            public void RequestError(Exception exception) {
                Log.i("ADD_CHILD_TO_PARENT", exception.toString());

                // Выводим диалог с ошибкой
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось добавить устройство. Возможно нет доступа в интернет.");
                errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            }

            @Override
            public void RequestCompleted() {
            }
        });
    }
}
