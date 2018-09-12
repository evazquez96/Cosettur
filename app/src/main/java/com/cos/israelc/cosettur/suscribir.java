package com.cos.israelc.cosettur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class suscribir extends AppCompatActivity {
    TextView correo;
   EditText nomp;
   EditText nomh;
    EditText telp;
    EditText cell;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscribir);

        correo=(EditText)findViewById(R.id.cor);
       nomp=(EditText)findViewById(R.id.np);
        nomh=(EditText)findViewById(R.id.na);
        telp=(EditText)findViewById(R.id.telp);
        cell=(EditText)findViewById(R.id.cel);
      correo.setHint(" Correo Electronico*");
       nomp.setHint(" Nombre del Padre/Tutor*");
        nomh.setHint(" Nombre del alumno*");
      telp.setHint(" Telefono local*");
       cell.setHint(" Telefono Personal*");

    }
}
