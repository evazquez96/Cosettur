package com.cos.israelc.cosettur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class rutas extends AppCompatActivity {
ImageView atras;
    ImageView atras1;
    ImageView atras2;
    ImageView atras3;
    ImageView atras4;
    ImageView atras5;
    PhotoViewAttacher zomm;
    PhotoViewAttacher zomm1;
    PhotoViewAttacher zomm2;
    PhotoViewAttacher zomm3;
    PhotoViewAttacher zomm4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);
        atras5=(ImageView)findViewById(R.id.imageView11);
        atras1=(ImageView)findViewById(R.id.imageView12);
        atras2=(ImageView)findViewById(R.id.imageView17);
        atras3=(ImageView)findViewById(R.id.imageView13);
        atras4=(ImageView)findViewById(R.id.ima);
        atras=(ImageView)findViewById(R.id.atr);
        zomm = new PhotoViewAttacher(atras1);
        zomm1 = new PhotoViewAttacher(atras2);
        zomm2 = new PhotoViewAttacher(atras3);
        zomm3 = new PhotoViewAttacher(atras4);
        zomm4 = new PhotoViewAttacher(atras5);



        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Intent neo= new Intent(rutas.this,menu.class);
               // startActivity(neo);
                finish();

            }
        });


    }
}
