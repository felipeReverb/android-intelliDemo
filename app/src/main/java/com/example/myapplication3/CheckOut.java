package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CheckOut extends AppCompatActivity {

    private EditText montoCheckout;
    private EditText opNumberCheckOut;
    private EditText authNumberCheckOut;
    private Button btnCheckOut;
    private TextView resultCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        montoCheckout = (EditText) findViewById(R.id.montoCheckOut);
        opNumberCheckOut = (EditText) findViewById(R.id.originalNumberCheckOut);
        authNumberCheckOut = (EditText) findViewById(R.id.authNumberCheckoutMan);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        resultCheckout = (TextView) findViewById(R.id.resultCheckOutMan);

        //Boton del Checkout


        //Boton para invocar el pago
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String trxType = "1";
                String montoChkOut = montoCheckout.getText().toString();
                String opNumChkOut = opNumberCheckOut.getText().toString();
                String authNumChkOut = authNumberCheckOut.getText().toString();
                String uri = "https://execute-payment/pay?License=5FW3fT5v-R7G3&TrxType=5&PType=1&TrxAmount="+montoChkOut+"&TrxCurrency=1&TrxOriginalNumber="+opNumChkOut+"&TrxAuthNumber="+authNumChkOut+"&TrxUser=Cajero1";
                //String uri2 = "https://execute-payment/pay?License=5FW3fT5v-R7G3&TrxType=5&PType=1&TrxAmount=1.00&TrxCurrency=1&TrxOriginalNumber=1234567&TrxAuthNumber=ABC123&TrxUser=Cajero1";

                Intent intentVenta = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivityForResult(intentVenta, 555);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 555) { //Este es el número de Bundle que usaste en el Intent men
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

            resultCheckout.setText("Pago: "+TrxResult);
            montoCheckout.setText("");
            opNumberCheckOut.setText("");
            authNumberCheckOut.setText("");
        }


}
}
