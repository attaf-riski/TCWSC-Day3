package com.example.case2_doglovergallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

class ComboBoxAdapter extends BaseAdapter {
    Context context;
    JSONArray jsonArray;

    public ComboBoxAdapter(Context contextParam, JSONArray jsonArrayParam) {
        this.context = contextParam;
        this.jsonArray = jsonArrayParam;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
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
            ((TextView)viewInflate.findViewById(R.id.comboBoxTxt)).setText(jsonArray.getString(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return viewInflate;
    }
}
