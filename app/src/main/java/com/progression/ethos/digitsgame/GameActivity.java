package com.progression.ethos.digitsgame;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private TextView ScoreLabel;
    private TextView StartLabel;
    private ImageView andy;
    private ImageView d0;
    private ImageView d1;


    private ImageView dd;
    private ImageView gd;


    //Size
    private int frameHeight;
    private int andySize;
    private int screenWidth;
    private int screenHeight;

    // Position
    private int andyY;
    private int d0X;
    private int d0Y;
    private int d1X;
    private int d1Y;

    private int ddX;
    private int ddY;

    private int gdX;
    private int gdY;



    //Speed
    private int andySpeed;
    private int d0Speed;
    private int d1Speed;
    private int ddSpeed;
    private int gdSpeed;



    // score
    private int score = 0;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer sound;

    // Status Check
    private boolean action_flg = false;
    private boolean start_flg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sound = new SoundPlayer(this);

        ScoreLabel = (TextView) findViewById(R.id.ScoreLabel);
        StartLabel = (TextView) findViewById(R.id.StartLabel);
        andy = (ImageView) findViewById(R.id.andy);
        d0 = (ImageView) findViewById(R.id.d0);
        d1 = (ImageView) findViewById(R.id.d1);
        dd = (ImageView) findViewById(R.id.dd);
        gd = (ImageView) findViewById(R.id.gd);



        //Get screen size.

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point Size = new Point();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        //Now
        //Nexus4 widith:768 height:1184
        //Speed andy:40 d0:12 d1:20 dd:20 gd:16

        andySpeed = Math.round(screenHeight / 60F);
        d0Speed = Math.round(screenWidth / 60F);
        d1Speed = Math.round(screenWidth / 36F);
        ddSpeed = Math.round(screenWidth / 45F);
        gdSpeed = Math.round(screenWidth / 50F);


        Log.v("SPEED_ANDY", andySpeed+"");
        Log.v("SPEED_d0", d0Speed+"");
        Log.v("SPEED_d1", d1Speed+"");
        Log.v("SPEED_dd", ddSpeed+"");
        Log.v("SPEED_gd", gdSpeed+"");




        //Move to out of screen.
        d0.setX(-80);
        d0.setY(-80);
        d1.setX(-80);
        d1.setY(-80);
        dd.setX(-80);
        dd.setY(-80);
        gd.setX(-80);
        gd.setY(-80);


        ScoreLabel.setText("Score : 0");




    }

    public void changePos() {

        hitCheck();

        //d1
        d1X -= d1Speed;
        if (d1X < 0) {
            d1X = screenWidth + 50;
            d1Y = (int) Math.floor(Math.random() * (frameHeight - d1.getHeight()));
        }
        d1.setX(d1X);
        d1.setY(d1Y);



        //dd
        ddX -= ddSpeed;
        if (ddX < 0) {
            ddX = screenWidth + 70;
            ddY = (int) Math.floor(Math.random() * (frameHeight - dd.getHeight()));
        }
        dd.setX(ddX);
        dd.setY(ddY);

        //d0
        d0X -= d0Speed;
        if (d0X < 0) {
            d0X = screenWidth + 50;
            d0Y = (int) Math.floor(Math.random() * (frameHeight - d0.getHeight()));

        }
        d0.setX(d0X);
        d0.setY(d0Y);

        //gd
        gdX -= gdSpeed;
        if (gdX < 0) {
            gdX = screenWidth + 70;
            gdY = (int) Math.floor(Math.random() * (frameHeight - gd.getHeight()));
        }
        gd.setX(gdX);
        gd.setY(gdY);

        // Move andy
        if (action_flg == true) {
            // Touching
            andyY -= andySpeed;

        } else {
            // Releasing
            andyY += andySpeed;
        }

        // Check andy position.
        if (andyY < 0) andyY = 0;

        if (andyY > frameHeight - andySize) andyY = frameHeight - andySize;


        andy.setY(andyY);

        ScoreLabel.setText("Score :" + score);
    }

    public void hitCheck() {

        // If the center of the digit id in andy, it counts as a hit.

        // d1
        int d1CenterX = d1X + d1.getWidth()/2;
        int d1CenterY = d1Y + d1.getHeight()/2;

        // 0 <= d1CenterX <= andyWidth
        //andyX <= d1CenterY <= andyY + andyHeight



        if (0 <= d1CenterX && d1CenterX <= andySize &&
                andyY  <= d1CenterY && d1CenterY <= andyY + andySize) {

            score += 1;
            d1CenterX = -10;
            sound.playgameplaysound();
        }

        // d0
        int d0CenterX = d0X  + d0.getWidth()/2;
        int d0CenterY = d0Y + d0.getHeight()/2;

        if (0 <= d0CenterX && d0CenterX <= andySize &&
                andyY  <= d0CenterY && d0CenterY <= andyY + andySize) {

            score += 3;
            d0CenterX = -10;
            sound.playgameplaysound();
        }

        // dd
        int ddCenterX = ddX  + dd.getWidth()/2;
        int ddCenterY = ddY + dd.getHeight()/2;

        if (0 <= ddCenterX && ddCenterX <= andySize &&
                andyY  <= ddCenterY && ddCenterY <= andyY + andySize) {

            // Stop Timer!
            timer.cancel();
            timer = null;

            sound.playgameoversound();

            // Show Result.
            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE" , score);
            startActivity(intent);



        }

        // gd
        int gdCenterX = gdX  + gd.getWidth()/2;
        int gdCenterY = gdY + gd.getHeight()/2;

        if (0 <= gdCenterX && gdCenterX <= andySize &&
                andyY  <= gdCenterY && gdCenterY <= andyY + andySize) {

            // Stop Timer!
            timer.cancel();
            timer = null;

            sound.playgameoversound();

            // Show Result.
        }
    }

    public boolean onTouchEvent(MotionEvent me) {


        if (start_flg == false) {

            start_flg = true;

            // Why get frame height  and andy height here?
            // Because the UI has not been set on the screen in OnCreate()!!

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            andyY =(int)andy.getY();

            andySize = andy.getHeight();


            StartLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);


        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;

            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }

         return true;

    }

}
