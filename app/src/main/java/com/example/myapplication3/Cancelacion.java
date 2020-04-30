package com.example.myapplication3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class Cancelacion extends AppCompatActivity {

    private EditText editTxtMonto;
    private EditText editTxtAuth;
    private EditText editTxtOpNum;
    private Button btnCancel;
    private TextView cancelResultDude;
    private static int PAYMENT_PROCESS_RESULT = 222;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelacion);

        editTxtMonto = (EditText) findViewById(R.id.editTextMonto);
        editTxtAuth = (EditText) findViewById(R.id.editTextAuthNumber);
        editTxtOpNum = (EditText) findViewById(R.id.editTextOpNumber);
        btnCancel = (Button) findViewById(R.id.imageButtonCancel);
        cancelResultDude = (TextView) findViewById(R.id.cancelResult);


        //Boton para invocar la Cancelacion
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = editTxtMonto.getText().toString();
                String authNumber = editTxtAuth.getText().toString();
                String opNumber = editTxtOpNum.getText().toString();
                String uri3 = "https://integration.intelipos.io/payment/refund";
               // String uri = "https://execute-payment/pay?License=5FW3fT5v-R7G3&TrxType=6&PType=1&TrxAmount="+amount+"&TrxCurrency=1&TrxOriginalNumber="+opNumber+"&TrxAuthNumber="+authNumber+"TrxUser=Cajero1";
                //Intent intentCancelMen = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                //startActivityForResult(intentCancelMen,222);

                Gson gson = new Gson();
                Cancelation Cancelar = new Cancelation("OGQ4MzJlMjEtZmU0MC00OWIxLThlZWYtNmIwZjQ4MGNiNDg2", amount,"1",opNumber,uri3,authNumber);
                String json = gson.toJson(Cancelar);


                //Intent intentVenta = new Intent(Intent.ACTION_VIEW,Uri.parse(uri2));
                Intent intentVenta = new Intent(Intent.ACTION_VIEW, Uri.parse(uri3));
                intentVenta.putExtra("request", json);
                startActivityForResult(intentVenta, PAYMENT_PROCESS_RESULT);
            }
        });

    }

    //Recuperar el resultado del intentCancelMen,

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222 && data != null ) { //Este es el número de Bundle que usaste en el Intent we, por lo que te deberia de regresar el mismo
            String response = data.getStringExtra("response");
            String TrxResult = data.getStringExtra("TrxResult");
            String TrxAmount = data.getStringExtra("TrxAmount");
            String TrxCard = data.getStringExtra("TrxCard");
            String TrxAuthNumber = data.getStringExtra("TrxAuthNumber");
            String NumOperacion = data.getStringExtra("TrxOriginalNumber");
                cancelResultDude.setText("Response: "+ "\n"+TrxResult+"\n"+response);
                editTxtMonto.setText("");
                editTxtAuth.setText("");
                editTxtOpNum.setText("");
            }else {
                Toast.makeText(this,"No se recibio algun codigo o info de respuesta",Toast.LENGTH_LONG).show();
            }

        }

    }



