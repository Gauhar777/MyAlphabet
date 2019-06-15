package com.example.myalphabet;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    private MediaPlayer mediaPlayer;
    long letterId;

    public static DetailFragment newInstance(int letterId) {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        args.putInt("letter_id",letterId);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_detail, container, false);
        letterId = getArguments() != null ? getArguments().getInt("letter_id") : 1;
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
/*****************************************letter of head section***************************************************************************/
        TextView cLetter= (TextView)getView().findViewById(R.id.capital_letter);
        String letter;
        setupDBHelper();
        int lId= (int) (letterId+1);
        Cursor cursorLetter=mDb.rawQuery("SELECT * FROM letter WHERE id="+lId+"",null);
        cursorLetter.moveToFirst();
        letter=cursorLetter.getString(1);
        cLetter.setText(letter);
        /*****************************************letter transcription of head section***************************************************************************/


        /*-****************************************photo of head section***************************************************************************/
        ImageView pageImg=(ImageView)getView().findViewById(R.id.banner);
        String imageName=Letter.letters[Integer.parseInt(String.format("%d",letterId))].getImage();
        Log.d("letterIdent","***********"+letterId);
        int resID = getResources().getIdentifier(imageName, "drawable","com.example.myalphabet");
        pageImg.setImageResource(resID);
        /*-****************************************main example word of head section***************************************************************************/
        TextView pageExample=(TextView)getView().findViewById(R.id.example);
        String example;
        example=Letter.letters[Integer.parseInt(String.format("%d",letterId))].getExample();
        pageExample.setText(example);
        /*-****************************************main word transcription of head section***************************************************************************/
        TextView pageTranscript=(TextView)getView().findViewById(R.id.transcript);
        String transcript;
        transcript=Letter.letters[Integer.parseInt(String.format("%d",letterId))].getTranscript();
        pageTranscript.setText(transcript);



    }

    public void onWomanVoiceClick(View view){
        mediaPlayer= MediaPlayer.create(getContext(),R.raw.dua_lipa);
        mediaPlayer.start();
    }
    public void onManVoiceClick(View view ){
        mediaPlayer.pause();
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
