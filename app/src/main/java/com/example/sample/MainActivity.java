package com.example.sample;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    BroadcastReceiver r = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            int battery = intent.getIntExtra("level", 0);
            ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
            bar.setProgress(battery);
            TextView textView = (TextView) findViewById(R.id.textField);

            String battery_level = "BATTERY LEVEL : " +
                    battery +
                    "%";

            //String battery_level = String.format("%f", battery);
            textView.setText(battery_level);
        }
    };

    // AN INNER CLASS FOR THE BROADCAST RECEIVER .
     /*
     class Receiver extends BroadcastReceiver{
       @Override
        public void onReceive(Context context, Intent intent) {
        }
     }
      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(r, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public void clickMe(View view) {
        EditText startAlarm = findViewById(R.id.startAlarm);
        String counter_time = startAlarm.getText().toString();

        if (!counter_time.isEmpty()) {

            int time = Integer.parseInt(counter_time);

            // CREATING THE INTENT AN D
            Intent intent = new Intent(this, MyReceiver.class);

            //creating a pending intent
            PendingIntent pendingIntent = PendingIntent.getBroadcast
                    (this.getApplicationContext(), 0, intent, 0);

            //real time clock
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            //CALLING THE ALARM
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() +
                    (time * 1000), pendingIntent);

            // TOAST TO DISPLAY THE ALARM TIME
            Toast.makeText(this, "Alarm set in" + time + "seconds", Toast.LENGTH_LONG).show();
        } else {
            Toast toast = Toast.makeText(this, "PLEASE ENTER THE COUNTER TIME ! ",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 220);
            toast.show();
            startAlarm.requestFocus();
        }
    }

    public void sendMessage(View view) {
        EditText message = findViewById(R.id.message);
        String msg = message.getText().toString();

        if (!msg.isEmpty()) {
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra("MESSAGE", msg);
            startActivity(intent);

            message.setText("");

        } else {
            Toast mytoast = Toast.makeText(this, "PLEASE ENTER A MESSAGE TO SEND ! ", Toast.LENGTH_SHORT);
            mytoast.setGravity(Gravity.CENTER, 0, -600);
            mytoast.show();
            message.requestFocus();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.text:
                startActivity(new Intent(this, Text.class));
                return true;
            case R.id.song:
                startActivity(new Intent(this, Song.class));
                return true;
            case R.id.recycler:
                startActivity(new Intent(this, RecView.class));
                return true;
            case R.id.implicit:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("https://medium.com/swlh/google-python-challenge-1-58c0eb760c92")));

            case R.id.c:
                startActivity(new Intent(this, Cprogram.class));
                return true;

            case R.id.sqlite:
                startActivity(new Intent(this, Main2Activity.class));
                return true;

            case R.id.call:

                Intent mycall;

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

                    return true;
                } else {
                    mycall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0706440333"));
                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT)
                            .show();
                }
                startActivity(mycall);

            case R.id.email:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                String to[] = {"killopoop@gmail.com",
                        "edgarbaluku@gmail.com",
                        "derek.barigye@gmail.com"};
                email.putExtra(Intent.EXTRA_EMAIL, to);
                email.putExtra(Intent.EXTRA_SUBJECT, "EMAIL WORK ANDROID");
                email.putExtra(Intent.EXTRA_TEXT, "MF JUST WORK ");
                email.setType("message/rfc822");
                startActivity(email);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

