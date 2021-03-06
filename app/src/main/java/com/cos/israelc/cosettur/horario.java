package com.cos.israelc.cosettur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import com.cos.israelc.cosettur.helpers.HorarioHelper;
import com.itextpdf.text.DocumentException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;
import java.util.Date;

public class horario extends AppCompatActivity {
    Button fin;
    String lu1,lu2,ma1,ma2,mie1,mie2,ju1,ju2,vie1,vie2, user;
    Context context = null;
    ProgressDialog pdialog = null;
    int nGrado = 0;
    int nCiclo = 0;
    int nModalidad = 0;
    int nRuta = 0;
    int semestree;
    float pagos;
    short rutes;
    String usuario;
    String alumno;
    String padres;
    String local;
    String semestres;
    String telefono;
    String pago;
    String ciclo;
    String modalidad;
    String grado;
    String ruta;
    String entradas[] = new String[5];
    String salidas[]= new String[5];
    int resultDos;
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
    String[] grade1 = {"07:00:00","08:00:00","09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00","21:00:00","22:00:00"};

    // Metodo que queremos ejecutar en el servicio web
    private static final String Metodo = "inscribirHorario";
    // Namespace definido en el servicio web
    private static final String namespace = "http://ws.cosettur.com/";
    // namespace + metodo
    private static final String accionSoap = "http://ws.cosettur.com/inscribirHorario";
    // Fichero de definicion del servcio web
    private static final String url = "http://node37874-env-3073930.jl.serv.net.mx/ROOT-215/CosetturWS?wsdl";
    private static SoapPrimitive resultado;

    String result1;

    public boolean consumirWs() {

        Boolean bandera = true;

        try {
            SoapObject request = new SoapObject(namespace, Metodo);
            request.addProperty("user", usuario);
            request.addProperty("grado", nGrado);
            request.addProperty("semestre", semestree);
            request.addProperty("ruta", nRuta);
            request.addProperty("localidad", local);
            request.addProperty("modalidad", nModalidad);
            request.addProperty("ciclo", nCiclo);
            request.addProperty("tutor", padres);
            request.addProperty("tutorado", alumno);
            request.addProperty("Linicio",entradas[0]);
            request.addProperty("Lfin",salidas[0]);
            request.addProperty("Marinicio",entradas[1]);
            request.addProperty("Marfin",salidas[1]);
            request.addProperty("Mierinicio",entradas[2]);
            request.addProperty("Mierfin",salidas[2]);
            request.addProperty("Jinicio",entradas[3]);
            request.addProperty("Jfin",salidas[3]);
            request.addProperty("Vinicio",entradas[4]);
            request.addProperty("Vfin",salidas[4]);
            request.addProperty("pago", pago);
            request.addProperty("tel", telefono);

            // Modelo el Sobre
            SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            //si esta en .net se pone true, se lo contrario false
            sobre.dotNet = false;

            sobre.setOutputSoapObject(request);

            // Modelo el transporte
            HttpTransportSE transporte = new HttpTransportSE(url, 600000);

            // Llamada
            transporte.call(accionSoap, sobre);

            // Resultado
            resultado = (SoapPrimitive) sobre.getResponse();

            Log.i("Resultado", resultado.toString());

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
            bandera = false;
        } finally {

            return bandera;
            /*
             * El finally siempre se va a ejecutar, sin importar que se lanze
             * una exepction
             */
        }
    }

