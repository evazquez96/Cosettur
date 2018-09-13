package com.cos.israelc.cosettur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class suscribir extends AppCompatActivity {
Button sig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscribir);


    sig=(Button)findViewById(R.id.siguiente);
    sig.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent pago= new Intent(suscribir.this,horario.class);
            startActivity(pago);
            finish();
        }
    });
    }

}
