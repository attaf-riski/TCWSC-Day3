package com.example.case1_amonicairlines;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ReserveActivity extends AppCompatActivity {

    EditText txtTicket;
    ImageView imgSeat;
    Button btnNext;
    Button btnReserveSeat;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        txtTicket = findViewById(R.id.reserveTxtTicket);
        imgSeat = findViewById(R.id.reserveImg);
        btnNext = findViewById(R.id.reserveBtnNext);
        btnReserveSeat = findViewById(R.id.reserveBtnReserve);
        btnBack = findViewById(R.id.reserveBtnBack);

        final Bitmap bitmapSeat = null;
        imgSeat.setImageBitmap(bitmapSeat);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtTicket.getText().toString().trim().equals("")) {
                    try {
                        HttpHelper request = new HttpHelper("http://10.0.2.2:5000/api/ticket/" + txtTicket.getText().toString(), "GET");
                        request.setRequestProperty("Content-Type", "application/json");
                        request.execute();

                        JSONObject object = new JSONObject(request.get());
                        Bitmap bitmapShow = null;
                        if(object.getString("type").equals("business")) {
                            bitmapShow = BitmapFactory.decodeResource(getResources(), R.drawable.amonicbusiness);
                        } else if(object.getString("type").equals("first")) {
                            bitmapShow = BitmapFactory.decodeResource(getResources(), R.drawable.amonicfirstclass);
                        } else {
                            Toast.makeText(ReserveActivity.this, "This ticket's cabin type is not available", Toast.LENGTH_SHORT).show();
                        }

                        imgSeat.setImageBitmap(bitmapShow);
                    } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ReserveActivity.this, "Fill up ticket number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReserveSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReserveActivity.super.onBackPressed();
            }
        });
    }
}