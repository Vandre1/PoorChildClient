package com.avorobyev.poorchild.Child;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.avorobyev.poorchild.ActionDialogFragment;
import com.avorobyev.poorchild.ActionDialogResultListener;
import com.avorobyev.poorchild.PreferenceHelper;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

public class ViewDeviceCodeForAddToParentActivity extends AppCompatActivity implements ActionDialogResultListener {

    private TextView codeToAddDeviceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_view_device_code_for_add_to_parent);

        codeToAddDeviceTextView = findViewById(R.id.codeToAddDeviceTextView);
        codeToAddDeviceTextView.setText(Hawk.get(PreferenceHelper.ChildDeviceKey).toString());
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
