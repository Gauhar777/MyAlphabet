package com.example.myalphabet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ZhattygularActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhattygular);
        Button btn1=(Button)findViewById(R.id.btn_1);
        Button btn2=(Button)findViewById(R.id.btn_2);
        Button btn3=(Button)findViewById(R.id.btn_3);
        Button btn4=(Button)findViewById(R.id.btn_4);
        Button btn5=(Button)findViewById(R.id.btn_5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ZhattygularActivity.this,ExrWriteActivity.class);
                intent.putExtra(ExrWriteActivity.EXTRA_TASK_ID, 1);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ZhattygularActivity.this,ExrReadActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ZhattygularActivity.this,ExrQuestionActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ZhattygularActivity.this,ExrWriteActivity.class);
                intent.putExtra(ExrWriteActivity.EXTRA_TASK_ID, 4);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ZhattygularActivity.this,ExrWriteActivity.class);
                intent.putExtra(ExrWriteActivity.EXTRA_TASK_ID, 5);
                startActivity(intent);
            }
        });


        goPreviousPgeTollbar();
    }
    public void goPreviousPgeTollbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
