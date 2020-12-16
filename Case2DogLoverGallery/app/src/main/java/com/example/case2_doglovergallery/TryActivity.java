package com.example.case2_doglovergallery;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class TryActivity extends AppCompatActivity {

    Spinner comboBreed;
    ImageView img;
    Button btnNext;
    JSONArray breedsJSONArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        comboBreed = findViewById(R.id.tryComboBreed);
        img = findViewById(R.id.tryImg);
        btnNext = findViewById(R.id.tryBtnNext);

        img.setImageBitmap(null);

        breedsJSONArray.put("All option");

        try {
            HttpHelper request = new HttpHelper("https://dog.ceo/api/breeds/list/all", "GET");
            request.setRequestProperty("Content-Type", "application/json");
            request.execute();

            JSONObject object = new JSONObject(request.get());
            JSONObject allBreedsObject = object.getJSONObject("message");

            Iterator<String> allBreedKeys = allBreedsObject.keys();
            while(allBreedKeys.hasNext()) {
                String breed = allBreedKeys.next();

                JSONArray breedChildren = allBreedsObject.getJSONArray(breed);
                if(breedChildren.length() > 0) {
                    for(int i = 0; i < breedChildren.length(); i++) {
                        breedsJSONArray.put(breed + "-" + breedChildren.getString(i));
                    }
                } else {
                    breedsJSONArray.put(breed);
                }
            }
        } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        comboBreed.setAdapter(new ComboBoxAdapter(TryActivity.this, breedsJSONArray));

        comboBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LoadGambar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadGambar();
            }
        });
    }

    private void LoadGambar() {
        try {
            JSONObject imgShowObject = new JSONObject();
            String urlImageShow = "";
            String requestURL = "";
            if(breedsJSONArray.getString(comboBreed.getSelectedItemPosition()).equals("All option")) {
                requestURL = "https://dog.ceo/api/breeds/image/random";
            } else if(breedsJSONArray.getString(comboBreed.getSelectedItemPosition()).contains("-")) {
                String selectedBreedParent = breedsJSONArray.getString(comboBreed.getSelectedItemPosition()).split("-")[0];
                String selectedBreedChild = breedsJSONArray.getString(comboBreed.getSelectedItemPosition()).split("-")[1];
                requestURL = "https://dog.ceo/api/breed/" + selectedBreedParent + "/" + selectedBreedChild + "/images/random";
            } else {
                String selectedBreed = breedsJSONArray.getString(comboBreed.getSelectedItemPosition());
                requestURL = "https://dog.ceo/api/breed/" + selectedBreed + "/images/random";
            }

            HttpHelper request = new HttpHelper(requestURL, "GET");
            request.setRequestProperty("Content-Type", "application/json");
            request.execute();

            imgShowObject = new JSONObject(request.get());
            urlImageShow = imgShowObject.getString("message");

            final String finalUrlImageShow = urlImageShow;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(finalUrlImageShow);
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
        } catch (JSONException | IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}