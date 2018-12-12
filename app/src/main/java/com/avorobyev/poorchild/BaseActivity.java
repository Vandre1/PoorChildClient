package com.avorobyev.poorchild;

import android.support.v7.app.AppCompatActivity;

import com.avorobyev.poorchild.Repository.IRepository;
import com.avorobyev.poorchild.Repository.LocalDataRepository;

public class BaseActivity extends AppCompatActivity {

    // Основной репозиторий для работы с приложением
    public IRepository MainRepository = new LocalDataRepository();
}
