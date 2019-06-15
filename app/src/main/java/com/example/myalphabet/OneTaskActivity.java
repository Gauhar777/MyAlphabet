package com.example.myalphabet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OneTaskActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    public static final String EXTRA_TASK_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_task);
        //TextView tv=(TextView)findViewById(R.id.id_task);
//        int idC=(int)getIntent().getExtras().get(EXTRA_TASK_ID);
        String idTask="***"+getIntent().getExtras().get(EXTRA_TASK_ID).toString()+"***";
        //int idTask=(int) getIntent().getExtras().get(EXTRA_TASK_ID);
        //tv.setText(idTask);
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

    @Override
    protected void onStart() {
        super.onStart();
        setupDBHelper();
        int idTask=(int)getIntent().getExtras().get(EXTRA_TASK_ID);
        Cursor cursor=mDb.rawQuery("SELECT * FROM sentence WHERE task_id="+idTask,null);
        cursor.moveToFirst();
        TextView kirSentence=(TextView)findViewById(R.id.kir_sentence);
        TextView latSentence=(TextView)findViewById(R.id.lat_sentence);
        kirSentence.setText(cursor.getString(1));
        latSentence.setText(cursor.getString(2));
        cursor.close();
    }

    private void setupDBHelper(){
        Context context=this;
        mDBHelper=new DBHelper(context);
        try {
            mDBHelper.updateDataBase();
        }catch (IOException mIOException){
            throw new Error("UnableToUpdate");
        }

        try {
            mDb=mDBHelper.getWritableDatabase();
        }catch (SQLException mSQLException){
            throw mSQLException;
        }
    }

}
