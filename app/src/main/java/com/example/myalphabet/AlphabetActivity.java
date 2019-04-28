package com.example.myalphabet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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

    private void buttonInGridLayout(){
        int length=Letter.letters.length;
        GridLayout grl=(GridLayout)findViewById(R.id.grid);
        for (int i = 0; i <length;i++){
            String text=Letter.letters[i].getCapital()+" "+Letter.letters[i].getUppercase();
            Button capital=new Button(this);
            capital.setBackgroundResource(R.drawable.letter_button_back);
            capital.setText(text);
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
