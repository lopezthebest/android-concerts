package com.example.ariadna.engrescat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Llista extends AppCompatActivity {

    private ArrayList<Concert>concerts;
    private ListView llc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista);

        concerts= new ArrayList<>();
        concerts=concertsdb.loadConcerts(Llista.this);

        llc=(ListView)findViewById(R.id.llc);
        llc.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                concerts
        ));
    }
}
