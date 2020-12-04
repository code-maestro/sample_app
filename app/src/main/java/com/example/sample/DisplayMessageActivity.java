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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        TextView messageView = findViewById(R.id.messageTextView);

        TextView storedMessages = findViewById(R.id.stored_messages);

        messageView.setText(message);

        writeToInternal(message);

        storedMessages.setText(readToInternal());

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
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String readToInternal(){

        StringBuilder storedMessage = new StringBuilder();

        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                storedMessage.append(strLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return storedMessage.toString();
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
