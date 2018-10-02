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

    String[] model={"Parcial","Completo","Parcial-Med","Completo-Med"};
    String[] rut={"Naucalpan","Tlanepantla","Coacalco","C.Izcalli"};
    String[]ciclos={"ENERO-ABRIL","MAYO-AGOSTO","SEPTIEMBRE-DICIEMBRE"};

    Spinner gradi;
    Spinner sems;
    Spinner rot;
    Spinner lol;
    Spinner mod;
    Spinner cic;
    Button sig;
    Button coutas;
    Button atrass;
    TextInputEditText correitos;
    TextInputEditText namesf;
    TextInputEditText child;
    TextInputEditText phones;
    TextInputEditText cl;
    TextView pagar;
    TextView texc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscribir);
        coutas=(Button)findViewById(R.id.btncoutas);
        atrass=(Button)findViewById(R.id.btnatr);
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
        texc=(TextView)findViewById(R.id.cuotas);
        gradi.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sem));
        rot.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rut));
        mod.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, model));
        cic.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciclos));

        atrass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        gradi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String[] grade1 = {"1","2","3","4","5","6","7","8","9"};
                String[] grade2 = {"1","2","3","4","5","6","7","8","9","10","11","12"};
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
                String[]li1={"Vips San Miguel","OPERAGUA","Bod. Comercial Mexicana","OXXO Palomas","Telpalcapan","Perinorte"};//cizcalli
                String[]li2={"Pte Cipreses","Dep. Carlos Hermosillo","Banamex/CFE","Mercado Tenayo","Banorte tenayuca","Suburbano TLALNE","Iglesia Reyes Iztacala","TOKS Mario Colin","PLAZA TLALNE","Vicky Form","Glorieta Los bastones","Santa Monica"};//tlane
                String[]l3={"Vips Echegaray","Glorieta las americas ","Plaza Gran Terraza","Incorp. Lomas Verdez","Cristobal Colon","Hospital Rio de la Loza ","LA ERA","Blvb Bellavista 54"};//naucalpan
                String[]l4={"COSMOPOL","Fuentes del valle","Asta bandera","La Quebrada","OXXO Barrientos","Valle Dorado"};//coacalco
                lol=(Spinner)findViewById(R.id.localidad);
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
        coutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo();
            }
        });



        sig=(Button)findViewById(R.id.siguiente);


        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              try {

                    createpdf();
                    orden();
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
        String pagarr=pagar.getText().toString();
        String txt=texc.getText().toString();
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
        tem.lines("C L A U S U L A S ");
        tem.addparagraph("Primera.- Las partes contratantes se reconocen capacidad,  la personalidad y voluntad con la que comparecen a la celebración del este instrumento.-   ");
        tem.addparagraph("Segunda. - Este contrato se celebra por un tiempo determinado y consecuentemente tendrá una vigencia y será obligatorio para las partes durante el período denominado ciclo 19-1 que para efectos del servicio de transporte escolar comprende del lunes 10 de septiembre 2018 al 14 de diciembre   2018 excepto los días no laborables marcados en el calendario de Unitec exceptuando sábados y domingos y período vacacional.  y que finalizado el período referido en éstas clausula, el presente contrato, quedará extinto terminando las fechas antes mencionadas.  \n" +
                " ");
        tem.addparagraph("Tercera.- El TRANSPORTISTA se obliga a prestar al PADRE DE FAMILIA los servicios  de transporte escolar  para el alumno(a) registrado en el formato de alta al sistema (ANEXO 1).-Estos servicios están expuestos en las declaraciones y con la modalidad solicitada por el padre o tutor. Para prestar los servicios que se contratan, el TRANSPORTISTA se obliga, a contar y obtener exclusivamente por su cuenta el equipo material y técnico. ");
        tem.addparagraph("Cuarta.- El PADRE DE FAMILIA reconoce que el servicio se paga por alumno-usuario del servicio, de manera anticipada y se obliga a pagar al TRANSPORTISTA la cantidad derivada de la opción que escoja, a saber: ");
        tem.addparagraph("-1 pago"+pagarr+txt);
        tem.addparagraph("Dicho precio no será susceptible de variación alguna durante la vigencia de este contrato -a menos que exista un incremento fuera del control del transportista- y será liquidado mediante tarjeta de crédito o débito, efectivo, depósito o transferencia, en la cuenta que para el efecto designe el TRANSPORTISTA, con las modalidades que se convienen en este instrumento y las comisiones que se señalen por el uso de tarjeta de crédito o débito, pagaderos los primeros 5 días naturales de cada  mes durante la vigencia de este contrato, manifestando su conformidad con la firma del presente contrato. En caso de no realizarlo, el PADRE DE FAMILIA está de acuerdo en el pago de los recargos de la manera siguiente:");
        tem.addparagraph("Del día 6 en adelante (naturales) del mes: 10% sobre monto parcial más IVA por cada parcialidad vencida Del DÍA OCHO EN ADELANTE SE SUSPENDERÁ EL SERVICIO HASTA LA RECEPCIÓN DEL PAGO CORRESPONDIENTE Una vez hecha la alta del alumno al servicio del transporte NO HAY REEMBOLSOS, DEVOLUCIONES, BONIFICACIONES NI BAJAS ");
        tem.addparagraph("Asimismo, el padre o tutor reconoce y acepta de manera voluntaria que deberá firmar los documentos de garantía de pago (pagarés) a favor del transportista y que es su responsabilidad recordar fechas de pago e igualmente de forma personal recoger los documentos cancelados en el módulo del servicio u oficinas del transportista previa cita ");
        tem.addparagraph("El Padre de Familia RECONOCE QUE SE LE  HA EXPLICADO el proceso para facturación y  QUE debido a los requerimientos de autoridades de SHCP en caso de no solicitar la factura los primeros 5 días naturales del mes corriente, ya no podrá solicitarla, debido a que se facturara como “Público en General”, manifestando su conformidad con la firma del presente contrato ");
        tem.addparagraph("Quinta. - El TRANSPORTISTA, se obliga, a entregar el recibo respectivo al PADRE DE FAMILIA, así como de generar la factura electrónica correspondiente a solicitud del cliente una vez que obren en su poder los datos requeridos para la emisión de la misma.  \n" +
                " ");
        tem.addparagraph(" Que además de las obligaciones contenidas en el presente contrato y de las derivadas de la propia naturaleza del servicio de transporte escolar, el TRANSPORTISTA asume, de forma expresa, las siguientes:");
        tem.addparagraph("1. A realizar el transporte escolar cumpliendo el itinerario, horario y calendario señalados. ");
        tem.addparagraph("2. A poner a disposición del servicio de transporte escolar, vehículos que cuenten con un buen estado de conservación y de uso y que cumplan, asimismo, las condiciones técnicas exigidas para el cumplimiento del servicio objeto del presente contrato (aún y si fuera una unidad particular) ");
        tem.addparagraph("3.- A tener suscrita una póliza de seguro, de conformidad a lo exigido en materia de transporte escolar y de circulación de vehículos. ");
        tem.addparagraph("4.- A ofrecer como mecanismo de queja, reclamación o inconformidad el horario de atención de la oficina mismo que es de lunes a viernes de 9 a 14 hrs y adicionalmente la comunicación vía celular de la titular de la empresa como vía de expresión de quejas, sugerencias, opiniones; así como, el correo de COSETTUR@YAHOO.COM.MX.   ");
        tem.addparagraph("Sexta – Son causas de rescisión del presente contrato sin responsabilidad para el transportista, el faltar al reglamento del servicio mismo que es leído, explicado y entregado una copia del mismo al Padre o tutor legal y/o  usuario en el momento de contratación y el cual  se encuentra como anexo al presente.(Anexo 2) ");
        tem.addparagraph("Séptima.- Ambas partes aceptan y reconocen expresamente que el presente contrato, no crea ni genera ni constituye una relación de trabajo, por lo que ambas partes quedan relevados de cualquier obligación de carácter laboral regulada y sancionada por la ley federal del trabajo.  Es importante señalar que este contrato sólo tendrá efecto entre las partes que lo otorgan, sin afectar a terceros");
        tem.addparagraph("Octava.-Ambas partes están de acuerdo en que en lo no previsto, se aplique lo dispuesto por el Código Civil D.F. y que en caso de controversia o conflicto sobre la interpretación, aplicación y alcances de este contrato, se someten a la  jurisdicción de los tribunales competentes DEL DF. ");
        tem.addparagraph("Novena.-  Ambas partes manifiestan que conocen y aprueban el contenido y alcances de este instrumento, por lo que lo firman al calce (Abajo) y al margen (A un lado de la hoja) como prueba eficaz de su pleno y total consentimiento, expresando que en él no concurre violencia física o moral, coacción o vicio alguno de la voluntad, a los ___ días del mes de ______________del año 2018 en la ciudad de Tlalnepantla de Baz, Estado de  México.  ");
        tem.addparagraph("EL TRANSPORTISTA  ");
        tem.lines("Asesor Ejecutivo Elsa C Trujillo Romero. ");
        tem.addparagraph(" EL PADRE DE FAMILIA ");
        tem.lines(padres);
        tem.addparagraph("Nuevo León Núm. 110 Fracc Jacarandas Tlalnepantla B., Méx. CP 54050 Teléfono 5397 0032 Facabook:cosetturtransportepersonas www.cosettur.com TWITER:@cosettur");
        tem.lines("");
        tem.closedocument();

    }
    public void calculo(){

        rutasol=rot.getSelectedItem().toString();
        modal=mod.getSelectedItem().toString();
        local=lol.getSelectedItem().toString();
//naucalpan
        if(rutasol.equals("Naucalpan")&&modal.equals("Parcial")){
      pagar.setText("1620.00");
      texc.setText("(Mil seiscientos veinte pesos)4 pagos parciales");

        }else if(rutasol.equals("Naucalpan")&&modal.equals("Completo")){
                pagar.setText("6156.00");
                texc.setText("(Seis mil ciento cincuenta y seis pesos)Un solo pago");
        }else if(rutasol.equals("Naucalpan")&&modal.equals("Completo-Med")){
            pagar.setText("4860.00");
            texc.setText("(Cuatro mil ochocientos secenta)cuatrimestral");
        }else if(rutasol.equals("Naucalpan")&&modal.equals("Parcial-Med")){
            pagar.setText("1215.00");
            texc.setText("(Mil docientos quince)4 pagos parciales");
        }
//coacalco precio 1
    else if(modal.equals("Completo")&&(local.equals("COSMOPOL")||local.equals("Fuentes del valle")||local.equals("Asta bandera"))){
            pagar.setText("8056");
            texc.setText("(Ocho mil Cincuenta y seis)Un solo pago");
        }else if(modal.equals("Completo-Med")&&(local.equals("COSMOPOL")||local.equals("Fuentes del valle")||local.equals("Asta bandera"))){
            pagar.setText("6360.00");
            texc.setText("(Seis mil trecientos sesenta peso)cuatrimestral");
        }else if(modal.equals("Parcial")&&(local.equals("COSMOPOL")||local.equals("Fuentes del valle")||local.equals("Asta bandera"))){
            pagar.setText("2120.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
        }else if(modal.equals("Parcial-Med")&&(local.equals("COSMOPOL")||local.equals("Fuentes del valle")||local.equals("Asta bandera"))) {
            pagar.setText("1590.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
        }

        else if(modal.equals("Completo")&&local.equals("La Quebrada")){
            pagar.setText("4560.00");
            texc.setText("(Ocho mil cuatrocientos ochenta peso)cuatrimestral");
        }else if(modal.equals("Completo-Med")&&local.equals("La Quebrada")){
            pagar.setText("3120.00");
            texc.setText("(Seis mil trecientos sesenta peso)cuatrimestral");
        }else if(modal.equals("Parcial")&&local.equals("La Quebrada")){
            pagar.setText("1200.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
        }else if(modal.equals("Parcial-Med")&&local.equals("La Quebrada")) {
            pagar.setText("780.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
        }
//cocalco 3*/
        else if(modal.equals("Completo")&&(local.equals("OXXO Barrientos")||local.equals("Valle Dorado"))){
            pagar.setText("3920.00");
            texc.setText("(tres mil novecientos veinte)cuatrimestral");
        }else if(modal.equals("Completo-Med")&&(local.equals("OXXO Barrientos")||local.equals("Valle Dorado"))){
            pagar.setText("2920.00");
            texc.setText("(Seis mil trecientos sesenta peso)cuatrimestral");
        }else if(modal.equals("Parcial")&&(local.equals("OXXO Barrientos")||local.equals("Valle Dorado"))){
            pagar.setText("980.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
        }else if(modal.equals("Parcial-Med")&&(local.equals("OXXO Barrientos")||local.equals("Valle Dorado"))) {
            pagar.setText("730.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
        }






        else if(rutasol.equals("C.Izcalli")&&modal.equals("Completo")){
            pagar.setText("8056.00");
            texc.setText("(Ocho mil cuatrocientos ochenta peso)un solo pago");
        }else if(rutasol.equals("C.Izcalli")&&modal.equals("Completo-Med")){
            pagar.setText("6360.00");
            texc.setText("(Seis mil trecientos sesenta peso)cuatriemestral");
        }else if(rutasol.equals("C.Izcalli")&&modal.equals("Parcial")){
            pagar.setText("2120.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
        }else if(rutasol.equals("C.Izcalli")&&modal.equals("Parcial-Med")) {
            pagar.setText("1590.00");
            texc.setText("(Mil docientos quince pesos)4 pagos parciales");
            //tlane 1
        }else if(modal.equals("Completo")&&(local.equals("Pte Cipreses")||local.equals("Dep. Carlos Hermosillo")||local.equals("Banamex/CFE")||local.equals("Mercado Tenayo")||local.equals("Suburbano TLALNE")||local.equals("Banorte tenayuca"))){
           pagar.setText("4560.00");
           texc.setText("cuatro mil ochocientos");
        }else if(modal.equals("Parcial")&&(local.equals("Pte Cipreses")||local.equals("Dep. Carlos Hermosillo")||local.equals("Banamex/CFE")||local.equals("Mercado Tenayo")||local.equals("Suburbano TLALNE")||local.equals("Banorte tenayuca"))){
            pagar.setText("1200.00");
            texc.setText("cuatro mil ochocientos");
        }else if(modal.equals("Completo-Med")&&(local.equals("Pte Cipreses")||local.equals("Dep. Carlos Hermosillo")||local.equals("Banamex/CFE")||local.equals("Mercado Tenayo")||local.equals("Suburbano TLALNE")||local.equals("Banorte tenayuca"))){
            pagar.setText("3120.00");
            texc.setText("cuatro mil ochocientos");
        }else if(modal.equals("Parcial-Med")&&(local.equals("Pte Cipreses")||local.equals("Dep. Carlos Hermosillo")||local.equals("Banamex/CFE")||local.equals("Mercado Tenayo")||local.equals("Suburbano TLALNE")||local.equals("Banorte tenayuca"))){
            pagar.setText("780.00");
            texc.setText("cuatro mil ochocientos");
        }
//tlane 2
        else if(modal.equals("Completo")&&(local.equals("Iglesia Reyes Iztacala")||local.equals("TOKS Mario Colin")||local.equals("PLAZA TLALNE")||local.equals("Vicky Form")||local.equals("Glorieta Los bastones")||local.equals("Santa Monica"))){
            pagar.setText("3920.00");
            texc.setText("cuatro mil ochocientos");
        }else if(modal.equals("Parcial")&&(local.equals("Iglesia Reyes Iztacala")||local.equals("TOKS Mario Colin")||local.equals("PLAZA TLALNE")||local.equals("Vicky Form")||local.equals("Glorieta Los bastones")||local.equals("Santa Monica"))){
            pagar.setText("980.00");
            texc.setText("cuatro mil ochocientos");
        }else if(modal.equals("Completo-Med")&&(local.equals("Iglesia Reyes Iztacala")||local.equals("TOKS Mario Colin")||local.equals("PLAZA TLALNE")||local.equals("Vicky Form")||local.equals("Glorieta Los bastones")||local.equals("Santa Monica"))){
            pagar.setText("2920.00");
            texc.setText("cuatro mil ochocientos");
        }else if(modal.equals("Parcial-Med")&&(local.equals("Iglesia Reyes Iztacala")||local.equals("TOKS Mario Colin")||local.equals("PLAZA TLALNE")||local.equals("Vicky Form")||local.equals("Glorieta Los bastones")||local.equals("Santa Monica"))){
            pagar.setText("730.00");
            texc.setText("cuatro mil ochocientos");
        }




    }
    public void orden() throws DocumentException {
        String pa=pagar.getText().toString();
        String mo=texc.getText().toString();
        Templatepdf tem=new Templatepdf(getApplicationContext());
        tem.createfile("contrato-"+alum);
        tem.opendocument();
        tem.addMetaData("COSSETTUR","Ficha de Inscripcion","Cosetturapps");
        tem.addtitle("Proveedor de Servicios Educativos\n" +
                "Transportación y Turismo","RFC COS 160907 JZ5","atencionunitec@cosettur.com\n" +
                "cosettur@yahoo.com.mx");
        tem.lines("Formato de pago");
        tem.addparagraph("Monto a pagar: "+pagar+texc);
        tem.addparagraph("Datos Bancario :  Hsbc");
        tem.addparagraph("A Nombre de: COSETTUR S.A. DE C.V.");
        tem.addparagraph("NO de cuenta: 4060-2729-03");
        tem.addparagraph("Clave Interbancaria: 0211-8004-0602-33");

        tem.addparagraph("Datos Bancario :  BANORTE");
        tem.addparagraph("A Nombre de: Maria Luisa Torres Sorroza");
        tem.addparagraph("NO de cuenta: 0248-8430-67");
        tem.addparagraph("Clave Interbancaria: 0721-8000-2488-67");
        tem.closedocument();

    }

}

