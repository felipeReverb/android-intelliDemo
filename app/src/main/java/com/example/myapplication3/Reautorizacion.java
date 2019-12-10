package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Reautorizacion extends AppCompatActivity {

    private EditText txtMonto;
    private EditText opNum;
    private EditText authNum;
    private Button btnReaut;
    private TextView reauthResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reautorizacion);

        txtMonto = (EditText) findViewById(R.id.editTextMonto);
        opNum = (EditText) findViewById(R.id.originalNumber);
        authNum =(EditText) findViewById(R.id.AuthNumber);
        btnReaut = (Button) findViewById(R.id.btnReautorizacion);
        reauthResult = (TextView) findViewById(R.id.resultAuth);

        //Boton para invocar el pago
        btnReaut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trxType = "4";
                String monto = txtMonto.getText().toString();
                String autorizacion = authNum.getText().toString();
                String operacion = opNum.getText().toString();
                String uri = "https://execute-payment/pay?TrxAmount="+monto+"&TrxCurrency=1&TrxUser=1234&TrxOriginalNumber="+operacion+"&TrxAuthNumber="+autorizacion+"&License=5FW3ft5v-R7G3&TrxType="+trxType+"&PType=1";
                Intent intentReaut = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivityForResult(intentReaut, 333);
            }
        });

    }

    //Recuperar el resultado del intentPay,

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 333) { //Este es el número de Bundle que usaste en el Intent
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

            reauthResult.setText("Pago: "+TrxResult+"\n"+"Monto: "+TrxAmount+"\n"+"Autorizacion: "+TrxAuthNumber);
            txtMonto.setText("");
            authNum.setText("");
            opNum.setText("");
        }
    }

}
