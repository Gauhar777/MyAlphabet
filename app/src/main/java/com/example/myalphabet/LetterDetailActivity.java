package com.example.myalphabet;
import android.content.Intent;
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
import android.widget.Toast;
public class LetterDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BUTTON_ID = "id";
    NestedScrollView bottomSheet;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_detail);
        Intent mIntent=getIntent();
        int letter_id=(int)mIntent.getExtras().get(EXTRA_BUTTON_ID);
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
        pager.setCurrentItem(letter_id-1);
//*******************************************************examples in bottomsheet pagers************************************************************
        int curentItm=pager.getCurrentItem();
        final ViewPager exPager=(ViewPager) findViewById(R.id.ex_pager);
        exPager.setAdapter(new ExamplesAdapter(getSupportFragmentManager()));
        exPager.setCurrentItem(curentItm,true);
        goPreviousPgeTollbar();
//*******************************************************When cards scrolled examples list scrolled too*********************************************
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Toast.makeText(LetterDetailActivity.this,i+"*",Toast.LENGTH_SHORT).show();
                exPager.setCurrentItem(i,true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        Button btnNext=(Button)findViewById(R.id.next_page);
        Button btnPrev=(Button)findViewById(R.id.prev_page);
//*******************************************************Next/previous buttons*****************************************
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curentItm=pager.getCurrentItem();
                pager.setCurrentItem(curentItm+1, true);
                exPager.setCurrentItem(curentItm+1,true);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curentItm=pager.getCurrentItem();
                pager.setCurrentItem(curentItm-1, true);
                exPager.setCurrentItem(curentItm-1,true);
            }
        });

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