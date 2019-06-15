package com.example.myalphabet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ExrActivity extends AppCompatActivity implements ExrListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exr);
        goPreviousPgeTollbar();
    }

    public void itemCategoryClicked(long id) {
        Intent intent=new Intent(this,OneTaskActivity.class);
        intent.putExtra(OneTaskActivity.EXTRA_TASK_ID, (int) id);
        startActivity(intent);
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
