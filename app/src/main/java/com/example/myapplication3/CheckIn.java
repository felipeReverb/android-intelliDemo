package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CheckIn extends AppCompatActivity {

    private EditText editTxtMonto;
    private EditText editTxtReferencia;
    private EditText editTxtRoom;
    private Button btnCheckIn;
    private TextView checkinResultDude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        editTxtMonto = (EditText) findViewById(R.id.editTextMonto);
        editTxtReferencia = (EditText) findViewById(R.id.editTextReference);
        editTxtRoom = (EditText) findViewById(R.id.editTextRoom);
        btnCheckIn = (Button) findViewById(R.id.btnCheckIn);
        checkinResultDude = (TextView) findViewById(R.id.checkInResult);

        //Boton Para invocar el Check In

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monto = editTxtMonto.getText().toString();
                String referencia = editTxtReferencia.getText().toString();
                String room = editTxtRoom.getText().toString();
                String uri = "https://execute-payment/pay?License=5FW3fT5v-R7G3&TrxType=3&PType=1&TrxRoomNbr="+room+"&TrxCurrency=1&TrxAmount="+monto+"&TrxReference="+referencia+"TrxFValue=E1&TrxUser=Cajero1;";

                Intent intentCheckIn = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivityForResult(intentCheckIn,222);

            }
        });


    }

    //Recuperar resultado del intent

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222) { //Este es el número de Bundle que usaste en el Intent
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

            checkinResultDude.setText("Pago: "+TrxResult+"\n"+"Monto: "+TrxAmount+"\n"+"Autorizacion: "+TrxAuthNumber);
            editTxtMonto.setText("");
            editTxtReferencia.setText("");
            editTxtRoom.setText("");
        }
    }
}
