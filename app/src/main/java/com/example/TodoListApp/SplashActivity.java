package com.example.TodoListApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

//    Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

//        btn_continue = (Button) findViewById(R.id.btn_splash_continue);
//
//        btn_continue.setOnClickListener(new view.);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                Boolean authentication = preferences.getBoolean("authentication", false);
                Intent intent;
                if(authentication){
                    intent = new Intent(SplashActivity.this, MainActivity.class
                    );
                }else{
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2500);
    }
}