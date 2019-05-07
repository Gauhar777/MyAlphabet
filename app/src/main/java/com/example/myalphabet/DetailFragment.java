package com.example.myalphabet;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
        TextView pageHeader=(TextView)view.findViewById(R.id.txt1);
        String header;
        header = Letter.letters[Integer.parseInt(String.format("%d",letterId+1))].getCapital();
        pageHeader.setText(header);
        return view;
    }

    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }

    public long getLetterId() {
        return letterId;
    }
}
