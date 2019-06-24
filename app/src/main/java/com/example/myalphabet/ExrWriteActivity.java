package com.example.myalphabet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class ExrWriteActivity extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "id";
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exr_write);
        int idTask=(int) getIntent().getExtras().get(EXTRA_TASK_ID);
        setupDBHelper();
        Cursor cursor=mDb.rawQuery("SELECT * FROM task WHERE id="+idTask,null);
        cursor.moveToFirst();
        goPreviousPgeTollbar();
        TextView taskText=(TextView)findViewById(R.id.task);
        taskText.setText(cursor.getString(1));
        Cursor cursorOneTask=mDb.rawQuery("SELECT * FROM sentence WHERE task_id="+idTask,null);
        cursorOneTask.moveToFirst();
        TextView kirSentence=(TextView)findViewById(R.id.kir);
        final TextView latSentence=(TextView)findViewById(R.id.lat);
        kirSentence.setText(cursorOneTask.getString(1));
        latSentence.setText(cursorOneTask.getString(2));
        cursor.close();
        cursorOneTask.close();
        latSentence.setVisibility(View.INVISIBLE);
        Button btnRez=(Button)findViewById(R.id.rez);
        btnRez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latSentence.setVisibility(View.VISIBLE);
            }
        });
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
    public void goPreviousPgeTollbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
