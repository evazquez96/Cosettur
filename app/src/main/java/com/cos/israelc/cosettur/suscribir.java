package com.cos.israelc.cosettur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class suscribir extends AppCompatActivity {
String correos;
String padres;
String alum;
int tell;
int call;

String grados;
int semestres;
String rutasol;
String local;
String modal;
String cicle;



    String[] sem = {"Preparatoria","Licenciatura"};
    String[] grade1 = {"1","2","3","4","5","6"};
    String[] grade2 = {"1,2,3,4,5,6,7,8,9"};
    String[] model = {"Parcial","Completo"};
    String[] rut = {"Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};
    String[]li1={"TEC MilenioTE","OPERAGUA","BOD.COMERCIAL MEXICANA","OXXO PALOMAS","PERINORTE"};
    String[]li2={"METRO ROSARIO,CASA DE LA CULTURA,SUBURBANO TLALNE,TOKS COLIN,PLAZA TLALNE,RODEO SANTA FE"};
    String[]l3={"VIPS ECHEGARAY,GLORIETA LLAS AMERICAS,GRAN TERRAZA,INCORP LOPEZ MATEOS A LOMAS VERDES,CRISTOBAL COLON,HOSPITAL RIO DE LA LOZA,LA ERA\n"};
    String[]l4={"localidad,COSMOPOL,FUENTES DEL VALLE,ASTA BANDERA,VALLE DORADO"};
    String[]ciclos={"ENERO-ABRIL","MAYO-AGOSTO","SEPTIEMBRE-DICIEMBRE"};

    Spinner gradi;
    Spinner sems;
    Spinner rot;
    Spinner lol;
    Spinner mod;
    Spinner cic;
    Button sig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscribir);
        gradi=(Spinner)findViewById(R.id.grado);
        sems=(Spinner)findViewById(R.id.semestre);
        rot=(Spinner)findViewById(R.id.Ruta);
        cic=(Spinner)findViewById(R.id.ciclo);
        mod=(Spinner)findViewById(R.id.modalida);
        lol=(Spinner)findViewById(R.id.localidad);
gradi.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sem));
rot.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rut));
mod.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, model));
cic.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciclos));
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