    class asyncBitacora extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            if(consumirWs()){
                return "ok";
            }else
                return "error";
        }

        @Override
        protected void onPostExecute(String result){
            if(result.equals("ok")){
                try {
                    result1 = resultado.toString();

                    resultDos = Integer.parseInt(result1);

                    llenarDatos();
                    pdialog.dismiss();

                } catch (Exception e) {
                    Toast.makeText(horario.this,"Error de conexion Intente mas tarde", Toast.LENGTH_SHORT).show();
                    pdialog.dismiss();
                }
            }else{
                Log.e("ERROR", "Error al consumir el webService");
                System.out.println("Error al consumir");

            }
        }

        public void llenarDatos() {

            if (resultDos != 0) {
                Toast.makeText(horario.this,"Registro almacenado en base de datos con folio: "+resultDos, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(horario.this,"El dato no se almaceno en la base de datos, registra de nuevo tu horario", Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        context=this;
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
        Date date = new Date();
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    pdialog = ProgressDialog.show(context, "", "Iniciando Sesion...", true);
                    asyncBitacora ejec =new asyncBitacora();
                    datos();
                    ejec.execute();
                    create();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                finish();
           /* Intent pago= new Intent(horario.this,Paypal.class);
                String money= pagos+"";
                String alum= alumno;
                pago.putExtra("pago",money);
                pago.putExtra("doc",alum);
                startActivity(pago)*/
            }
        });

    }
    public void create() throws DocumentException {
        String alumnos=getIntent().getStringExtra("alumno");
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

        Templatepdf tem=new Templatepdf(getApplicationContext());
        tem.createfile(alumnos+"horario");
        tem.opendocument();

        tem.addMetaData("COSSETTUR","Ficha de Inscripcion","Cosetturapps");
        tem.addtitle("Proveedor de Servicios Educativos\n" +
                "Transportación y Turismo","RFC COS 160907 JZ5","atencionunitec@cosettur.com\n" +
                "cosettur@yahoo.com.mx");
        tem.lines("Horario Sugerido");

        tem.addparagraph("Nombre del alumno:  "+alumnos);
        tem.addparagraph("                               Lunes  Martes  Miercoles  Juevez  Viernes");
        tem.addparagraph("Hora de Ascenso:    "+lu1+"     "+ma1+"     "+m11+"     "+je1+"     "+vi1 );
        tem.addparagraph("Hora de Descenso:   "+lu2+"     "+ma2+"     "+mi2+"     "+je2+"     "+vi2 );
        tem.closedocument();




    }

    public void datos(){


       lu1=lunes1.getSelectedItem().toString();
       lu2=lunes2.getSelectedItem().toString();
       ma1=martes1.getSelectedItem().toString();
       ma2=martes2.getSelectedItem().toString();
       mie1=miercoles1.getSelectedItem().toString();
       mie2=miercoles2.getSelectedItem().toString();
       ju1=jueves1.getSelectedItem().toString();
       ju2=jueves2.getSelectedItem().toString();
       vie1=viernes1.getSelectedItem().toString();
       vie2=viernes2.getSelectedItem().toString();
        usuario =getIntent().getStringExtra("user");
        alumno = getIntent().getStringExtra("alumno");
        padres = getIntent().getStringExtra("padres");
        local = getIntent().getStringExtra("local");
        semestres = getIntent().getStringExtra("semestres");
        telefono = getIntent().getStringExtra("telefono");
        pago = getIntent().getStringExtra("pago");
        ciclo = getIntent().getStringExtra("ciclo");
        modalidad = getIntent().getStringExtra("modalidad");
        ruta = getIntent().getStringExtra("ruta");
        grado = getIntent().getStringExtra("grado");
        semestree = Integer.parseInt(semestres);
        pagos = Float.parseFloat(pago);

        if (grado.equals("Licenciatura")) {
            nGrado = 2;
        } else {
            nGrado = 1;
        }

        if (ciclo.equals("ENERO-ABRIL")) {
            nCiclo = 1;
        }

        if (ciclo.equals("MAYO-AGOSTO")) {
            nCiclo = 2;
        }

        if (ciclo.equals("SEPTIEMBRE-DICIEMBRE")) {
            nCiclo = 3;
        }

        if (modalidad.equals("Parcial")) {
            nModalidad = 1;
        }

        if (modalidad.equals("Completo")) {
            nModalidad = 2;
        }

        if (modalidad.equals("Parcial-Med")) {
            nModalidad = 4;
        }

        if (modalidad.equals("Completo-Med")) {
            nModalidad = 3;
        }

        if (ruta.equals("Naucalpan")) {
            nRuta = 2;
        }

        if (ruta.equals("Tlanepantla")) {
            nRuta = 4;
        }

        if (ruta.equals("Coacalco")) {
            nRuta = 1;
        }

        if (ruta.equals("C.Izcalli")) {
            nRuta = 3;
        }

        entradas[0]=lu1;
        entradas[1]=ma1;
        entradas[2]=mie1;
        entradas[3]=ju1;
        entradas[4]=vie1;

        salidas[0]=lu2;
        salidas[1]=ma2;
        salidas[2]=mie2;
        salidas[3]=ju2;
        salidas[4]=vie2;

    }

}
