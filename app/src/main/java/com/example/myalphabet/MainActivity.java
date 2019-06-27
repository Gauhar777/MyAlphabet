package com.example.myalphabet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timer timer;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,AlphabetActivity.class);
                startActivity(intent);
                finish();
            }
        },10000);
    }
}
