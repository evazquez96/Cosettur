package com.cos.israelc.cosettur;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;

public class suscribir extends AppCompatActivity {
    String correos;
    String padres;
    String alum;
    String tell;
    String call;
     int pago;

    String grados;
    String semestres;
    String rutasol;
    String local;
    String modal;
    String cicle;



    String[] sem = {"Preparatoria","Licenciatura"};

    String[] model = {"Parcial","Completo"};
    String[] rut = {"Ruta","Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};
    String[]ciclos={"ENERO-ABRIL","MAYO-AGOSTO","SEPTIEMBRE-DICIEMBRE"};

    Spinner gradi;
    Spinner sems;
    Spinner rot;
    Spinner lol;
    Spinner mod;
    Spinner cic;
    Button sig;
    TextInputEditText correitos;
    TextInputEditText namesf;
    TextInputEditText child;
    TextInputEditText phones;
    TextInputEditText cl;
    TextView pagar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscribir);
        gradi=(Spinner)findViewById(R.id.grado);
        sems=(Spinner)findViewById(R.id.semestre);
        rot=(Spinner)findViewById(R.id.Ruta);
        cic=(Spinner)findViewById(R.id.ciclo);
        mod=(Spinner)findViewById(R.id.modalida);
        correitos=(TextInputEditText)findViewById(R.id.correoss);
        namesf=(TextInputEditText)findViewById(R.id.padre);
        child=(TextInputEditText)findViewById(R.id.alumno);
        phones=(TextInputEditText)findViewById(R.id.telephone);
        cl=(TextInputEditText)findViewById(R.id.celphone);
        pagar=(TextView)findViewById(R.id.idpag);
        gradi.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sem));
        rot.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rut));
        mod.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, model));
        cic.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciclos));

        gradi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String[] grade1 = {"1","2","3","4","5","6"};
                String[] grade2 = {"1","2","3","4","5","6","7","8","9"};
                sems=(Spinner)findViewById(R.id.semestre);
                String msupplier=gradi.getSelectedItem().toString();
                switch (msupplier){
                    case "Preparatoria":
                        sems.setAdapter(new ArrayAdapter<String>(suscribir.this, android.R.layout.simple_spinner_item, grade1));
                        break;
                    case"Licenciatura":
                        sems.setAdapter(new ArrayAdapter<String>(suscribir.this, android.R.layout.simple_spinner_item, grade2));

                        break; }
            }@Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        rot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[]li1={"Localidad","TEC MilenioTE","OPERAGUA","BOD.COMERCIAL MEXICANA","OXXO PALOMAS","PERINORTE"};
                String[]li2={"Localidad","METRO ROSARIO,CASA DE LA CULTURA,SUBURBANO TLALNE,TOKS COLIN,PLAZA TLALNE,RODEO SANTA FE"};
                String[]l3={"Localidad","VIPS ECHEGARAY","GLORIETA ","LAS AMERICAS","GRAN TERRAZA","LOMAS VERDES","CRISTOBAL COLON","HOSPITAL RIO ","LA ERA"};
                String[]l4={"Localidad","COSMOPOL","FUENTES DEL VALLE","ASTA BANDERA","VALLE DORADO"};
                lol=(Spinner)findViewById(R.id.localidad);
                modal=mod.getSelectedItem().toString();
                local=lol.getSelectedItem().toString();
                String msupplier=rot.getSelectedItem().toString();
                switch (msupplier){

                    case "Naucalpan":
                    lol.setAdapter(new ArrayAdapter<String>(suscribir.this, android.R.layout.simple_spinner_item, l3));



                        break;
                    case "Tlanepantla":
                        lol.setAdapter(new ArrayAdapter<String>(suscribir.this, android.R.layout.simple_spinner_item, li2));
                        break;
                    case "Coacalco":

                        lol.setAdapter(new ArrayAdapter<String>(suscribir.this, android.R.layout.simple_spinner_item, l4));

                        break;
                    case "C.Izcalli":
                        lol.setAdapter(new ArrayAdapter<String>(suscribir.this, android.R.layout.simple_spinner_item, li1));

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                                          @Override
                                          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                              
                                          }

                                          @Override
                                          public void onNothingSelected(AdapterView<?> adapterView) {

                                          }
                                      });


                sig = (Button) findViewById(R.id.siguiente);


        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    createpdf();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                Intent pago= new Intent(suscribir.this,horario.class);
                startActivity(pago);
                finish();
            }
        });

    }
    public void createpdf() throws DocumentException {

        grados= gradi.getSelectedItem().toString();
        rutasol=rot.getSelectedItem().toString();
        modal=mod.getSelectedItem().toString();
        cicle=cic.getSelectedItem().toString();
        local=lol.getSelectedItem().toString();
        semestres=sems.getSelectedItem().toString();
        correos=correitos.getText().toString();
        padres=namesf.getText().toString();
        alum=child.getText().toString();
        tell=phones.getText().toString();
        call=cl.getText().toString();
        Toast.makeText(suscribir.this,"Guardando archivo", Toast.LENGTH_SHORT).show();

        Templatepdf tem=new Templatepdf(getApplicationContext());
        tem.createfile(alum);
        tem.opendocument();

        tem.addMetaData("COSSETTUR","Ficha de Inscripcion","Cosetturapps");
        tem.addtitle("Proveedor de Servicios Educativos\n" +
                "Transportación y Turismo","RFC COS 160907 JZ5","atencionunitec@cosettur.com\n" +
                "cosettur@yahoo.com.mx");
        tem.lines("CONTRATO DE TRANSPORTE ESCOLAR CICLO 19-1");

        //tem.addparagraph("Correo Electronico:  "+correos);
        tem.addparagraph("CONTRATO DE PRESTACIÓN DE SERVICIO DE TRANSPORTE ESCOLAR QUE CELEBRAN TRANSPORTES DEL ÁNGEL –COSETTUR S.A. C.V.- Y (NOMBRE DEL PADRE O TUTOR)  :  "+padres);
        tem.addparagraph("Este instrumento lo celebran conforme a las declaraciones y cláusulas siguientes: ");
        tem.lines("declaraciones");
        tem.addparagraph("I.- Declara el TRANSPORTISTA ser una empresa establecida legalmente según consta en la escritura pública núm. 27,252 de fecha 7 de septiembre\n" +
                "del 2016 exhibida ante el Lic. Gustavo Mauricio Gamez Imaz, titular de la correduría no. 1 del Estado de México, e inscrita en el registro público de\n" +
                "comercio con número 20160006782900f2 de fecha 06 de octubre del 2016 y con el RFC: COS160907 JZ5, tener como domicilio convencional el\n" +
                "ubicado en: Nuevo León Núm. 110 Fracc Jacarandas, Tlalnepantla B., Méx. CP 54050 con número telefónico 5397 0032");
        tem.addparagraph("Que su representante legal acredita su personalidad, mediante testimonio notarial en la escritura pública núm. 27,252 de fecha 7 de septiembre\n" +
                "del 2016 exhibida ante el Lic. Gustavo Mauricio Gamez Imaz, titular de la correduría no. 1 del Estado de México, identificándose con la credencial\n" +
                "para votar con fotografía número 4999011725570 expedida por el Instituto Federal Electoral, misma que previo cotejo se devuelve a su poseedor,\n" +
                "exhibiéndose en los anexos del contrato una copia simple de la misma\n");
        tem.addparagraph("Que se encuentra reconocida por la Universidad Tecnológica de México Campus Atizapán como proveedor externo oficial para el servicio de\n" +
                "transporte escolar de alumnos de su comunidad estudiantil y que cuenta con capacidad general, especial y de preparación, infraestructura y\n" +
                "equipos suficientes para el cumplimiento del objeto de este instrumento.");
      tem.addparagraph("II.- Declara el PADRE DE FAMILIA: ");
        tem.addparagraph("Ser el Padre o Tutor del alumno(a)  "+alum);
        tem.addparagraph("Que cuenta con la capacidad legal, general y especial para celebrar el presente contrato, con domicilio en ");
        tem.addparagraph("Tener como domicilio convencional el ubicado en:  ");
        tem.addparagraph("Con número telefónico,:  "+tell);
        tem.addparagraph("III.- Que requiere y solicita del servicio de transporte escolar para la RUTA:   "+rutasol);
        tem.addparagraph("y punto de encuentro de ascenso en   "+local);
        tem.addparagraph("Con destino a Unitec Campus Atizapán, en la modalidad de transporte:   "+modal);
        tem.addparagraph("Y HORARIOS de entrada 7:00 y salida 13 hrs ó 15 hrs. ");
        tem.addparagraph("Durante el período denominado:  "+cicle+" excepto los días no laborables marcados en el calendario de Unitec exceptuando sábados y domingos y período vacacional.");
        tem.addparagraph("III.- Ambas partes declaran que conocen y comprenden del contenido y naturaleza de este contrato y que el mismo se celebra de conformidad a la " +
                "legislación civil aplicable y que no genera ni constituye relación de trabajo entre los contratantes y consecuentemente ninguna obligación derivada " +
                "de la existencia de una relación o contrato de trabajo.. ");

        tem.addparagraph("Nuevo León Núm. 110 Fracc Jacarandas Tlalnepantla B., Méx. CP 54050 Teléfono 5397 0032 Facabook:cosetturtransportepersonas www.cosettur.com TWITER:@cosettur");
        tem.lines("");



        tem.closedocument();

    }
    public void calculo(){

        rutasol=rot.getSelectedItem().toString();
        modal=mod.getSelectedItem().toString();
        local=lol.getSelectedItem().toString();

        if(rutasol.equals("Naucalpan")&&modal.equals("Parcial")){
        pago=3455;
            Toast.makeText(suscribir.this,"Usted pagara"+pago, Toast.LENGTH_SHORT).show();

        }

    }
    public void orden(){



    }

}

