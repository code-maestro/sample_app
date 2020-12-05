package com.example.sample;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class InternalExternal extends AppCompatActivity {

    EditText inputText, internal, external;

    Button saveButton,readButton;

    private String InternalFileName= "SampleAppInternalData.txt";
    private String InternalFilePath = "MyFileStorage";

    private String ExternalFileName = "StoredData.txt";
    private String ExternalFilePath = "MyFileStorage";

    File myInternalFile;
    File myExternalFile;

    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_external);

        inputText = (EditText) findViewById(R.id.data);
        internal = (EditText) findViewById(R.id.stored_messages);
        external = (EditText) findViewById(R.id.external_stored_messages);

        saveButton = findViewById(R.id.saveButton);
        readButton = (Button) findViewById(R.id.readButton);

        // Retrieving Files from the internal storage directory
        myInternalFile = new File(getFilesDir(), InternalFileName);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToInternal();
                writeToExternal();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               readFromExternal();
               readFromInternal();
            }
        });

        // Checking whether the external storage media is mounted
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            saveButton.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(ExternalFilePath), ExternalFileName);
        }
    }

    private void writeToInternal() {

        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = openFileOutput(InternalFileName, Context.MODE_APPEND);
            fileOutputStream.write(inputText.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToExternal() {
        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(inputText.getText().toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputText.setText("");
        Toast.makeText(InternalExternal.this,
                "StoredData.txt saved to External Storage...",
                Toast.LENGTH_SHORT).show();
    }

    private void readFromInternal() {

        try {
            FileInputStream fis = new FileInputStream(myInternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        internal.setText(myData);

    }

    private void readFromExternal(){
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        external.setText(myData);

        Toast.makeText(this,
                "StoredData.txt data retrieved from External Storage... ",
                Toast.LENGTH_SHORT).show();

    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

}
