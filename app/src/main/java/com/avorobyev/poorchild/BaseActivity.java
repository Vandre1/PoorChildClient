package com.avorobyev.poorchild;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.avorobyev.poorchild.Parent.ListOfChildsActivity;
import com.avorobyev.poorchild.Repository.IRepository;
import com.avorobyev.poorchild.Repository.LocalDataRepository;

public class BaseActivity extends AppCompatActivity {

    // Основной репозиторий для работы с приложением
    public IRepository MainRepository = new LocalDataRepository();
}
