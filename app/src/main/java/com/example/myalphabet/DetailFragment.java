package com.example.myalphabet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    long letterId;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view=getView();
        TextView txt=(TextView)view.findViewById(R.id.txt1);
        String letter=Letter.letters[(int) letterId].getCapital();
        txt.setText(letter);
    }

    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }
}
