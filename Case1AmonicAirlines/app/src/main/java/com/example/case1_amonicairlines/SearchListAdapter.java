package com.example.case1_amonicairlines;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SearchListAdapter extends BaseAdapter {
    SearchActivity searchActivity;
    JSONArray jsonArray;
    String date;

    public SearchListAdapter(SearchActivity searchActivityParam, JSONArray jsonArrayParam, String dateParam) {
        this.searchActivity = searchActivityParam;
        this.jsonArray = jsonArrayParam;
        this.date = dateParam;
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
        View viewInflate = LayoutInflater.from(searchActivity).inflate(R.layout.search_list_layout, null, false);

        try {
            JSONObject object = jsonArray.getJSONObject(i);
            ((TextView)viewInflate.findViewById(R.id.searchListLayoutTxtFlightNumber)).setText("Flight Number : " + object.getString("flightNumber"));
            ((TextView)viewInflate.findViewById(R.id.searchListLayoutTxtPrice)).setText("Price : " + object.getString("price") + "$");
            String[] timeSplit = object.getString("time").split(":");
            ((TextView)viewInflate.findViewById(R.id.searchListLayoutTxtTimeAircraft)).setText("Time : " + String.format("%2s",timeSplit[0]).replace(" ", "0") + ":" +  String.format("%2s",timeSplit[1]).replace(" ", "0") + " Aircraft : " + object.getString("aircraft"));
            ((TextView)viewInflate.findViewById(R.id.searchListLayoutTxtDate)).setText(date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return viewInflate;
    }
}
