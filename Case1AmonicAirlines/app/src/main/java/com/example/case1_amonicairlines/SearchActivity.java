package com.example.case1_amonicairlines;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class SearchActivity extends AppCompatActivity {

    Spinner comboFrom;
    Spinner comboTo;
    EditText txtDate;
    Button btnSearch;
    ListView listView;
    Button btnBack;
    JSONArray airportJSONArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        comboFrom = findViewById(R.id.searchComboFrom);
        comboTo = findViewById(R.id.searchComboTo);
        txtDate = findViewById(R.id.searchTxtDate);
        btnSearch = findViewById(R.id.searchBtnSearch);
        listView = findViewById(R.id.searchListView);
        btnBack = findViewById(R.id.searchBtnBack);

        try {
            HttpHelper request = new HttpHelper("http://10.0.2.2:5000/api/port/list", "GET");
            request.setRequestProperty("Content-Type", "application/json");
            request.execute();

            airportJSONArray = new JSONArray(request.get());
            comboFrom.setAdapter(new ComboBoxAdapter(SearchActivity.this, airportJSONArray));
            comboTo.setAdapter(new ComboBoxAdapter(SearchActivity.this, airportJSONArray));
        } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String format = "dd/MM/yyyy";
                SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);

                txtDate.setText(formatter.format(calendar.getTime()));
            }
        };

        txtDate.setKeyListener(null);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SearchActivity.this,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(null);
                if(comboFrom.getSelectedItemPosition() != comboTo.getSelectedItemPosition()) {
                    if(!txtDate.getText().toString().trim().equals("")) {
                        try {
                            int fromAirportID = airportJSONArray.getJSONObject(comboFrom.getSelectedItemPosition()).getInt("id");
                            int toAirportID = airportJSONArray.getJSONObject(comboTo.getSelectedItemPosition()).getInt("id");
                            String[] dateSplit = txtDate.getText().toString().split("/");
                            String date = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];

                            HttpHelper request = new HttpHelper("http://10.0.2.2:5000/api/schedule/list?from=" + fromAirportID + "&to=" + toAirportID + "&date=" + date, "GET");
                            request.setRequestProperty("Content-Type", "application/json");
                            request.execute();

                            JSONArray scheduleJSONArray = new JSONArray(request.get());
                            listView.setAdapter(new SearchListAdapter(SearchActivity.this, scheduleJSONArray, txtDate.getText().toString()));
                        } catch (JSONException | IOException | InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(SearchActivity.this, "Select a date", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SearchActivity.this, "Deparature airport and arrival airport can't be the same", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.super.onBackPressed();
            }
        });
    }
}