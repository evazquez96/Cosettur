package com.cos.israelc.cosettur;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main2Activity extends AppCompatActivity {
    Session session = null;
    EditText reciep, sub, msg;
    ProgressDialog pdialog = null;
    Context context = null;
    Button login;
    String rec, subject, textMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        login = (Button) findViewById(R.id.btn_submit);
        reciep = (EditText) findViewById(R.id.et_to);
        sub = (EditText) findViewById(R.id.et_sub);
        msg = (EditText) findViewById(R.id.et_text);
        context = this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

         validar();

            }
        });
    }
    public void secion(){
        rec = reciep.getText().toString();
        subject = sub.getText().toString();
        textMessage = msg.getText().toString();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("cosettur.soporte@gmail.com", "cosetturapps123");
                    }
                }

        );
        pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);
        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }
    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject("recuperacion de contraseña");
                message.setContent("https://sites.google.com/view/recuperacioncosettur/p%C3%A1gina-principal?authuser=1", "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            reciep.setText("");
            msg.setText("");
            sub.setText("");
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
    public void validar(){
if(reciep.getText().toString().equals("")){
    reciep.setHint("campo obligatorio");

}else if(sub.getText().toString().equals("")){
    sub.setHint("campo obligatorio");
}else if(reciep.getText().toString().equals(sub.getText().toString())){
    secion();

}else {
    reciep.setHint("No coinciden los campos");
   sub.setHint("No coinciden los campos");
}
{

}

    }


}
