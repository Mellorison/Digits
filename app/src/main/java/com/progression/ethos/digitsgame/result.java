package com.progression.ethos.digitsgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView ScoreLabel = (TextView) findViewById(R.id.ScoreLabel);
        TextView HighScoreLabel = (TextView) findViewById(R.id.HighScoreLabel);

        int Score = getIntent().getIntExtra("SCORE",0);
        ScoreLabel.setText(Score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int HighScore = settings.getInt("THANKS FOR PLAYING BETA",1);
        if (Score < HighScore) {
            HighScoreLabel.setText("High Score : " + Score);

            //Save.

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("THANKS FOR PLAYING BETA",Score);
            editor.commit();

        }else {
            HighScoreLabel.setText("BETA  :" + HighScore);
        }

    }

     public void tryAgain (View view) {
         startActivity(new Intent(getApplicationContext(), SplashActivity.class));
     }

    //Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;

            }
        }

        return super.dispatchKeyEvent(event);
    }
}
