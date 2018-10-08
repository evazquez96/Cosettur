package com.cos.israelc.cosettur;

public class WebService {

    // Metodo que queremos ejecutar en el servicio web
    private static final String Metodo = "login";
    // Namespace definido en el servicio web
    private static final String namespace = "http://ws.cosettur.com/";
    // namespace + metodo
    private static final String accionSoap = "http://ws.cosettur.com/inscribirHorario";
    // Fichero de definicion del servcio web
    private static final String url = "http://node37874-env-3073930.jl.serv.net.mx/ROOT-289/CosetturWS?wsdl";

    private SoapPrimitive resultado;

    public Response conumirWs(String user, String grado, String semestre, String ruta, String localidad, String modalidad, String ciclo, String tutor, String tutorado, String entrada, String salida, String pago, String telefono) {

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
            request.addProperty("entrada", entrada);
            request.addProperty("salida", salida);
            request.addProperty("pago", pago);
            request.addProperty("telefono", telefono);

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

            response.setResponses(resultado.toString);


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
