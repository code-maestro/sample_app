package com.example.sample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private String filename = "StoredMessages.txt";
    private String filepath = "MESSAGES";
    EditText storedMessages;
    Button readExternalBtn;
    File myExternalFile;
    String myData = "";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        TextView messageView = findViewById(R.id.messageTextView);
        readExternalBtn = findViewById(R.id.readBtn);
        storedMessages = findViewById(R.id.stored_messages);

        messageView.setText(message);

        readExternalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromInternal();
            }
        });

        writeToInternal(message);

        //writeToExternal(message);

    }

    //  METHOD TO WRITE TO INTERNAL STORAGE
    public void writeToInternal(String message){
        String msg = message;

        String fileName= "messages";

        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write(msg.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readFromInternal(){

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

        String data = storedMessage.toString();

        storedMessages.setText(data);

    }

    public void writeToExternal(String message){

    String msg = message;

        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(msg.getBytes());
            Log.d("external", "writeToExternal: FILE WRITTEN TO EXTERNAL STORAGE");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Toast.makeText(this, "NO EXTERNAL STORAGE MEDIA MOUNTED",
                    Toast.LENGTH_SHORT).show();
        }else{
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
            Toast.makeText(this, "FILE CREATED", Toast.LENGTH_SHORT).show();
        }

//        String state = Environment.getExternalStorageState();
//
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//
//            // Available to read and write
//            Toast.makeText(this,
//                    "EXTERNAL STORAGE MOUNTED",
//                    Toast.LENGTH_SHORT).show();
//
//            // Access your app's directory in the device's Public documents directory
//            File docs = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DOCUMENTS), "MESSAGES");
//
//        }
//        else {
//            Toast.makeText(this,
//                    "EXTERNAL STORAGE NOT MOUNTED",
//                    Toast.LENGTH_SHORT).show();
//
//        }
//
//        if (state.equals(Environment.MEDIA_MOUNTED) ||
//                state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
//            // Available to at least read
//        }

    }

    public void readFromExternal(){
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
                Log.d("external", "readFromExternal: " + myData);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        storedMessages.setText(myData);

        Toast.makeText(this,
                "StoredMessages.txt data retrieved from Internal Storage...",
                Toast.LENGTH_SHORT).show();
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }

    public void onClose(View view) {
        super.finish();
        }
    }
