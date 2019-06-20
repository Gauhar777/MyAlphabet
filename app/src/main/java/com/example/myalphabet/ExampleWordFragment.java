package com.example.myalphabet;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
        int letter= (int) (letterId);
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

    private class LAdapter extends ArrayAdapter<String> {
        int layout;
        public LAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            layout=resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                LayoutInflater inflater=LayoutInflater.from(getContext());
                convertView=inflater.inflate(layout,parent,false);
                ViewHolder viewHolder=new ViewHolder();
                viewHolder.txt=(TextView)convertView.findViewById(R.id.ex_word);
                viewHolder.man_v=(Button)convertView.findViewById(R.id.ex_man_voice);
                viewHolder.woman_v=(Button)convertView.findViewById(R.id.ex_woman_voice);
                viewHolder.man_v.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),position+"*",Toast.LENGTH_SHORT).show();
                    }
                });
                convertView.setTag(viewHolder);
            }
            return super.getView(position, convertView, parent);
        }

    }

    public class ViewHolder{
        TextView txt,transc;
        Button man_v,woman_v;
    }

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

