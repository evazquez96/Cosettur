package com.example.israelc.cosettur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class recuperar extends AppCompatActivity {
TextView r1;
TextView r2;
    TextView r3;
    TextView r4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        r1=(TextView)findViewById(R.id.correo1);

         r3=(TextView)findViewById(R.id.passw1);
        r4=(TextView)findViewById(R.id.passw2);
        r1.setHint("    Correo electronico");

        r3.setHint("    Nueva Contraseña");
        r4.setHint("    Confirmar Contraseña");

    }
}
