package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class RecView extends AppCompatActivity {

    String rv[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_view);

        RecyclerView recyclerView = findViewById(R.id.recview);

        rv = getResources().getStringArray(R.array.apps);

        MyAdapta myAdapta = new MyAdapta(this, rv);

        recyclerView.setAdapter(myAdapta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
