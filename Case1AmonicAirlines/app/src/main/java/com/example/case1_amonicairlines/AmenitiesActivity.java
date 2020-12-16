package com.example.case1_amonicairlines;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AmenitiesActivity extends AppCompatActivity {

    ListView listView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenities);

        listView = findViewById(R.id.amenitiesListView);
        btnBack = findViewById(R.id.amenitiesBtnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmenitiesActivity.super.onBackPressed();
            }
        });

        ArrayList<Amenity> amenities = new ArrayList<>();
        amenities.add(new Amenity("Extra Blanket", 10));
        amenities.add(new Amenity("Next Seat Free", 30));
        amenities.add(new Amenity("Two Neighboring Seats Free", 50));
        amenities.add(new Amenity("Tablet Rental", 12));
        amenities.add(new Amenity("Laptop Rental", 15));
        amenities.add(new Amenity("Lounge Access", 25));
        amenities.add(new Amenity("Soft Drinks", 0));
        amenities.add(new Amenity("Premium Headphones Rental", 5));
        amenities.add(new Amenity("Extra Bag", 15));
        amenities.add(new Amenity("Fast Checkin Lane", 10));
        amenities.add(new Amenity("Wi-Fi 50 mb", 0));
        amenities.add(new Amenity("Wi-Fi 250 mb", 25));

        listView.setAdapter(new AmenitiesListAdapter(AmenitiesActivity.this, amenities));
    }
}