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
    Spinner spinner2;
    Button sus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camion);
      spinner1 = (Spinner) findViewById(R.id.rutass);
sus=(Button)findViewById(R.id.ssc);

        String[] element = {"Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, element));

sus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSc78IW6UWqlVSWOhuuXCox0iDx_4BKwx3CsTvpSq86PQDMb1A/viewform?usp=sf_link");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
});


    }
}
