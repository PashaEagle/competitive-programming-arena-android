package com.example.arena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onButtonLoginActivityClick(View v){

        Intent intent = new Intent(".LoginActivity");
        startActivity(intent);
    }

    public void onButtonRegisterActivityClick(View v){

        Intent intent = new Intent(".RegisterActivity");
        startActivity(intent);
    }
}
