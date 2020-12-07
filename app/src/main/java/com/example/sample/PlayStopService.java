package com.example.sample;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import static com.example.sample.Notification.CHANEL_ID;

// AN UNBOUND SERVICE(Started service) for playing music in the back ground
public class PlayStopService extends Service {

    private MediaPlayer mPlayer;
    private Notification mNotification;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        playSong();

        notificationStuff();

        return START_NOT_STICKY;

    }

    private void playSong(){
        mPlayer = MediaPlayer.create(this, R.raw.higher);
        mPlayer.start();
    }

    private void notificationStuff(){
        Intent songUIintent = new Intent(this,Song.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, songUIintent, 0);

        mNotification = new NotificationCompat.Builder(this, CHANEL_ID)
                .setContentTitle("ForegroundService Example")
                .setContentText("music playing in the back ground")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, mNotification);
    }
    
    @Override
    public void onDestroy(){
        super.onDestroy();
        mPlayer.stop();
    }
}

