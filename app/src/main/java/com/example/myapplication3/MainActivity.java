package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView cardViewDirectPay;
    private CardView cardViewCancelation;
    private CardView cardViewCheckIn;
    private CardView cardViewReauto;
    private CardView cardViewCheckOut;
    private CardView cardViewAyuda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardViewDirectPay = (CardView)  findViewById(R.id.cardViewDirectPay);
        cardViewCancelation = (CardView)  findViewById(R.id.cardViewCancelPay);
        cardViewCheckIn = (CardView) findViewById(R.id.cardViewCheckIn);
        cardViewReauto = (CardView) findViewById(R.id.cardViewReauth);
        cardViewCheckOut = (CardView) findViewById(R.id.cardViewCheckOut);
        cardViewAyuda = (CardView) findViewById(R.id.cardViewAyuda);


        // SE crea el listener del OnClick para abrir la Activity de Pago Dircecto ( ThirdActivity)
        cardViewDirectPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });


        //Se crea el listener del Onclick para abrir la Activity de Cancelacion

        cardViewCancelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCancel = new Intent(MainActivity.this, Cancelacion.class);
                startActivity(intentCancel);
            }
        });

        //Se crea el listener del Onclick para abrir la Activity de Check In

        cardViewCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCheckInbaby = new Intent(MainActivity.this, CheckIn.class);
                startActivity(intentCheckInbaby);
            }
        });

        //Se crea el listener del Onclick para abrir la Activity de la Reautorizacion

        cardViewReauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReauto = new Intent(MainActivity.this, Reautorizacion.class);
                startActivity(intentReauto);
            }
        });


        //Se crea el listener del Onclick para abrir la Activity del Checkout

        cardViewCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCheckOut = new Intent(MainActivity.this, CheckOut.class);
                startActivity(intentCheckOut);
            }
        });


        //Se crea el listener del Onclick para abrir la Avtivity de "Ayuda", la cual abre el pedo de los pop ups genericos ----ONLY FOR TEST PURPOSES MEN

        cardViewAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAyuda = new Intent(MainActivity.this, CustomPopupScreen.class);
                startActivity(intentAyuda);

            }
        });



    }
}
