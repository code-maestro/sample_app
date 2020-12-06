package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private Button add_btn, view_btn;
    private EditText entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        add_btn = findViewById(R.id.add_btn);
        view_btn = findViewById(R.id.view_btn);
        entry = findViewById(R.id.entry);

        databaseHelper = new DatabaseHelper(this);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newEntry = entry.getText().toString();

                if (newEntry.length() != 0){
                    addData(newEntry);
                    entry.setText("");
                    entry.requestFocus();
                }else {
                    Toast.makeText(Main2Activity.this,
                            "ENTER DATA TO STORE IN THE DATABASE",
                            Toast.LENGTH_SHORT).show();
                    entry.requestFocus();
                }
            }
        });

        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, Text.class));

            }
        });
    }

    public void addData (String data){
        boolean insertData = databaseHelper.addData(data);

        if (insertData){
            Toast.makeText(this, "DATA ENTRY SUCCESSFUL ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "DATA ENTRY FAILED..", Toast.LENGTH_SHORT).show();
        }

    }
}
