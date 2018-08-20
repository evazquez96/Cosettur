package com.example.israelc.cosettur;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class camiones extends AppCompatActivity {
    Spinner spinner1;
    ImageView regre;
    ImageView imagen;
    WebView myWebView;
    String[] element = {"","Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camiones);
        spinner1 = (Spinner) findViewById(R.id.rutass);
        imagen=(ImageView)findViewById(R.id.in) ;
        myWebView = (WebView) findViewById(R.id.we);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, element));

       regre=(ImageView)findViewById(R.id.atras);
       regre.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               Intent pago= new Intent(camiones.this,menu.class);
               startActivity(pago);
           }
       });
       spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

           @Override
           public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {




               String msupplier=spinner1.getSelectedItem().toString();
               switch (msupplier){
                   case "Naucalpan":

                       Toast.makeText(camiones.this, "naucalpan", Toast.LENGTH_LONG).show();
                       imagen.setVisibility(View.GONE);
                       myWebView.setWebViewClient(new WebViewClient());
                       myWebView.loadUrl("https://drive.google.com/open?id=1n09RFLirEIMssKE8xqU3YxxijCd-3Zmh");
                       break;
                   case "Tlanepantla":

                       Toast.makeText(camiones.this, "No esta Diponible la informacion", Toast.LENGTH_LONG).show();
                       imagen.setVisibility(View.GONE);
                      // Uri uri1 = Uri.parse("https://drive.google.com/open?id=1n09RFLirEIMssKE8xqU3YxxijCd-3Zmh");
                       //Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                      // startActivity(intent1);
                           break;
                   case "Coacalco":

                       Toast.makeText(camiones.this, "Coacalco", Toast.LENGTH_LONG).show();
                       imagen.setVisibility(View.GONE);
                       myWebView.setWebViewClient(new WebViewClient());
                       myWebView.loadUrl("https://drive.google.com/open?id=1wgOQJ1jKLb5z0yowhSDhPFvK_mXQrwxB");
                       break;
                   case "C.Izcalli":

                       Toast.makeText(camiones.this, "C.Izcalli", Toast.LENGTH_LONG).show();
                       imagen.setVisibility(View.GONE);
                       myWebView.setWebViewClient(new WebViewClient());
                       myWebView.loadUrl("https://drive.google.com/open?id=1n09RFLirEIMssKE8xqU3YxxijCd-3Zmh");
                       break;
                   case "":
                       imagen.setVisibility(View.VISIBLE);
                       break;

               }

           }

           @Override
           public void onNothingSelected(AdapterView<?> arg0) {

           }
       });
    }

}
