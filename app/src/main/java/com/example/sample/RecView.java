package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class RecView extends AppCompatActivity {

    MyContentProvider mProvider;
    String rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_view);

        populateRecyclerView();

    }

    private void populateRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recview);

         Intent stringIntent = getIntent();

        rv = stringIntent.getStringExtra("MESSAGE");

        MyAdapta myAdapta = new MyAdapta(this, rv);

        recyclerView.setAdapter(myAdapta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
