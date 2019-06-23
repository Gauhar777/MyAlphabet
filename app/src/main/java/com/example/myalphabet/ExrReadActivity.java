package com.example.myalphabet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class ExrReadActivity extends AppCompatActivity {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exr_read);
        setupDBHelper();
        Cursor cursor=mDb.rawQuery("SELECT * FROM task WHERE id=2",null);
        cursor.moveToFirst();
        TextView taskText=(TextView)findViewById(R.id.task_2);
        taskText.setText(cursor.getString(1));

        Cursor cursorOneTask=mDb.rawQuery("SELECT * FROM sentence WHERE task_id="+2,null);
        cursorOneTask.moveToFirst();
        TextView kirSentence=(TextView)findViewById(R.id.kir_2);
        final TextView latSentence=(TextView)findViewById(R.id.lat_2);
        kirSentence.setText(cursorOneTask.getString(1));
        latSentence.setText(cursorOneTask.getString(2));
        cursor.close();
        cursorOneTask.close();
        latSentence.setVisibility(View.INVISIBLE);
        Button btnRez=(Button)findViewById(R.id.rez_2);
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
}
