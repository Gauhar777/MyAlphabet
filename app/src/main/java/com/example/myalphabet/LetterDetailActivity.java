package com.example.myalphabet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LetterDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BUTTON_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_letter_detail);
      Intent mIntent=getIntent();
      int btn=(int)mIntent.getExtras().get(EXTRA_BUTTON_ID);
      TextView txt=(TextView)findViewById(R.id.cen_text);
      String letter=Letter.letters[btn].getCapital();
      txt.setText(letter);

    }
}
