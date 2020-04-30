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
import android.widget.Toast;

public class Cancelacion extends AppCompatActivity {

    private EditText editTxtMonto;
    private EditText editTxtAuth;
    private EditText editTxtOpNum;
    private Button btnCancel;
    private TextView cancelResultDude;


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
                String uri = "https://execute-payment/pay?License=5FW3fT5v-R7G3&TrxType=6&PType=1&TrxAmount="+amount+"&TrxCurrency=1&TrxOriginalNumber="+opNumber+"&TrxAuthNumber="+authNumber+"TrxUser=Cajero1";
                Intent intentCancelMen = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivityForResult(intentCancelMen,222);
            }
        });

    }

    //Recuperar el resultado del intentCancelMen,

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222 && data != null ) { //Este es el número de Bundle que usaste en el Intent we, por lo que te deberia de regresar el mismo
            String TrxResult = data.getStringExtra("TrxResult");
            String TrxAmount = data.getStringExtra("TrxAmount");
            String TrxCard = data.getStringExtra("TrxCard");
            String TrxAuthNumber = data.getStringExtra("TrxAuthNumber");
                cancelResultDude.setText("Response: "+TrxResult);
                editTxtMonto.setText("");
                editTxtAuth.setText("");
                editTxtOpNum.setText("");
            }else {
                Toast.makeText(this,"No se recibio algun codigo o info de respuesta",Toast.LENGTH_LONG).show();
            }

        }

    }



