package com.example.israelc.cosettur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
Button registrar;
Button mein;
EditText contra;
    TextView recuperar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
registrar=(Button)findViewById(R.id.re);
recuperar=(TextView)findViewById(R.id.recu) ;
mein=(Button)findViewById(R.id.in);

registrar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent inicio= new Intent(MainActivity.this,registro.class);
        startActivity(inicio);
    }
});
mein.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
Intent rut= new Intent(MainActivity.this,menu.class);
startActivity(rut);

    }
});

recuperar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent rec= new Intent(MainActivity.this,recuperar.class);
        startActivity(rec);
    }
});


    }
}
