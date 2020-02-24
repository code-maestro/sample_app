package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class Song extends AppCompatActivity {

    Button playbtn, stopbtn;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.higher);

        playbtn = findViewById(R.id.playbtn);

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.higher);
                mPlayer.start();
            }
        });

        stopbtn = findViewById(R.id.stopbtn);

        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPlayer!=null && mPlayer.isPlaying()){//If music is playing already
                    mPlayer.stop();//Stop playing the music
                }
            }
        });
    }
}
