package com.example.myalphabet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LetterFragment extends ListFragment {


    public LetterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int length=Letter.letters.length;
        ArrayList<Map<String, Object>> letterList=new ArrayList<Map<String, Object>>(length);
        Map<String, Object> m;
        for (int i=0; i<length;i++){
            m=new HashMap<String,Object>();
            m.put("capital",Letter.letters[i].getCapital());
            m.put("upper",Letter.letters[i].getUppercase());
            letterList.add(m);
       }
        String[] from={"capital","upper"};
        int[] to={R.id.a_capital,R.id.a_uppercase};
        SimpleAdapter adapter=new SimpleAdapter(getContext(),letterList,R.layout.fragment_letter,from,to);
        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);
//        return inflater.inflate(R.layout.fragment_letter, container, false);
    }

}
