package com.example.sample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver r = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            int battery = intent.getIntExtra("level", 0);
            ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
            bar.setProgress(battery);
            TextView textView = (TextView) findViewById(R.id.textField);
            textView.setText("THE BATTERY LEVEL : " + Integer.toString(battery) + "%");

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
        int time = Integer.parseInt(startAlarm.getText().toString());

    }

    public void pushToSplash(View view) {
        startActivity(new Intent(this, SplashScreen.class));
    }

    public void sendMessage(View view) {
        EditText message = findViewById(R.id.message);

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("MESSAGE", message.getText().toString());
        startActivity(intent);

        message.setText(" ..");
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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://medium.com/swlh/google-python-challenge-1-58c0eb760c92")));

            case R.id.c:
                startActivity(new Intent(this, Cprogram.class));
                return true;

            case R.id.call:
                Intent mycall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0780730001"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return true;
                }
                startActivity(mycall);

            case R.id.email:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                String to[] = {"michaelajnew@gmail.com", "edgarbaluku@gmail.com", "derek.barigye@gmail.com"};
                email.putExtra(Intent.EXTRA_EMAIL, to);
                email.putExtra(Intent.EXTRA_SUBJECT, "EMAIL WORK ANDROID");
                email.putExtra(Intent.EXTRA_TEXT, "JUST WORK ");
                email.setType("message/rfc822");
                startActivity(email);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

