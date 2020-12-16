package com.example.case2_doglovergallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NeverActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<NeverImageList> neverImageList = new ArrayList<>();
    NeverListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_never);

        listView = findViewById(R.id.neverListView);

        try {
            for(int j = 0; j < 3; j++) {
                HttpHelper request = new HttpHelper("https://dog.ceo/api/breeds/image/random/9", "GET");
                request.setRequestProperty("Content-Type", "application/json");
                request.execute();

                JSONObject object = new JSONObject(request.get());
                JSONArray imageList = object.getJSONArray("message");

                ArrayList<NeverImage> neverImages = new ArrayList<>();
                for(int i = 0; i < imageList.length(); i++) {
                    neverImages.add(new NeverImage(imageList.getString(i)));
                }

                neverImageList.add(new NeverImageList(neverImages));
            }

            adapter = new NeverListAdapter(NeverActivity.this, neverImageList);
            listView.setAdapter(adapter);
        } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int terlewat, int nampak, int totalSemua) {
                if(terlewat + nampak >= totalSemua) {
                    try {
                        HttpHelper request = new HttpHelper("https://dog.ceo/api/breeds/image/random/9", "GET");
                        request.setRequestProperty("Content-Type", "application/json");
                        request.execute();

                        JSONObject object = new JSONObject(request.get());
                        JSONArray imageList = object.getJSONArray("message");

                        ArrayList<NeverImage> neverImages = new ArrayList<>();
                        for(int i = 0; i < imageList.length(); i++) {
                            neverImages.add(new NeverImage(imageList.getString(i)));
                        }

                        neverImageList.add(new NeverImageList(neverImages));

                        adapter.notifyDataSetChanged();
                    } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}