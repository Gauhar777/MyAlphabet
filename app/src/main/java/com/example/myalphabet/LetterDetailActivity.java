package com.example.myalphabet;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
public class LetterDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BUTTON_ID = "id";
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    NestedScrollView bottomSheet;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_detail);
        Intent mIntent=getIntent();

        int letter_id=((int)mIntent.getExtras().get(EXTRA_BUTTON_ID));
//*********************************************************bottom sheet first descriptions***************************************************
        bottomSheet=(NestedScrollView)findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Button btn=(Button)findViewById(R.id.arrowBtn);
                if (i==BottomSheetBehavior.STATE_COLLAPSED){
                    btn.setRotation(0);
                }else if (i==BottomSheetBehavior.STATE_EXPANDED){
                    btn.setRotation(180);
                }
            }
            @Override
            public void onSlide(@NonNull View view, float v) {
            }

        });
        bottomSheetBehavior.setPeekHeight(150);
//*******************************************************cards pages******************************************************************************
        final ViewPager pager=(ViewPager)findViewById(R.id.pager2);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        int id=(letter_id-1);
        pager.setCurrentItem(id);

//*******************************************************examples in bottomsheet pagers************************************************************
        goPreviousPgeTollbar();
//*******************************************************When cards scrolled examples list scrolled too*********************************************

//*******************************************************Next/previous buttons*****************************************
        ImageButton btnNext=(ImageButton)findViewById(R.id.next_page);
        ImageButton btnPrev=(ImageButton)findViewById(R.id.prev_page);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curentItm=pager.getCurrentItem();
                pager.setCurrentItem(curentItm+1, true);
//                exPager.setCurrentItem(curentItm+1,true);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pager.getCurrentItem()>1) {
                    int curentItm = pager.getCurrentItem();
                    pager.setCurrentItem(curentItm - 1, true);
//                    exPager.setCurrentItem(curentItm - 1, true);
                }
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
            }
            @Override
            public void onPageScrollStateChanged(int i) {
                if (bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                ListView ld=(ListView)findViewById(R.id.data_list);
                dBaseAdapter adapter=new dBaseAdapter(getBaseContext(),generateItems());
                ld.setAdapter(adapter);
            }
        });
//******************************************************Example list****************************************************
        ListView ld=(ListView)findViewById(R.id.data_list);
        dBaseAdapter adapter=new dBaseAdapter(this,generateItems());
        ld.setAdapter(adapter);
    }
    private ArrayList<ExampleListItemDTO> generateItems (){
        ViewPager pager=(ViewPager)findViewById(R.id.pager2);
        int letter=pager.getCurrentItem()+1;
        ArrayList<ExampleListItemDTO> list=new ArrayList<>();
        setupDBHelper();
        final Cursor cursorExample=mDb.rawQuery("SELECT * FROM example WHERE letter_id="+letter,null);
        cursorExample.moveToFirst();
        try {
        while (!cursorExample.isAfterLast()){
            int example_id=cursorExample.getInt(0);
            String ex_word=cursorExample.getString(1);
            String ex_transc=cursorExample.getString(3);

            Cursor cursorVoice=mDb.rawQuery("SELECT * FROM voice WHERE example_id="+example_id,null);
            cursorVoice.moveToFirst();
            String ex_w_voice=cursorVoice.getString(2);
            String ex_m_voice=cursorVoice.getString(1);
            list.add(new ExampleListItemDTO(ex_word,ex_transc,ex_w_voice,ex_m_voice));
            cursorExample.moveToNext();
        }
        }catch (Exception e){

        }
        cursorExample.close();
        return list;
    }
    public void onArrowClick(View view){
        bottomSheet=(NestedScrollView)findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheet);
        if (bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_COLLAPSED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }else{
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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