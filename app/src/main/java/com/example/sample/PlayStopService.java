package com.example.sample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

// AN UNBOUND SERVICE(Started service) for playing music in the back ground

public class PlayStopService extends Service {

    private MediaPlayer mPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        mPlayer = MediaPlayer.create(this, R.raw.higher);
        mPlayer.start();

        return START_STICKY;

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mPlayer.stop();

    }
}

