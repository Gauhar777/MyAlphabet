package com.example.myalphabet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import static com.example.myalphabet.R.color.mainBlue;

public class AlphabetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        buttonInGridLayout();
    }

//    public void onButtonClick(View view ){
//        Intent intent=new Intent(this,LetterDetailActivity.class);
//        intent.putExtra("LetterNumber",view.getId());
//        startActivity(intent);
//    }

    private void buttonInGridLayout(){
        int length=Letter.letters.length;
        GridLayout grl=(GridLayout)findViewById(R.id.grid);
        for (int i = 0; i <length;i++){
            String text=Letter.letters[i].getCapital()+" "+Letter.letters[i].getUppercase();
            Button capital=new Button(this);
            capital.setBackgroundResource(R.drawable.letter_button_back);
            capital.setText(text);
            capital.setId(i);
            capital.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("","**********************************************************clicked"+v.getId()+"*********************************************");
                    int id=v.getId();
                    Intent intent = new Intent(AlphabetActivity.this,LetterDetailActivity.class);
                    intent.putExtra(LetterDetailActivity.EXTRA_BUTTON_ID,(int) id);
                    startActivity(intent);
                }
            });
            capital.setTextSize(50);
            capital.setTextColor(getResources().getColor(R.color.mainGray));
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = 330;
            layoutParams.width = 330;
            layoutParams.setMargins(15, 15,15, 15);
            layoutParams.setGravity(Gravity.CENTER);
            grl.addView(capital, layoutParams);
        }
    }
}
