package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CountDownAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_animation);
    }

    public void clickMe(View view) {
        EditText startAlarm = findViewById(R.id.startAlarm);
        String count = startAlarm.getText().toString();

        //if

        // SETTING THE TIME TO THE TEXT VIEW THAT WILL BE ANIMATED.
        TextView counter = findViewById(R.id.counter);
        counter.setText(count);

        //Variable storing the value for the decrement and progress.
        int number = Integer.parseInt(count);

        // Progress bar to move along with the counter..
        ProgressBar prog = findViewById(R.id.prog);
        prog.setProgress(number);

        // CREATING THE INTENT AN D
        Intent intent = new Intent(this, MyReceiver.class);

        //creating a pending intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this.getApplicationContext(),0,intent,0);

        //real time clock
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //CALLING THE ALARM
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+
                        (number* 2000), pendingIntent);

        // TOAST TO DISPLAY THE ALARM TIME
        Toast.makeText(this, "Alarm set in"+ number +"seconds", Toast.LENGTH_LONG)
                .show();
        }

        public void stop(View view){
            Toast.makeText(this,
                    "This is supposed to end the alarm counter",
                    Toast.LENGTH_SHORT)
                    .show();

            super.finish();
        }
    }

