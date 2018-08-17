package com.example.israelc.cosettur;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.time.Instant;

public class menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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
            Intent mis= new Intent(menu.this,mision.class);
            startActivity(mis);

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


            Intent rut= new Intent(menu.this,rutas.class);
            startActivity(rut);

        } else if (id == R.id.elgir) {
            Intent elcamion= new Intent(menu.this,camion.class);
            startActivity(elcamion);

        }
    else if (id == R.id.suscribir) {
            WebView myWebView = (WebView) findViewById(R.id.we);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdy4eBAykFIpHNLdn3HRlcENBd39Cikht8vUzIC99hP0OTmvg/viewform?usp=sf_link");


        }
        else if (id == R.id.nav_share) {


            WebView myWebView = (WebView) findViewById(R.id.we);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("https://www.cosettur.com/");






        } else if (id == R.id.nav_send) {
            String[] TO = {"cosseru@gmail.com"}; //aquí pon tu correo
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
                Intent intent2 = new Intent(menu.this,menu.class);
                startActivity(intent2);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
