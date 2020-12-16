package com.example.case1_amonicairlines;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    Button btnBack;
    TextView txtAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnBack = findViewById(R.id.aboutBtnBack);
        txtAbout = findViewById(R.id.aboutTxtAbout);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutActivity.super.onBackPressed();
            }
        });

        String about = "AMONIC Airlines is a new airline based in Abu Dhabi, UAE and is trying to bring a flying option to those with fun and active lifestyles, with brand new planes, attractive fares, top-notch service, and a host of fun, innovative amenities. We plan to reinvent regional air travel in our image. \n" +
                "The AMONIC Airlines mission statement is to connect people to what is important in their lives through friendly, reliable, and low-cost air travel. While keeping our status as the region’s youngest and most vibrant, AMONIC Airlines makes flying more fun.";

        txtAbout.setText(about);
    }
}