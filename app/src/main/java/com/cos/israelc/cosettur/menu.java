package com.cos.israelc.cosettur;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progreso();
        WebView myWebView = (WebView) findViewById(R.id.we);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://sites.google.com/view/cossetur/p%C3%A1gina-principal");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        String usuario = getIntent().getStringExtra("user");
        name = (TextView)findViewById(R.id.nameUser);
        name.setText(usuario);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent mis= new Intent(menu.this,vision.class);
            startActivity(mis);

            return true;
        }
        if (id == R.id.cerrar) {
            Intent mis= new Intent(menu.this,MainActivity.class);
            startActivity(mis);
            finish();

            return true;
        }
        if (id == R.id.contrato) {
            Uri uri = Uri.parse("https://drive.google.com/open?id=1DSmfvo13PD82eE0q2Wekb-DHoue5g340");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        }
        if (id == R.id.pal) {
            Intent intent = new Intent(menu.this,cuentas.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.inicio) {



            WebView myWebView = (WebView) findViewById(R.id.we);
            myWebView.setVisibility(View.VISIBLE);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("https://sites.google.com/view/cossetur/p%C3%A1gina-principal");

        }

        if (id == R.id.pago) {

           Intent pagar= new Intent(menu.this,Paypal.class);
            startActivity(pagar);


        }

        if (id == R.id.rutas) {

            Intent pago= new Intent(menu.this,rutas.class);
            startActivity(pago);

        } else if (id == R.id.elgir) {

            Intent pago= new Intent(menu.this,camiones.class);
            startActivity(pago);

        }
    else if (id == R.id.suscribir) {
            Intent pago= new Intent(menu.this,suscribir.class);
            startActivity(pago);


          /*  WebView myWebView = (WebView) findViewById(R.id.we);
            myWebView.setVisibility(View.VISIBLE);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdy4eBAykFIpHNLdn3HRlcENBd39Cikht8vUzIC99hP0OTmvg/viewform?usp=sf_link");
*/
        }
        else if (id == R.id.nav_share) {

            progreso();


            WebView myWebView = (WebView) findViewById(R.id.we);
            myWebView.setVisibility(View.VISIBLE);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("https://www.cosettur.com/");





        } else if (id == R.id.nav_send) {

            String[] TO = {"atencionunitec@cosettur.com"}; //aquí pon tu correo
            String[] CC = {""};
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
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(menu.this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }




        }
        else if (id == R.id.whats) {
            Intent _intencion = new Intent("android.intent.action.MAIN");
            _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("521" + "5531072936")+"@s.whatsapp.net");
            startActivity(_intencion);
        }
        else if (id == R.id.whats2) {
            Intent _intencion = new Intent("android.intent.action.MAIN");
            _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("521" + "5566801443")+"@s.whatsapp.net");
            startActivity(_intencion);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

}
