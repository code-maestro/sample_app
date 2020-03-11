package com.example.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        // TOAST DISPALYING THE END OF THE ALARM
        Toast toast = Toast.makeText(context, "Time is UP dwagg", Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();


        throw new UnsupportedOperationException("Not yet implemented");
    }
}
