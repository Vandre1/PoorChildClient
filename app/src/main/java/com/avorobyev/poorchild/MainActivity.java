package com.avorobyev.poorchild;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.avorobyev.poorchild.Child.ViewDeviceCodeForAddToParentActivity;
import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Dao.Parent;
import com.avorobyev.poorchild.Networking.LoadItemResultListener;
import com.avorobyev.poorchild.Parent.AddChildDeviceActivity;
import com.orhanobut.hawk.Hawk;

public class MainActivity extends BaseActivity {

    ProgressBar registerParentProgressBar;
    ProgressBar registerChildProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerChildProgressBar = findViewById(R.id.registerChildProgressBar);
        registerParentProgressBar = findViewById(R.id.registerParentProgressBar);

        Hawk.init(this).build();

        // Intent bindIntent = new Intent(this, LockScreenService.class);
        // startService(bindIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void ThisIsChildDeviceClicked(View sender) {
        Children children = new Children("Василий", "Дубров");

        this.MainRepository.CreateChildren(children, this.registerChildProgressBar, this, new LoadItemResultListener<Children>() {
            @Override
            public void LoadSuccess(Children item) {
                Log.i("REG_CHILD_DEVICE", "Registered.");

                // Запоминаем Id записи регистрации устройства, а так же указываем, что пользователь зарегестрировался как ChildDevice
                Hawk.put(PreferenceHelper.ChildDeviceId, item.Id);
                Hawk.put(PreferenceHelper.ChildDeviceKey, item.RegistrationCode);
                Hawk.put(PreferenceHelper.IsCurrentDeviceChild, true);
                Hawk.put(PreferenceHelper.IsCurrentDeviceParent, false);

                Intent viewDeviceCodeForAddToParentIntent = new Intent(MainActivity.this, ViewDeviceCodeForAddToParentActivity.class);
                startActivity(viewDeviceCodeForAddToParentIntent);
            }

            @Override
            public void LoadError(Exception exception) {
                Log.e("REG_CHILD_DEVICE", exception.toString());

                // Выводим диалог с ошибкой
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось добавить устройство. Возможно нет доступа в интернет.");
                errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            }

            @Override
            public void LoadCompleted() {
            }
        });
    }

    public void ThisIsParentDeviceClicked(View sender) {
        Parent parent = new Parent("Марина", "Дуброва");

        this.MainRepository.CreateParent(parent, this.registerParentProgressBar, this, new LoadItemResultListener<Parent>() {
            @Override
            public void LoadSuccess(Parent item) {
                Log.i("REG_PARENT_DEVICE", "Registered.");

                Hawk.put(PreferenceHelper.ParentId, item.Id);
                Hawk.put(PreferenceHelper.IsCurrentDeviceChild, false);
                Hawk.put(PreferenceHelper.IsCurrentDeviceParent, true);

                Intent inputCodeIntent = new Intent(MainActivity.this, AddChildDeviceActivity.class);
                startActivity(inputCodeIntent);
            }

            @Override
            public void LoadError(Exception exception) {
                Log.e("REG_PARENT_DEVICE", exception.toString());

                // Выводим диалог с ошибкой
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Ошибка", "Не удалось добавить устройство. Возможно нет доступа в интернет.");
                errorDialogFragment.show(getSupportFragmentManager(), "ErrorDialogFragment");
            }

            @Override
            public void LoadCompleted() {
            }
        });
    }
}
