package com.example.ariadna.engrescat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class activity_filtre extends AppCompatActivity {

    Spinner poblevalue;
    Spinner grupvalue;
    EditText datavalue;
    Calendar myCalendar;
    boolean antmapa =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);

        antmapa =getIntent().getBooleanExtra("antmapa", false);

        Button btn_cerca = (Button) findViewById(R.id.btn_cerca);
        poblevalue = (Spinner) findViewById(R.id.poblevalue);
        grupvalue = (Spinner) findViewById(R.id.grupvalue);
        datavalue = (EditText) findViewById(R.id.datavalue);

        ArrayList<String> poblacions = concertsdb.loadPoblacions();
        poblacions.add(0, "");
        ArrayAdapter<String> poblacionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, poblacions);
        poblacionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        poblevalue.setAdapter(poblacionsAdapter);

        ArrayList<String> grups = concertsdb.loadGrups();
        grups.add(0, "");
        ArrayAdapter<String> grupsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, grups);
        grupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grupvalue.setAdapter(grupsAdapter);

        btn_cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poble = poblevalue.getSelectedItem().toString().trim();
                String data = datavalue.getText().toString().trim();
                String grup = grupvalue.getSelectedItem().toString().trim();
                concertsdb.LoadFiltre(poble, data, grup);

                if (antmapa){
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    intent.putExtra("isFilter", true);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), Llista.class);
                    intent.putExtra("isFilter", true);
                    startActivity(intent);
                }
            }
        });




        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datavalue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(activity_filtre.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }


    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        datavalue.setText(sdf.format(myCalendar.getTime()));
    }
}
