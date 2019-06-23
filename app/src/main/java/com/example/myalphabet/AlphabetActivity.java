package com.example.myalphabet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.io.IOException;

public class AlphabetActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        buttonInGridLayout();
        goPreviousPgeTollbar();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
//***************************************Every button on calculator alphabeth*******************************
    private void buttonInGridLayout(){
        GridLayout grl=(GridLayout)findViewById(R.id.grid);
            setupDBHelper();
            Cursor cursorLetter=mDb.rawQuery("SELECT * FROM letter",null);
            cursorLetter.moveToFirst();
            String text;
            Button btnLetter;
            while (!cursorLetter.isAfterLast()) {
                text = cursorLetter.getString(1);
                btnLetter=new Button(this);
                String i = cursorLetter.getString(0);
                btnLetter.setId(Integer.parseInt(i));
                btnLetter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id=v.getId();
                        Toast.makeText(AlphabetActivity.this,id+">>>"+"*",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AlphabetActivity.this,LetterDetailActivity.class);
                        intent.putExtra(LetterDetailActivity.EXTRA_BUTTON_ID,id);
                        startActivity(intent);
                    }
                });
                btnLetter.setText(text);
                String type=cursorLetter.getString(3);
                //Log.d("myTag","*********  "+type+"  **************");
                if (type.equals("ds")){
                    btnLetter.setTextColor(getResources().getColor(R.color.mainWhite));
                    btnLetter.setBackgroundResource(R.drawable.ds_letter_button_back);
                }else {
                    btnLetter.setTextColor(getResources().getColor(R.color.mainGray));
                    btnLetter.setBackgroundResource(R.drawable.dt_letter_button_back);
                }

                cursorLetter.moveToNext();
            btnLetter.setTransformationMethod(null);
            btnLetter.setHeight(350);
            btnLetter.setWidth(350);
            btnLetter.setTextSize(50);
            btnLetter.setPadding(50,50,50,50);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.setMargins(2, 2,2, 2);
            layoutParams.setGravity(Gravity.CENTER);
            grl.addView(btnLetter, layoutParams);
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
