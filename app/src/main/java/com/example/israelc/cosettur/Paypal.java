package com.example.israelc.cosettur;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.paypal.android.sdk.payments.*;

import org.json.JSONException;

import java.math.BigDecimal;

public class Paypal extends AppCompatActivity {

    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;


    //el CONFIG_CLIENT_ID se debe de sustituir por el que se tiene en la cuenta de paypal
    private static final String CONFIG_CLIENT_ID = "AWTDrHxlorgGLwHGxvimaz4Q8R-z_C-0m9xi8bj9okKspDhcJo447eQ3091HNkCL-rRV42ooe6TqxMPf";
    private static final int REQUEST_CODE_PAYMENT = 1;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)

            // configuracion minima del ente
            .merchantName("Mi tienda")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.mi_tienda.com/privacy"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.mi_tienda.com/legal"));

    PayPalPayment thingToBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        findViewById(R.id.order).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                thingToBuy = new PayPalPayment(new BigDecimal("1"), "MXN",
                        "Transporte", PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(Paypal.this,
                        PaymentActivity.class);

                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

                startActivityForResult(intent, REQUEST_CODE_PAYMENT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data
                    .getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {

                    // informacion extra del pedido
                    System.out.println(confirm.toJSONObject().toString(4));
                    System.out.println(confirm.getPayment().toJSONObject()
                            .toString(4));

                    Toast.makeText(getApplicationContext(), "Orden procesada",
                            Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            System.out.println("El usuario canceló el pago");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
