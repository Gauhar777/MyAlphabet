package com.example.myalphabet;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LetterDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BUTTON_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_detail);
        Intent mIntent=getIntent();
        int letter_id=(int)mIntent.getExtras().get(EXTRA_BUTTON_ID);
//        TextView txt=(TextView)findViewById(R.id.cen_text);
//        String letter=Letter.letters[letter_id].getCapital();
//        txt.setText(letter);

        ViewPager pager=(ViewPager)findViewById(R.id.pager2);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(letter_id-1);
//        DetailFragment dfrag=(DetailFragment)getSupportFragmentManager().findFragmentById(R.id.detailFrag);
//        dfrag.setLetterId(letter_id);
    }


//    public void nextLetter(View view){
//        DetailFragment dfrag2=(DetailFragment)getSupportFragmentManager().findFragmentById(R.id.detailFrag);
//        Long idLetter=dfrag2.getLetterId();
//        Log.d("","*************************************************** "+idLetter);
//        dfrag2.setLetterId(idLetter+1);
//
//        DetailFragment dfrag1=(DetailFragment)getSupportFragmentManager().findFragmentById(R.id.detailFrag);
//        dfrag1.setLetterId(idLetter+1);
//
//    }

}