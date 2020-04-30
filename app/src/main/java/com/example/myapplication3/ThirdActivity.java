package com.example.myapplication3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;


public class ThirdActivity extends AppCompatActivity {

    private EditText montoVenta;
    private EditText referenceVenta;
    private EditText PaymentMode;
    private Button btnPay;
    private TextView txtDisplayVenta;
    private static int PAYMENT_PROCESS_RESULT = 111;
    private static int REPORT_PROCESS_RESULT = 322;
    private static int CARD_PROCESS_RESULT = 422;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        montoVenta = (EditText) findViewById(R.id.montoVenta);
        referenceVenta = (EditText) findViewById(R.id.referenceVenta);
        PaymentMode = (EditText) findViewById(R.id.PaymentMode);
        btnPay = (Button) findViewById(R.id.imageButtonPay);
        txtDisplayVenta = (TextView) findViewById(R.id.resultVenta);

        //Boton para invocar el pago
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String trxType = "1";
                String monto = montoVenta.getText().toString();
                String reference = referenceVenta.getText().toString();
                String formaPago = PaymentMode.getText().toString();
                String uri3 = "https://integration.intelipos.io/payment/sale";
                //En la línea 49 se muestra la forma de petición URI que se usaba en versiones anteriores.
                //String uri2 = "https://execute-payment/pay?License=5FW3fT5v-R7G3&TrxType=1&PType=1&TrxCurrency=1&TrxAmount="+monto+"&TrxReference="+reference+"Factura100&TrxFValue=E1&TrxUser=Cajero1&TrxPaymentMode=0,3";
                //forma el JSON;

                //if(formaPago != "")) {

                    ///////////////////////// A Partir de este punto se muestra la forma en que deberá cambiar la petición en JSON.
                    Gson gson = new Gson();
                    Transaction transaction = new Transaction("YTFiOWI2ZDktMTFlYy00YjhiLTliMmYtYmI3MDM2ODg2NmYz", monto, "1", reference, uri3, "0");
                    String json = gson.toJson(transaction);


                    //Intent intentVenta = new Intent(Intent.ACTION_VIEW,Uri.parse(uri2));
                    Intent intentVenta = new Intent(Intent.ACTION_VIEW, Uri.parse(uri3));
                    intentVenta.putExtra("request", json);
                    startActivityForResult(intentVenta, PAYMENT_PROCESS_RESULT);
                /*}else{
                    Gson gson = new Gson();
                    Transaction transaction = new Transaction("OGQ4MzJlMjEtZmU0MC00OWIxLThlZWYtNmIwZjQ4MGNiNDg2", monto, "1", reference, uri3,formaPago);
                    String json = gson.toJson(transaction);


                    //Intent intentVenta = new Intent(Intent.ACTION_VIEW,Uri.parse(uri2));
                    Intent intentVenta = new Intent(Intent.ACTION_VIEW, Uri.parse(uri3));
                    intentVenta.putExtra("request", json);
                    startActivityForResult(intentVenta, PAYMENT_PROCESS_RESULT);
                }*/

            }
        });

    }

    //Recuperar el resultado del intentPay,

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
          // if (requestCode == 111) { //Este es el número de Bundle que usaste en el Intent men
            if (requestCode == PAYMENT_PROCESS_RESULT) { //Este es el número de Bundle que usaste en el Intent men
                String response = data.getStringExtra("response");
                //Transaction.getInstance().initFromJson(response);
                String TrxResult = data.getStringExtra("TrxResult");
            String TrxAmount = data.getStringExtra("TrxAmount");
            String TrxCard = data.getStringExtra("TrxCard");
            String TrxAuthNumber = data.getStringExtra("TrxAuthNumber");
            String TrxOrgNumber = data.getStringExtra("TrxOrgNumber");
            String TrxCardType = data.getStringExtra("TrxCardType");
            String TrxMerchant = data.getStringExtra("TrxMerchant");
            String TrxARQC = data.getStringExtra("TrxARQC");
            String TrxAID = data.getStringExtra("TrxAID");
            String TrxBank = data.getStringExtra("TrxBank");
            String TrxCardInstrument = data.getStringExtra("TrxCardInstrument");
            String TrxPaymentMode = data.getStringExtra("TrxPaymentMode");
            String TrxReference = data.getStringExtra("TrxReference");
            String TrxRoomNbr = data.getStringExtra("TrxRoomNbr");


                    //txtDisplayVenta.setText("Pago: "+TrxResult+"\n"+"Monto: "+TrxAmount+"\n"+"Autorizacion: "+TrxAuthNumber+"Tarjeta: "+TrxCard+"\n"+"Num Operacion: "+TrxOrgNumber+"\n"+"response: "+response);
                txtDisplayVenta.setText("response: "+response);
                    montoVenta.setText("");
                    referenceVenta.setText("");


        }
    }

}
