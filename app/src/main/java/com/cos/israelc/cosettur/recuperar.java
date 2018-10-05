package com.cos.israelc.cosettur;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class recuperar extends AppCompatActivity {

    TextView r1;
    TextView r2;
    TextView r3;
    TextView r4;
    String correo;
    String contra;
    String result1;
    Button recovery;

    // Metodo que queremos ejecutar en el servicio web
    private static final String Metodo = "cambiarPass";
    // Namespace definido en el servicio web
    private static final String namespace = "http://webservice.cosettur.com/";
    // namespace + metodo
    private static final String accionSoap = "http://webservice.cosettur.com/cambiarPass";
    // Fichero de definicion del servcio web
    private static final String url = "http://node37874-env-3073930.jl.serv.net.mx/UserWS?wsdl";

    private SoapPrimitive resultado;

    public boolean conumirWs() {


        Boolean bandera = true;
        try {

            SoapObject request = new SoapObject(namespace, Metodo);
            request.addProperty("correo", correo);
            request.addProperty("newPass", contra);

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
                resultData();
            }else{
                Log.e("ERROR", "Error al consumir el webService");
                System.out.println("Error al consumir");
            }
        }

        private void resultData() {

           // Intent w = new Intent(recuperar.this, MainActivity.class);

            if (result1.equals("1")) {

                Toast.makeText(recuperar.this,"Contraseña cambiada satisfactoriamente", Toast.LENGTH_LONG).show();
                //startActivity(w);
                finish();

            } else {

                Toast.makeText(recuperar.this,"El correo no existe o no tienes conexión a internet", Toast.LENGTH_LONG).show();

            }

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        r1=(TextView)findViewById(R.id.correo1);
        r3=(TextView)findViewById(R.id.passw1);
        r4=(TextView)findViewById(R.id.passw2);
        recovery = (Button)findViewById(R.id.recover);
        r1.setHint("    Correo electronico");
        r3.setHint("    Nueva Contraseña");
        r4.setHint("    Confirmar Contraseña");

        recovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // verificBox();


                String[] TO = {"cosettur.soporte@gmail.com"}; //aquí pon tu correo
                String[] CC = {"superisraelsaya777@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                    finish();
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(recuperar.this,
                            "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void verificBox() {

        if (r1.getText().toString().equals("")) {
            r1.setHint("*Campo obligatorio");
        } else {
            if (r3.getText().toString().equals("")) {
                r3.setHint("*Campo obligatorio");
            } else {
                if (r4.getText().toString().equals("")) {
                    r4.setHint("*Campo obligatorio");
                } else {
                    if(r3.getText().toString().equals(r4.getText().toString())) {
                        getData();
                    } else {
                        r3.setText("");
                        r4.setText("");
                        r3.setHint("*Los campos no coinciden");
                        r4.setHint("*Los campos no coinciden");
                    }
                }

            }

        }

    }

    private void getData() {

        correo = r1.getText().toString();
        contra = r4.getText().toString();
        asyncBitacora q = new asyncBitacora();
        q.execute();

    }
}
