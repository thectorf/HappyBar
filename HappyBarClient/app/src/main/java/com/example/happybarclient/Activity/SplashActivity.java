package com.example.happybarclient.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.happybarclient.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask = new TimerTask() {                                                         //se crea un TimerTask que se ejecuta despues del Timer (abajo declarado)
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginRegisterActivity.class);      //se indica que el MainActivity se abrira
                startActivity(intent);                                                                  //se inicia el MainActivity
                finish();                                                                               //se mata o finaliza el LoginRegisterActivity para que no ocupe memoria
            }
        };

        Timer timer = new Timer();                                                                      //se crea Timer
        timer.schedule(timerTask, 700);

    }
}
