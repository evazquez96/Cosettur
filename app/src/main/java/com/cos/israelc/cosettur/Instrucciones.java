package com.cos.israelc.cosettur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Instrucciones extends AppCompatActivity {

    Button acept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        final Context context = this;
        SharedPreferences sharedPrefe = getSharedPreferences("opcion",context.MODE_PRIVATE);
        acept = (Button) findViewById(R.id.aceptar);
        SharedPreferences sharedPrefer = getPreferences(context.MODE_PRIVATE);
        Boolean ne = sharedPrefer.getBoolean("opc",false);
        if(ne == true){
            Intent nuevo= new Intent(Instrucciones.this,inicio.class);
            startActivity(nuevo);
        }


       acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean bandera;

                SharedPreferences sharedPrefer = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefer.edit();
                Intent nuevo= new Intent(Instrucciones.this,MainActivity.class);
                startActivity(nuevo);
                bandera=true;
                editor.putBoolean("opc",bandera);
                editor.commit();
            }
        });

    }
}
