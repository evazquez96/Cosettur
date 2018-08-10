package com.example.israelc.cosettur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class MainActivity extends AppCompatActivity {
Button registrar;
Button mein;
EditText contra;
EditText usuarios;
TextView recuperar;
String a;
String b;
String result1;

    // Metodo que queremos ejecutar en el servicio web
    private static final String Metodo = "inicio";
    // Namespace definido en el servicio web
    private static final String namespace = "http://prueba/";
    // namespace + metodo
    private static final String accionSoap = "http://prueba/inicio";
    // Fichero de definicion del servcio web
    private static final String url = "http://node37458-env-8450796.jl.serv.net.mx/Prueba?wsdl";

    private SoapPrimitive resultado;

    public boolean conumirWs() {


        Boolean bandera = true;
        try {

            SoapObject request = new SoapObject(namespace, Metodo);
            request.addProperty("p1", a);
            request.addProperty("p2", b);

            // Modelo el Sobre
            SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            //si esta en .net se pone true, se lo contrario false
            sobre.dotNet = false;

            sobre.setOutputSoapObject(request);

            // Modelo el transporte
            HttpTransportSE transporte = new HttpTransportSE(url);

            // Llamada
            transporte.call(accionSoap, sobre);

            // Resultado
            resultado = (SoapPrimitive) sobre.getResponse();


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
            if(conumirWs()){
                return "ok";
            }else
                return "error";
        }

        @Override
        protected void onPostExecute(String result){
            if(result.equals("ok")){
                result1 = resultado.toString();
                llenarDatos();
            }else{
                Log.e("ERROR", "Error al consumir el webService");
                System.out.println("Error al consumir");
            }
        }

        public void llenarDatos() {

            if (result1.equals("true")) {


                Intent a = new Intent(MainActivity.this, menu.class);
                startActivity(a);
            } else {

                Toast.makeText(MainActivity.this,"Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                contra.setText("");
                usuarios.setText("");

            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contra = (EditText) findViewById(R.id.contrasena);
        usuarios = (EditText) findViewById(R.id.users);
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
        b = contra.getText().toString();
        a = usuarios.getText().toString();

        asyncBitacora ejec =new asyncBitacora();
        ejec.execute();

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
