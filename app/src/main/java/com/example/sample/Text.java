package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Text extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        list = findViewById(R.id.list);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(" Morbius  ");
        arrayList.add(" Black widow ");
        arrayList.add(" X-men ");
        arrayList.add(" Terminator ");
        arrayList.add(" Hitman ");
        arrayList.add(" Grand Theft Auto ");
        arrayList.add(" Vickie ");
        arrayList.add(" Ty dolla Sign ");
        arrayList.add(" Wiz khalifa ");
        arrayList.add(" Drake ");
        arrayList.add(" Trophies ");
        arrayList.add("official");
        arrayList.add(" chune ");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                arrayList);

        list.setAdapter(arrayAdapter);
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(Text.this, "You've clicked :   " + i +
                        "  " + arrayList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
