package com.cos.israelc.cosettur.ws;

import android.util.Log;
import android.util.Property;

import com.cos.israelc.cosettur.helpers.HorarioHelper;
import com.cos.israelc.cosettur.models.Response;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Date;

public class WebService {

    // Metodo que queremos ejecutar en el servicio web
    private static final String Metodo = "inscribirHorario";
    // Namespace definido en el servicio web
    private static final String namespace = "http://ws.cosettur.com/";
    // namespace + metodo
    private static final String accionSoap = "http://ws.cosettur.com/inscribirHorario";
    // Fichero de definicion del servcio web
    private static final String url = "http://node37874-env-3073930.jl.serv.net.mx/ROOT-215/CosetturWS?wsdl";
    private static SoapPrimitive resultado;



    public static Response consumirWs(String user, int grado, String semestre, int ruta, String localidad, int modalidad, int ciclo, String tutor, String tutorado, String Linicio, String Lfin, String Marinicio, String Marfin, String Mierinicio, String Mierfin, String Jinicio, String Jfin, String Vinicio, String Vfin, String pago, String tel) {

        Response response = new Response();

        try {

            SoapObject request = new SoapObject(namespace, Metodo);
            request.addProperty("user", user);
            request.addProperty("grado", grado);
            request.addProperty("semestre", semestre);
            request.addProperty("ruta", ruta);
            request.addProperty("localidad", localidad);
            request.addProperty("modalidad", modalidad);
            request.addProperty("ciclo", ciclo);
            request.addProperty("tutor", tutor);
            request.addProperty("tutorado", tutorado);
            request.addProperty("Linicio",Linicio);
            request.addProperty("Lfin",Lfin);
            request.addProperty("Marinicio",Marinicio);
            request.addProperty("Marfin",Marfin);
            request.addProperty("Mierinicio",Mierinicio);
            request.addProperty("Mierfin",Mierfin);
            request.addProperty("Jinicio",Jinicio);
            request.addProperty("Jfin",Jfin);
            request.addProperty("Vinicio",Vinicio);
            request.addProperty("Vfin",Vfin);
            request.addProperty("pago", pago);
            request.addProperty("tel", tel);

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

            response.setResponses(Integer.parseInt(resultado.toString()));


        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        } finally {

            return response;
            /*
             * El finally siempre se va a ejecutar, sin importar que se lanze
             * una exepction
             */
        }
    }
}
