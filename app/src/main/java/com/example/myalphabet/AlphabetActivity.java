package com.example.myalphabet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class AlphabetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        buttonInGridLayout();
        goPreviousPgeTollbar();
    }


    private void buttonInGridLayout(){
        int length=Letter.letters.length;
        GridLayout grl=(GridLayout)findViewById(R.id.grid);
        for (int i = 0; i <length;i++){

            String text=Letter.letters[i].getCapital()+" "+Letter.letters[i].getUppercase();
            Button btnLetter=new Button(this);
            if (Letter.letters[i].getType()=="dt"){
                btnLetter.setTextColor(getResources().getColor(R.color.mainGray));
                btnLetter.setBackgroundResource(R.drawable.dt_letter_button_back);
            }else {
                btnLetter.setTextColor(getResources().getColor(R.color.mainWhite));
                btnLetter.setBackgroundResource(R.drawable.ds_letter_button_back);
            }
            btnLetter.setText(text);
            btnLetter.setId(i);
            btnLetter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id=v.getId();
                    Intent intent = new Intent(AlphabetActivity.this,LetterDetailActivity.class);
                    intent.putExtra(LetterDetailActivity.EXTRA_BUTTON_ID,(int) id);
                    startActivity(intent);
                }
            });
            btnLetter.setTextSize(50);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = 330;
            layoutParams.width = 330;
            layoutParams.setMargins(15, 15,15, 15);
            layoutParams.setGravity(Gravity.CENTER);
            grl.addView(btnLetter, layoutParams);
        }
    }

    public void goPreviousPgeTollbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

}
