package com.cos.israelc.cosettur;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class MainActivity extends AppCompatActivity {
    public static final int RequestPermissionCode = 8;
    Button registrar;
Button mein;
TextView help;
EditText contra;
EditText usuarios;
TextView recuperar;
String a;
   Context context = null;
ProgressDialog pdialog = null;
String b;
String result1;
TextView aviso;

    // Metodo que queremos ejecutar en el servicio web
    private static final String Metodo = "login";
    // Namespace definido en el servicio web
    private static final String namespace = "http://webservice.cosettur.com/";
    // namespace + metodo
    private static final String accionSoap = "http://webservice.cosettur.com/login";
    // Fichero de definicion del servcio web
    private static final String url = "http://node37874-env-3073930.jl.serv.net.mx/UserWS?wsdl";

    private SoapPrimitive resultado;

    public boolean conumirWs() {


        Boolean bandera = true;
        try {

            SoapObject request = new SoapObject(namespace, Metodo);
            request.addProperty("user", a);
            request.addProperty("pass", b);

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
                try {
                    result1 = resultado.toString();

                    llenarDatos();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"Error de conexion Intente mas tarde", Toast.LENGTH_SHORT).show();
                }
            }else{
                Log.e("ERROR", "Error al consumir el webService");
                System.out.println("Error al consumir");

            }
        }

        public void llenarDatos() {

            if (result1.equals("1")) {
                Toast.makeText(MainActivity.this,"Bienvenido", Toast.LENGTH_SHORT).show();
            Intent activi = new Intent(MainActivity.this, menu.class);
            activi.putExtra("user",a);
                startActivity(activi);
                finish();
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
        configurarPermisos();
        context = this;
        setContentView(R.layout.activity_main);
aviso=(TextView)findViewById(R.id.terminos);
        contra = (EditText) findViewById(R.id.contrasena);
        usuarios = (EditText) findViewById(R.id.users);
        registrar=(Button)findViewById(R.id.re);
        help=(TextView) findViewById(R.id.helps);
recuperar=(TextView)findViewById(R.id.recu) ;
mein=(Button)findViewById(R.id.in);
help.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent ayudar = new Intent(MainActivity.this,ayuda.class);
        startActivity(ayudar);

    }
});

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

        Toast.makeText(MainActivity.this,"Espere un Momento..,", Toast.LENGTH_SHORT).show();
        if (usuarios.getText().toString().equals("")){
            usuarios.setHint("*Campo obligatorio");
        } else {

            if (contra.getText().toString().equals("")) {
                contra.setHint("*Campo obligatorio");


            } else {

                b = contra.getText().toString();
                a = usuarios.getText().toString();

                asyncBitacora ejec =new asyncBitacora();
                ejec.execute();
                }

        }


    }
});


recuperar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      Intent rec= new Intent(MainActivity.this,Main2Activity.class);
       startActivity(rec);




    }
});
aviso.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("https://www.cosettur.com/app/download/9959667419/Aviso+de+privacidad+Cosettur.pdf?t=1498142592");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
});


    }
    private static int progress;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    public void progreso(){

        progress = 0;
        progressBar = (ProgressBar) findViewById(R.id.progreso);
        progressBar.setMax(50);
        progressBar.setVisibility(View.VISIBLE);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 50) {
                    progressStatus = doSomeWork();
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @SuppressLint("WrongConstant")
                    public void run() {
                        // ---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
                        progressBar.setVisibility(8);
                    }
                });
            }

            private int doSomeWork() {
                try {
                    // ---simulate doing some work---
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void configurarPermisos() {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    {       WRITE_EXTERNAL_STORAGE,
                            READ_SMS,
                            READ_PHONE_STATE,
                            INTERNET
                    }, RequestPermissionCode);
        }


    public boolean CheckingPermissionIsEnabledOrNot() {

        int ReadSmsPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_SMS);
        int ReadPhoneStatePermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int ReadSdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int InternetPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);

        return ReadSmsPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ReadPhoneStatePermissionResult == PackageManager.PERMISSION_GRANTED && InternetPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

}
