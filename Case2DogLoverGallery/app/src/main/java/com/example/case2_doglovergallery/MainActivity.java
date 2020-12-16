package com.example.case2_doglovergallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btnTry;
    Button btnNever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.mainImg);
        btnTry = findViewById(R.id.mainBtnTry);
        btnNever = findViewById(R.id.mainBtnNever);

        img.setImageBitmap(null);

        try {
            HttpHelper request = new HttpHelper("https://dog.ceo/api/breeds/image/random", "GET");
            request.setRequestProperty("Content-Type", "application/json");
            request.execute();

            JSONObject object = new JSONObject(request.get());
            final String urlImage = object.getString("message");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(urlImage);
                        final Bitmap bitmapShow = BitmapFactory.decodeStream(url.openStream());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                img.setImageBitmap(bitmapShow);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TryActivity.class);
                startActivity(intent);
            }
        });

        btnNever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NeverActivity.class);
                startActivity(intent);
            }
        });
    }
}