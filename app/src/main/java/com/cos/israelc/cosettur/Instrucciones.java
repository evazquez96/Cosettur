package com.cos.israelc.cosettur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Instrucciones extends AppCompatActivity {

    final Context context = this;

    SharedPreferences sharedPrefe = getSharedPreferences("opcion",context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        SharedPreferences sharedPrefer = getPreferences(context.MODE_PRIVATE);
        Boolean ne = sharedPrefer.getBoolean("opc",false);
        if(ne == true){

        }

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean bandera;

                SharedPreferences sharedPrefer = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefer.edit();

                if(select.isChecked()){
                    Intent nuevo= new Intent(condiciones.this,LoginActivity.class);
                    startActivity(nuevo);
                    bandera=true;
                    editor.putBoolean("opc",bandera);
                    editor.commit();

                }

                else {
                    Toast.makeText(Instrucciones.this,"error",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
