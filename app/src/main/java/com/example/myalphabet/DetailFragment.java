package com.example.myalphabet;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

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
        letterId = getArguments() != null ? getArguments().getInt("letter_id") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_detail, container, false);
        TextView pageHeader=(TextView)view.findViewById(R.id.head_detail);
        String header;
        header = Letter.letters[Integer.parseInt(String.format("%d",letterId))].getCapital();
        ImageView pageImg=(ImageView)view.findViewById(R.id.banner);
        String imageName=Letter.letters[Integer.parseInt(String.format("%d",letterId))].getImage();
        pageHeader.setText(header);
        int resID = getResources().getIdentifier(imageName, "drawable","com.example.myalphabet");
        pageImg.setImageResource(resID);

        TextView pageExample=(TextView)view.findViewById(R.id.example);
        String example;
        example=Letter.letters[Integer.parseInt(String.format("%d",letterId))].getExample();
        pageExample.setText(example);

        TextView pageTranscript=(TextView)view.findViewById(R.id.transcript);
        String transcript;
        transcript=Letter.letters[Integer.parseInt(String.format("%d",letterId))].getTranscript();
        pageTranscript.setText(transcript);

        return view;

    }


    public void onWomanVoiceClick(View view){
        mediaPlayer= MediaPlayer.create(getContext(),R.raw.dua_lipa);
        mediaPlayer.start();
    }
    public void onManVoiceClick(View view ){
        mediaPlayer.pause();
    }

    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }

    public long getLetterId() {
        return letterId;
    }
}
