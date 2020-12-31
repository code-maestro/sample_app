package com.example.sample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class DisplayMessageActivity extends AppCompatActivity {

    EditText storedMessages;
    Button readInternalBtn;
    File myInternalFile;
    private String mInternalFileName;
    public String mData;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        TextView messageView = findViewById(R.id.messageTextView);
        readInternalBtn = findViewById(R.id.readButton);
        storedMessages = findViewById(R.id.stored_messages);

        messageView.setText(message);

        readInternalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromInternal();
            }
        });

        writeToInternal(message);

    }

    //  METHOD TO WRITE TO INTERNAL STORAGE
    public void writeToInternal(String message){
        String msg = message;

        mInternalFileName = "messages";

        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = openFileOutput(mInternalFileName, Context.MODE_APPEND);
            fileOutputStream.write(msg.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readFromInternal(){

        StringBuilder storedMessage = new StringBuilder();

        myInternalFile = new File(getFilesDir(), mInternalFileName);

        try {
            FileInputStream fis = new FileInputStream(myInternalFile);
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

        mData = storedMessage.toString();

        storedMessages.setText(mData);

    }

    public void onClose(View view) {
        super.finish();
        }
    }
