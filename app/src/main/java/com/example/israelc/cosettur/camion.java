package com.example.israelc.cosettur;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class camion extends AppCompatActivity {
    Spinner spinner1;
    String[] element = {"Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camion);
      spinner1 = (Spinner) findViewById(R.id.rutass);
  spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, element));




    }
}
