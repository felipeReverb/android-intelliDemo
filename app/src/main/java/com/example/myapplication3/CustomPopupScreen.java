package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomPopupScreen extends AppCompatActivity {

    private Dialog epicDialog;
    private Button positivePopupBtn;
    private Button negativePopupBtn;
    private Button btnAccept;
    private Button btnRetry;
    private TextView approvedResponseText;
    private TextView approvedPaymentDetailsText;
    private TextView deniedResponseText;
    private TextView deniedPaymentDetailsText;
    private ImageView closePopupPositiveImg;
    private ImageView closePopupNegativeImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_popup_screen);

        epicDialog = new Dialog(this);

        positivePopupBtn = (Button) findViewById(R.id.positiveBtn);
        negativePopupBtn = (Button) findViewById(R.id.negativeBtn);

        // Listeners de los Botones que invocan los popups

        positivePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPositivePopup();
            }
        });

        negativePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNegativePopup();
            }
        });


    }

    //Woeeeeee ----- Aqui van las funciones que utilizare dentro del Oncreate


    // 1.- Funcion para abir el pop up de pago Abrobado
    public void ShowPositivePopup(){

        epicDialog.setContentView(R.layout.epic_popup_positive);
        closePopupPositiveImg = (ImageView) epicDialog.findViewById(R.id.closePopupPositiveImg);
        btnAccept = (Button) epicDialog.findViewById(R.id.btnAccept);
        approvedResponseText = (TextView) epicDialog.findViewById(R.id.approvedResponseText);
        approvedPaymentDetailsText = (TextView) epicDialog.findViewById(R.id.approvedPaymentDetailsText);

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

        /*btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });*/


        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }












}
