package com.example.myalphabet;


import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LetterDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BUTTON_ID = "id";
    NestedScrollView bottomSheet;
    private MediaPlayer mediaPlayer;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("selectedLetter","*-*-*-*-*-*-*-*-*-*"+);
        setContentView(R.layout.activity_letter_detail);
        Intent mIntent=getIntent();
        int letter_id=(int)mIntent.getExtras().get(EXTRA_BUTTON_ID);

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
        ViewPager pager=(ViewPager)findViewById(R.id.pager2);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(letter_id-1);
        goPreviousPgeTollbar();
        //long idLetter=(int)getIntent().getExtras().get(EXTRA_BUTTON_ID);
        Log.d("gh","*+*+*+*+*+*"+letter_id);
        //ExampleWordFragment wordFragment=(ExampleWordFragment)getSupportFragmentManager().findFragmentById(R.id.example_list);
        //wordFragment.setLetterId(letter_id);
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
//    public void onWomanVoiceClick(View view){
//        mediaPlayer = MediaPlayer.create(this, R.raw.dua_lipa);
//        mediaPlayer.start();
//    }
//    public void onManVoiceClick(View view ){
//        mediaPlayer.pause();
//    }
    public void goPreviousPgeTollbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}