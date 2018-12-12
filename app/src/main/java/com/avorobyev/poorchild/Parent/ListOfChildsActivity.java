package com.avorobyev.poorchild.Parent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avorobyev.poorchild.BaseActivity;
import com.avorobyev.poorchild.R;

public class ListOfChildsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_activity_list_of_childs);
    }
}
