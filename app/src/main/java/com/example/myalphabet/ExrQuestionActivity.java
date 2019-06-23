package com.example.myalphabet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.io.IOException;

public class ExrQuestionActivity extends AppCompatActivity {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exr_question);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid_3);
        final GridLayout gridLayoutlat=(GridLayout)findViewById(R.id.grid_3_lat);
        setupDBHelper();
        Cursor cursor=mDb.rawQuery("SELECT * FROM task WHERE id="+3,null);
        cursor.moveToFirst();
        TextView taskText=(TextView)findViewById(R.id.task_3);
        taskText.setText(cursor.getString(1));

        Cursor cursorOneTask=mDb.rawQuery("SELECT * FROM sentence WHERE task_id="+3,null);
        cursorOneTask.moveToFirst();
        TextView kirSentence=(TextView)findViewById(R.id.kir_3);
        final TextView latSentence=(TextView)findViewById(R.id.lat_3);
        kirSentence.setText(cursorOneTask.getString(1));
        latSentence.setText(cursorOneTask.getString(2));
        cursor.close();
        cursorOneTask.close();
        latSentence.setVisibility(View.INVISIBLE);
        gridLayoutlat.setVisibility(View.INVISIBLE);
        Button btnRez=(Button)findViewById(R.id.rez_3);
        Cursor cursorAdSub=mDb.rawQuery("SELECT * FROM additionally_sentence WHERE sentence_id=3 ORDER BY id LIMIT 3",null);
        cursorAdSub.moveToFirst();
        while (!cursorAdSub.isAfterLast()) {
            String kir=cursorAdSub.getString(1);
            String lat=cursorAdSub.getString(2);
            Log.d("tag","*******"+kir+"********");
            Log.d("tag","*******"+lat+"********");
            TextView txtInGrid = new TextView(this);
            TextView txtInGridEmpt = new TextView(this);
            txtInGrid.setText(kir);

            TextView txtInGrid2=new TextView(this);
            txtInGrid2.setText(lat);
                gridLayout.addView(txtInGrid);
                gridLayout.addView(txtInGridEmpt);
                gridLayout.addView(txtInGrid2);
            //            gridLayoutlat.addView(txtInGrid2);

            cursorAdSub.moveToNext();
        }
        cursorAdSub.close();
        Cursor cursorAdSubLat=mDb.rawQuery("SELECT * FROM additionally_sentence WHERE sentence_id=3 AND  id>3",null);
        cursorAdSubLat.moveToFirst();
        while (!cursorAdSubLat.isAfterLast()) {
            String kir=cursorAdSubLat.getString(1);
            String lat=cursorAdSubLat.getString(2);
            Log.d("tag","*******"+kir+"********");
            Log.d("tag","*******"+lat+"********");
            TextView txtInGrid = new TextView(this);
            TextView txtInGridEmpt = new TextView(this);
            txtInGrid.setText(kir);

            TextView txtInGrid2=new TextView(this);
            txtInGrid2.setText(lat);
            gridLayoutlat.addView(txtInGrid);
            gridLayoutlat.addView(txtInGridEmpt);
            gridLayoutlat.addView(txtInGrid2);
            cursorAdSubLat.moveToNext();
        }
        cursorAdSubLat.close();
        btnRez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latSentence.setVisibility(View.VISIBLE);
                gridLayoutlat.setVisibility(View.VISIBLE);
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
