package com.avorobyev.poorchild.Child;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import com.avorobyev.poorchild.R;

public class WorkDetailsActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_work_details_activity);

        progressBar = findViewById(R.id.progressBar);

        this.DisplayWork();
    }

    protected void DisplayWork() {
    }
}
