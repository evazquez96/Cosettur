package com.example.israelc.cosettur;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {
    String[] element = {"Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};
    Spinner spinner1;
    public BlankFragment2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        spinner1 = (Spinner) getView().findViewById(R.id.rutass);


        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if(position != 0) { // la primer opcion es la vacia.
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rutass,new BlankFragment2()).commit();
        }
    }

}
