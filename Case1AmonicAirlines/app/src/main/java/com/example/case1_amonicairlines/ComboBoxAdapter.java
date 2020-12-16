package com.example.case1_amonicairlines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ComboBoxAdapter extends BaseAdapter {
    JSONArray airportJSONArray;
    Context context;

    public ComboBoxAdapter(Context contextParam, JSONArray jsonArrayParam) {
        this.context = contextParam;
        this.airportJSONArray = jsonArrayParam;
    }

    @Override
    public int getCount() {
        return airportJSONArray.length();
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
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.combo_box_layout, null, false);

        try {
            JSONObject object = airportJSONArray.getJSONObject(i);
            ((TextView)viewInflate.findViewById(R.id.comboBoxTxt)).setText(object.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return viewInflate;
    }
}
