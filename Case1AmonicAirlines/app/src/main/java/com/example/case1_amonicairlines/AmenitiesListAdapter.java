package com.example.case1_amonicairlines;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;

class AmenitiesListAdapter extends BaseAdapter {
    AmenitiesActivity amenitiesActivity;
    ArrayList<Amenity> amenities = new ArrayList<>();

    public AmenitiesListAdapter(AmenitiesActivity amenitiesActivityParam, ArrayList<Amenity> amenitiesParam) {
        this.amenitiesActivity = amenitiesActivityParam;
        this.amenities = amenitiesParam;
    }

    @Override
    public int getCount() {
        return amenities.size();
    }

    @Override
    public Object getItem(int i) {
        return -1;
    }

    @Override
    public long getItemId(int i) {
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(amenitiesActivity).inflate(R.layout.amenities_list_layout, null, false);

        if((i + 1) % 2 == 0) {
            ((ConstraintLayout)viewInflate.findViewById(R.id.amenitiesListLayoutConstraint)).setBackgroundColor(amenitiesActivity.getColor(R.color.colorLightGrey));
        } else {
            ((ConstraintLayout)viewInflate.findViewById(R.id.amenitiesListLayoutConstraint)).setBackgroundColor(amenitiesActivity.getColor(R.color.colorWhite));
        }

        ((TextView)viewInflate.findViewById(R.id.amenitiesListLayoutTxtService)).setText("Service: " + amenities.get(i).getService());
        int amenPrice = amenities.get(i).getPrice();
        if(amenPrice == 0) {
            ((TextView)viewInflate.findViewById(R.id.amenitiesListLayoutTxtPrice)).setText("Free");
        } else {
            ((TextView)viewInflate.findViewById(R.id.amenitiesListLayoutTxtPrice)).setText("$ " + amenPrice);
        }

        return viewInflate;
    }
}
