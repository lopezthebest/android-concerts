package com.example.ariadna.engrescat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Filtre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);

        Button btn_cerca = (Button) findViewById(R.id.btn_cerca);
        final EditText poblevalue = (EditText) findViewById(R.id.poblevalue);
        final EditText grupvalue = (EditText) findViewById(R.id.grupvalue);
        final EditText datavalue = (EditText) findViewById(R.id.datavalue);

        btn_cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poble = poblevalue.getText().toString().trim();
                String data = datavalue.getText().toString().trim();
                String grup = grupvalue.getText().toString().trim();

                //ArrayList<Concert> filtrat = concertsdb.LoadFiltre(poble, data, grup);

                Intent intent = new Intent(getApplicationContext(), Llista.class);
                intent.putExtra("isFilter", true);
                intent.putExtra("EXTRA_POBLE", poble);
                intent.putExtra("EXTRA_DATA", data);
                intent.putExtra("EXTRA_GRUP", grup);
                startActivity(intent);

            }
        });
    }
}
