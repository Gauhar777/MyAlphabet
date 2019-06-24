package com.example.myalphabet;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
    private MediaPlayer mediaPlayer;
    long idLetter;

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
        int nn=getArguments().getInt("letter_id",0);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_detail, container, false);
        idLetter= getArguments() != null ? getArguments().getInt("letter_id") : 1;
        final int letter_id=(int) idLetter+1;

        Button btnman=(Button)view.findViewById(R.id.man);
        Button btnwoman=(Button)view.findViewById(R.id.woman);
        btnman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setupDBHelper();
                    Cursor cursorLetter=mDb.rawQuery("SELECT * FROM voice WHERE letter_id="+letter_id,null);
                    cursorLetter.moveToFirst();
//                Toast.makeText(getActivity(),letter_id+"*",Toast.LENGTH_SHORT).show();
                    String name=cursorLetter.getString(1);
                    int rawId = getResources().getIdentifier("com.example.myalphabet:raw/" + name, null, null);
                    mediaPlayer = MediaPlayer.create(getContext(), rawId);
                    mediaPlayer.start();
                }catch (Exception ex){
                    Toast.makeText(getActivity(),"Qate bar!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnwoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setupDBHelper();
                    Cursor cursorLetter=mDb.rawQuery("SELECT * FROM voice WHERE letter_id="+letter_id,null);
                    cursorLetter.moveToFirst();
//                Toast.makeText(getActivity(),letter_id+"*",Toast.LENGTH_SHORT).show();
                    String name=cursorLetter.getString(2);
                    int rawId = getResources().getIdentifier("com.example.myalphabet:raw/" + name, null, null);
                    mediaPlayer = MediaPlayer.create(getContext(), rawId);
                    mediaPlayer.start();
                }catch (Exception ex){
                    Toast.makeText(getActivity(),"Qate bar!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
/*-****************************************letter of head section***************************************************************************/
        TextView cLetter= (TextView)getView().findViewById(R.id.capital_letter);
        String letter;
        setupDBHelper();
        final int letter_id=(int) idLetter+1;
//        Toast.makeText(getActivity(),"*"+letter_id+"*",Toast.LENGTH_SHORT).show();
//        Log.d("tag","*"+letter_id+"*");
//        int cl=getArguments().getInt("letter_id",2);
        //Toast.makeText(getActivity(),cl+"7",Toast.LENGTH_SHORT).show();
        Cursor cursorLetter=mDb.rawQuery("SELECT * FROM letter WHERE id="+letter_id+"",null);
        cursorLetter.moveToFirst();
        letter=cursorLetter.getString(1);
        cLetter.setText(letter);
/*-****************************************letter transcription of head section***************************************************************************/

/*-****************************************photo of head section***************************************************************************/
        ImageView pageImg=(ImageView)getView().findViewById(R.id.banner);
        Cursor cursorOnLetterImage=mDb.rawQuery("SELECT * FROM main_example WHERE letter_id="+letter_id,null);
        cursorOnLetterImage.moveToFirst();
        String imageName=cursorOnLetterImage.getString(2);
        int imgId = getResources().getIdentifier("com.example.myalphabet:drawable/" + imageName, null, null);
        pageImg.setImageResource(imgId);

/*-****************************************main example word of head section***************************************************************************/
        int exId=cursorOnLetterImage.getInt(1);
        TextView pageExample=(TextView)getView().findViewById(R.id.example);
        String example;
        Cursor cursorExample=mDb.rawQuery("SELECT * FROM example WHERE id="+exId,null);
        cursorExample.moveToFirst();
        example=cursorExample.getString(1);
        pageExample.setText(example);
/*-****************************************main word transcription of head section***************************************************************************/
        TextView pageTranscript=(TextView)getView().findViewById(R.id.transcript);
        String transcript;
        transcript=cursorExample.getString(3);
        pageTranscript.setText(transcript);
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
