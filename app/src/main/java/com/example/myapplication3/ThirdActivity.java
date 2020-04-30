package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ThirdActivity extends AppCompatActivity {

    private EditText montoVenta;
    private EditText referenceVenta;
    private Button btnPay;
    private TextView txtDisplayVenta;
    private static int PAYMENT_PROCESS_RESULT = 222;
    private static int REPORT_PROCESS_RESULT = 322;
    private static int CARD_PROCESS_RESULT = 422;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        montoVenta = (EditText) findViewById(R.id.montoVenta);
        referenceVenta = (EditText) findViewById(R.id.referenceVenta);
        btnPay = (Button) findViewById(R.id.imageButtonPay);
        txtDisplayVenta = (TextView) findViewById(R.id.resultVenta);

        //Boton para invocar el pago
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String trxType = "1";
                String monto = montoVenta.getText().toString();
                String reference = referenceVenta.getText().toString();
                String url = "https://integration.intelipos.io/payment/sale";
                String licencia = "ZmE5NjQxNDctYjIyNC00ZDUxLWJjZDYtZGYxZTkyN2JhYzM0";


                Transaction transaction = new Transaction();
                transaction.setLicense(licencia);
                transaction.setTrxAmount(monto);
                transaction.setTrxCurrency("1");
                transaction.setTrxReference(reference);
                transaction.setUrl(url);

                Gson gson = new Gson();
                String json = gson.toJson(transaction);
                System.out.println("json = " + json);

                Intent intentVenta = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivityForResult(intentVenta,PAYMENT_PROCESS_RESULT);

            }
        });

    }

    //Recuperar el resultado del intentPay,

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) { //Este es el número de Bundle que usaste en el Intent men
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

            txtDisplayVenta.setText("Pago: "+TrxResult+"\n"+"Monto: "+TrxAmount+"\n"+"Autorizacion: "+TrxAuthNumber);
            montoVenta.setText("");
            referenceVenta.setText("");
        }
    }

}