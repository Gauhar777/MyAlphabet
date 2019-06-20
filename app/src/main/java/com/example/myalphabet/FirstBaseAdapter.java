package com.example.myalphabet;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class FirstBaseAdapter extends BaseAdapter {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    Context context;
    private MediaPlayer mediaPlayer;
    private static final String TAG = FirstBaseAdapter.class.getSimpleName();
    ArrayList<String> listArray;
    public FirstBaseAdapter(){
        listArray=new ArrayList<String>(4);
        listArray.add("Gfg0");
        listArray.add("Gfg1");
        listArray.add("Gfg2");
        listArray.add("Gfg3");
    }

    public FirstBaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return listArray.size();
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.fragment_example_word, viewGroup, false);
        }
        final String dataModel = listArray.get(i);
        Button btn=(Button)view.findViewById(R.id.ex_woman_voice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setupDBHelper();
                Cursor cursorLetter=mDb.rawQuery("SELECT * FROM voice WHERE letter_id="+1,null);
                cursorLetter.moveToFirst();
                //Toast.makeText(getActivity(),idLetter+"*",Toast.LENGTH_SHORT).show();
                String name=cursorLetter.getString(2);
                int rawId = context.getResources().getIdentifier("com.example.myalphabet:raw/" + name, null, null);
                mediaPlayer = MediaPlayer.create(context, rawId);
                mediaPlayer.start();
            }
        });

        return view;
    }
    private void setupDBHelper(){
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
