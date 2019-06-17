package com.example.myalphabet;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExampleWordFragment extends ListFragment {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    public long letterId;

    public static ExampleWordFragment newInstance(int letter) {
        Bundle args = new Bundle();
        ExampleWordFragment fragment = new ExampleWordFragment();
        args.putInt("letter_id",letter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=super.onCreateView(inflater,container,savedInstanceState);
        letterId = getArguments() != null ? getArguments().getInt("letter_id") : 1;
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupDBHelper();
        letterId = getArguments() != null ? getArguments().getInt("letter_id") : 1;
        int letter= (int) (letterId+1);
        Cursor cursor=mDb.rawQuery("SELECT * FROM example WHERE letter_id="+letter,null);
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String text = cursor.getString(3);
            String title = cursor.getString(1);
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("title",title);
            hm.put("txt",text);
            aList.add(hm);
            cursor.moveToNext();
        }
        cursor.close();
        String[] from = { "title","txt"};
        int[] to={R.id.ex_word,R.id.ex_transcript};
        SimpleAdapter adapter = new SimpleAdapter(getContext(), aList, R.layout.fragment_example_word, from, to);
        setListAdapter(adapter);
    }
/*    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }
*/
    private void setupDBHelper(){
        Context context=getContext();
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
