package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Text extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        list = findViewById(R.id.list);
        databaseHelper = new DatabaseHelper(this);

        // DATA (The message) passed from the MainActivity to the list view activity
        // being stored in to the SQLite database and being retrieved into a list view.
        enterData();
        populateList();
    }

    private void enterData() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        databaseHelper.addData(message);
    }

    public void populateList(){
        Cursor data = databaseHelper.getData();
        final ArrayList<String> arrayList = new ArrayList<>();
        while (data.moveToNext()){
            arrayList.add(data.getString(1));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout
                .simple_list_item_1, arrayList);

        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                String name = arrayList.get(i);

                Toast.makeText(Text.this,
                        " YOU HAVE CLICKED  " + name,
                        Toast.LENGTH_SHORT).show();
        }
    });
    }
}
