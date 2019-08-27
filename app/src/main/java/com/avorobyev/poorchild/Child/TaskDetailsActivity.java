package com.avorobyev.poorchild.Child;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.R;
import com.orhanobut.hawk.Hawk;

public class TaskDetailsActivity extends BaseActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_task_details_activity);

        progressBar = findViewById(R.id.progressBar);

        Hawk.init(this).build();

        this.DisplayTask();

        // chat realization example
        // https://www.scaledrone.com/blog/android-chat-tutorial/
    }

    protected void DisplayTask() {
    }
}
