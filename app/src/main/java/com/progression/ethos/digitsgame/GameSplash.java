package com.progression.ethos.digitsgame;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by client on 12/24/2017.
 */

public class GameSplash extends AppCompatActivity {

    ProgressBar mprogressbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView img = (ImageView)findViewById(R.id.pixelspace);

        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.animotion);
        img.setAnimation(anim1);

        mprogressbar = (ProgressBar)findViewById(R.id.progressBar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressbar, "progress", 0, 100);
        anim1.setDuration(40);
        anim1.setInterpolator(new DecelerateInterpolator());
        anim1.start();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(GameSplash.this, GameActivity.class));
                finish();

            }
        },30);
    }
}
