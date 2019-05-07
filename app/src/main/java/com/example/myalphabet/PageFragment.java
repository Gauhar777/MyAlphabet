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
public class PageFragment extends Fragment {

    private int pageNumber;

    public static PageFragment newInstance(int position) {

        PageFragment fragment = new PageFragment();
        Bundle args=new Bundle();
        args.putInt("num", position);
        fragment.setArguments(args);
        return fragment;

    }

    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result=inflater.inflate(R.layout.fragment_page, container, false);
        TextView pageHeader=(TextView)result.findViewById(R.id.displayText);
        String header = String.format("Фрагмент %d", pageNumber+1);
        pageHeader.setText(header);
        return result;

    }

}
