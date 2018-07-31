package com.example.israelc.cosettur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class camion extends AppCompatActivity {
    Spinner spinner1;
    Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camion);
      spinner1 = (Spinner) findViewById(R.id.rutass);


        String[] element = {"Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, element));




    }
}
