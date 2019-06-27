package com.example.myalphabet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.io.IOException;

public class AlphabetActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        buttonInGridLayout();
        rightSideMenuBar();
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
            btnLetter.setTextSize(40);
            btnLetter.setPadding(50,50,50,50);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.setMargins(2, 2,2, 2);
            layoutParams.setGravity(Gravity.CENTER);
            grl.addView(btnLetter, layoutParams);
            }
    }
    public  void rightSideMenuBar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dl = (DrawerLayout)findViewById(R.id.alpha_dr);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        t.syncState();
        dl.addDrawerListener(t);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nav_left);
//***************************************************клик на позицию в меню*****************************************************************
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.support)
                {
                    Intent intent2=new Intent(AlphabetActivity.this,ZhattygularActivity.class);
                    startActivity(intent2);
                    Toast.makeText(AlphabetActivity.this, "Zhattygular",Toast.LENGTH_SHORT).show();

                } else if (id == R.id.mycart)
                {
                    Intent intent=new Intent(AlphabetActivity.this,Main2Activity.class);
                    startActivity(intent);
                    Toast.makeText(AlphabetActivity.this, "Biz turaly",Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.aboutUs)
                {
                    Intent intent3=new Intent(AlphabetActivity.this,AlphabetActivity.class);
                    startActivity(intent3);
                    Toast.makeText(AlphabetActivity.this, "Basty bet",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(t.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
