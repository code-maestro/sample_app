package com.example.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

public class RecordingService extends Service {
    private int NOTIFICATION = 1;
    // Unique identifier for our notification
    public static boolean isRunning = false;
    public static RecordingService instance = null;
    private NotificationManager notificationManager = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){

        instance = this;
        isRunning = true;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)        // the status icon
                .setTicker("Service running...")           // the status text
                .setWhen(System.currentTimeMillis())       // the time stamp
                .setContentTitle("My App")                 // the label of the entry
                .setContentText("Service running...")      // the content of the entry
                .setContentIntent(contentIntent)           // the intent to send when the entry is clicked
                .setOngoing(true)                          // make persistent (disable swipe-away)
                .build();

                // Start service in foreground mode
                startForeground(NOTIFICATION, notification);

        return START_STICKY;

    }

    @Override
    public void onDestroy(){
        isRunning = false;
        instance = null;
        notificationManager.cancel(NOTIFICATION); // Remove notification
        super.onDestroy();

    }

    public void doSomething(){
        Toast.makeText(getApplicationContext(), "Doing stuff from service...",
                Toast.LENGTH_SHORT).show();
    }

}

