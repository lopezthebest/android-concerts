package com.example.ariadna.engrescat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class activity_filtre extends AppCompatActivity {

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
                String poble = poblevalue.getText().toString();
                String data = datavalue.getText().toString();
                String grup = grupvalue.getText().toString();

                ArrayList<Concert> filtrat = concertsdb.LoadFiltre(poble, data, grup);

            }
        });
    }
}
