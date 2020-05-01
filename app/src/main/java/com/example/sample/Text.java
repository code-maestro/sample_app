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

        populateList();

    }

    public void populateList(){
        Cursor data = databaseHelper.getData();

        final ArrayList<String> arrayList = new ArrayList<>();

        while (data.moveToNext()){
            arrayList.add(data.getString(1));
        }

        arrayList.add(" Grand Theft Auto ");
        arrayList.add(" Ty dolla Sign ");
        arrayList.add(" Wiz khalifa ");
        arrayList.add(" Drake ");
        arrayList.add(" Trophies ");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout
                .simple_list_item_1, arrayList);

        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                String name = arrayList.get(i);

                Toast.makeText(Text.this, " YOU HAVE CLICKED  " + name, Toast.LENGTH_SHORT).show();


        }
    });
    }
}
