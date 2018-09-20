package com.cos.israelc.cosettur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class horario extends AppCompatActivity {
    Button fin;
    Spinner lunes1;
    Spinner lunes2;
    Spinner martes1;
    Spinner martes2;
    Spinner miercoles1;
    Spinner miercoles2;
    Spinner jueves1;
    Spinner jueves2;
    Spinner   viernes1;
    Spinner   viernes2;
    String[] grade1 = {"07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        fin=(Button)findViewById(R.id.siguiente);
        lunes1=(Spinner)findViewById(R.id.l1);
        lunes2=(Spinner)findViewById(R.id.l2);
        martes1=(Spinner)findViewById(R.id.m1);
        martes2=(Spinner)findViewById(R.id.m2);
        miercoles1=(Spinner)findViewById(R.id.mi1);
        miercoles2=(Spinner)findViewById(R.id.mi2);
        jueves1=(Spinner)findViewById(R.id.j1);
        jueves2=(Spinner)findViewById(R.id.j2);
        viernes1=(Spinner)findViewById(R.id.v1);
        viernes2=(Spinner)findViewById(R.id.v2);
        lunes1.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        lunes2.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        martes1.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        martes2.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        miercoles1.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        miercoles2.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        jueves1.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        jueves2.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        viernes1.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));
        viernes2.setAdapter(new ArrayAdapter<String>(horario.this, android.R.layout.simple_spinner_item, grade1));

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                create();
                finish();
            }
        });

    }
    public void create(){
        String lu1=lunes1.getSelectedItem().toString();
        String lu2=lunes2.getSelectedItem().toString();
        String ma1=martes1.getSelectedItem().toString();
        String ma2=martes2.getSelectedItem().toString();
        String m11=miercoles1.getSelectedItem().toString();
        String mi2=miercoles2.getSelectedItem().toString();
        String je1=jueves1.getSelectedItem().toString();
        String je2=jueves2.getSelectedItem().toString();
        String vi1=viernes1.getSelectedItem().toString();
        String vi2=viernes2.getSelectedItem().toString();
        Toast.makeText(horario.this,"Guardando archivo", Toast.LENGTH_SHORT).show();

        horariopdf tem=new horariopdf(getApplicationContext());
        tem.opendocument();

        tem.addMetaData("COSSETTUR","Ficha de Inscripcion","Cosetturapps");
        tem.addtitle("COSSETTUP","Ficha de Inscreipcion","Cosettuarpps");

        tem.addparagraph("Nombre del alumno:  ");
        tem.addparagraph("                               Lunes  Martes  Miercoles  Juevez  Viernes");
        tem.addparagraph("Hora de Ascenso:    "+lu1+"     "+ma1+"     "+m11+"     "+je1+"     "+vi1 );
        tem.addparagraph("Hora de Descenso:   "+lu2+"     "+ma2+"     "+mi2+"     "+je2+"     "+vi2 );
        tem.closedocument();




    }

}
