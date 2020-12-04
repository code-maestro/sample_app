package com.example.sample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        TextView messageView = findViewById(R.id.messageTextView);

        messageView.setText(message);

        writeToInternal(message);

    }

    //  METHOD TO WRITE TO INTERNAL STORAGE
    public void writeToInternal(String message){
        String msg = message;

        String fileName= "messages";
        // String textToWrite = "Hello, World!";
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write(msg.getBytes());
            Toast.makeText(this,
                    "A NEW MESSAGE BEEN APPEND TO THE messages FILE ON THE INTERNAL MEMORY",
                    Toast.LENGTH_SHORT).show();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void writeToExternal(String message){

        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {

            // Available to read and write
            Toast.makeText(this,
                    "EXTERNAL STORAGE MOUNTED",
                    Toast.LENGTH_SHORT).show();

            // Access your app's directory in the device's Public documents directory
            File docs = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "MESSAGES");

        }
        else {
            Toast.makeText(this,
                    "EXTERNAL STORAGE NOT MOUNTED",
                    Toast.LENGTH_SHORT).show();

        }

        if (state.equals(Environment.MEDIA_MOUNTED) ||
                state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            // Available to at least read
        }

    }

    public void onClose(View view) {
        super.finish();
        }
    }
