package com.example.israelc.cosettur;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class inicio extends Fragment {


    public inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            WebView myWebView = (WebView)getView().findViewById(R.id.we);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new WebViewClient());
            myWebView.loadUrl("https://sites.google.com/view/cossetur/p%C3%A1gina-principal");
        }
        catch(Exception e) {

        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);



    }

}
