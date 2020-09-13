package com.progression.ethos.digitsgame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by client on 1/18/2017.
 */

public class SoundPlayer {

    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;
    private static SoundPool soundPool;
    private static int gameplaysound;
    private static int gameoversound;
    private static int gamestartsound;

    public SoundPlayer(Context context) {

        // SoundPool is deprecated in API level 21. (lollipop)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();

        } else {
            //SoundPool (int maxStreams, int streamType, int srcQuality)

            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);


        }


        gameplaysound = soundPool.load(context, R.raw.belltoll,1);
        gameoversound = soundPool.load(context, R.raw.droprain,1);
        gamestartsound = soundPool.load(context, R.raw.raindrop,1);
    }
    public void playgameplaysound() {
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(gameplaysound, 1.0f, 1.0f,1,0,1.0f);
    }
    public void playgameoversound() {
        soundPool.play(gameoversound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public void playgamestartsound() {
        soundPool.play(gamestartsound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}
