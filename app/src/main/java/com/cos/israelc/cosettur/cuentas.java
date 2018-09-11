package com.cos.israelc.cosettur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class cuentas extends AppCompatActivity {
    ImageView atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentas);
        atras=(ImageView)findViewById(R.id.atr);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //Intent neo= new Intent(cuentas.this,menu.class);
                //startActivity(neo);
                finish();

            }
        });
    }
}
