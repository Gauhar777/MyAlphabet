package com.example.myalphabet;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        Toast.makeText(LetterDetailActivity.this,letter_id+">>>"+"*",Toast.LENGTH_SHORT).show();
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
        pager.setCurrentItem(letter_id);
//*******************************************************examples in bottomsheet pagers************************************************************
        int curentItm=pager.getCurrentItem();
//        final ViewPager exPager=(ViewPager) findViewById(R.id.ex_pager);
//        exPager.setAdapter(new ExamplesAdapter(getSupportFragmentManager()));
//        exPager.setCurrentItem(curentItm,true);
        goPreviousPgeTollbar();
//*******************************************************When cards scrolled examples list scrolled too*********************************************
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//            }
//            @Override
//            public void onPageSelected(int i) {
//                exPager.setCurrentItem(i,true);
//            }
//            @Override
//            public void onPageScrollStateChanged(int i) {
//            }
//        });

        ImageButton btnNext=(ImageButton)findViewById(R.id.next_page);
        ImageButton btnPrev=(ImageButton)findViewById(R.id.prev_page);
//*******************************************************Next/previous buttons*****************************************
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
//******************************************************Example list****************************************************
        ListView ld=(ListView)findViewById(R.id.data_list);
        dBaseAdapter adapter=new dBaseAdapter(this,generateItems());
        ld.setAdapter(adapter);
    }
    private ArrayList<ExampleListItemDTO> generateItems (){
        ArrayList<ExampleListItemDTO> list=new ArrayList<>();
        setupDBHelper();
        final Cursor cursorExample=mDb.rawQuery("SELECT * FROM example WHERE letter_id="+1,null);
        cursorExample.moveToFirst();
        while (!cursorExample.isAfterLast()){
            String ex_word=cursorExample.getString(1);
            String ex_transc=cursorExample.getString(3);
            list.add(new ExampleListItemDTO(ex_word,ex_transc));
        cursorExample.moveToNext();
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

    public class dBaseAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<ExampleListItemDTO> items;
        public dBaseAdapter(Context context,ArrayList<ExampleListItemDTO> items){
            this.context = context;
            this.items = items;
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                view=LayoutInflater.from(context).inflate(R.layout.example_list_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
                view.setTag(viewHolder);
            }else {
                viewHolder=(ViewHolder)view.getTag();
            }
            ExampleListItemDTO currentItem=(ExampleListItemDTO)getItem(position);
            viewHolder.itemWord.setText(currentItem.getWord());
            viewHolder.itemTransc.setText(currentItem.getW_transcript());
            return view;
        }

        public class ViewHolder{
            TextView itemWord;
            TextView itemTransc;
            public ViewHolder(View view) {
                itemWord=(TextView)view.findViewById(R.id.item_word);
                itemTransc=(TextView)view.findViewById(R.id.item_transcript);
            }
        }

    }
}