package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckIn extends AppCompatActivity {

    private EditText editTxtMonto;
    private EditText editTxtReferencia;
    private EditText editTxtRoom;
    private Button btnCheckIn;
    private TextView checkinResultDude;

    //se agregan componentes a utilizar para el mensaje de  notificacion
    private Dialog epicDialog;
    private Button btnAccept;
    private Button btnRetry;
    private ImageView closePopupPositiveImg;
    private ImageView closePopupNegativeImg;
    private TextView approvedResponseText;
    private TextView approvedPaymentDetailsText;
    //aqui termino de agregar los componentes que utilizare para el mensahe de notificacion

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
        if (requestCode == 222 && data != null && resultCode != 0 ) { // Condicion para validar que se regresa infomacion del startActivityForResult
            String TrxResultMen = data.getStringExtra("TrxResult");
            if ( TrxResultMen.equals("Approved") && TrxResultMen != null){ //Condidcion para controlar la respuesta e invoar popup de notificacion al usuario
                //Se instacia en Dialog
                epicDialog = new Dialog(this);
                String TrxAmount = data.getStringExtra("TrxAmount");
                String TrxCard = data.getStringExtra("TrxCard");
                String TrxAuthNumber = data.getStringExtra("TrxAuthNumber");
                ShowPositivePopup("Autorizacion: "+TrxAuthNumber,"Monto: "+TrxAmount, "Tarjeta: "+TrxCard);
                editTxtMonto.setText("");
                editTxtReferencia.setText("");
                editTxtRoom.setText("");
            }else if (TrxResultMen.equals("Denied") && TrxResultMen != null){ //flujo para delcinado
                epicDialog = new Dialog(this);
                ShowNegativePopup();
                //Toast.makeText(this,"Entro al else del pago declinado men",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"Entro al else del pago error men",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"ERROR!-.No se recibio algun codigo o info de respuesta",Toast.LENGTH_LONG).show();

        }
    }
    //Funcion para Mostrar el popup aprobado
    public void ShowPositivePopup(String AuthRes, String MontoRes, String cardNumberRes){

        epicDialog.setContentView(R.layout.epic_popup_positive);
        closePopupPositiveImg = (ImageView) epicDialog.findViewById(R.id.closePopupPositiveImg);
        btnAccept = (Button) epicDialog.findViewById(R.id.btnAccept);
        approvedResponseText = (TextView) epicDialog.findViewById(R.id.approvedResponseText);
        approvedPaymentDetailsText = (TextView) epicDialog.findViewById(R.id.approvedPaymentDetailsText);
        approvedPaymentDetailsText.setText(AuthRes + "\n" + MontoRes + "\n" + cardNumberRes);

        closePopupPositiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();


    }

    // 2.- Funcion para abir el pop up de pago Declinado
    public void ShowNegativePopup(){
        epicDialog.setContentView(R.layout.epic_popup_negative);
        closePopupNegativeImg = (ImageView) epicDialog.findViewById(R.id.closePopupNegativeImgDude);
        btnRetry = (Button) epicDialog.findViewById(R.id.btnRetry);
        approvedResponseText = (TextView) epicDialog.findViewById(R.id.approvedResponseText);
        approvedPaymentDetailsText = (TextView) epicDialog.findViewById(R.id.approvedPaymentDetailsText);

        closePopupNegativeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }



}
