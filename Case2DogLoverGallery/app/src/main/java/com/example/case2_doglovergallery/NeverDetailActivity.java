package com.example.case2_doglovergallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NeverDetailActivity extends AppCompatActivity {

    TextView txtTitle;
    ListView listView;
    ArrayList<NeverImage> neverImages = new ArrayList<>();
    String breedCode = "";
    String requestURL = "";
    NeverDetailListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_never_detail);

        txtTitle = findViewById(R.id.neverDetailTitle);
        listView = findViewById(R.id.neverDetailListView);

        breedCode = getIntent().getStringExtra("breed");
        txtTitle.setText("Never-Ending Cuteness\n" + breedCode);

        if(breedCode.contains("-")) {
            String breedParent = breedCode.split("-")[0];
            String breedChild = breedCode.split("-")[1];
            requestURL = "https://dog.ceo/api/breed/" + breedParent + "/" + breedChild + "/images/random";
        } else {
            requestURL = "https://dog.ceo/api/breed/" + breedCode + "/images/random";;
        }

        try {
            for(int i = 0; i < 3; i++) {
                HttpHelper request = new HttpHelper(requestURL, "GET");
                request.setRequestProperty("Content-Type", "application/json");
                request.execute();

                JSONObject object = new JSONObject(request.get());
                String urlImage = object.getString("message");

                neverImages.add(new NeverImage(urlImage));
            }
        } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        adapter = new NeverDetailListAdapter(NeverDetailActivity.this, neverImages);
        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int terlewat, int nampak, int totalSemuanya) {
                if(terlewat + nampak >= totalSemuanya) {
                    try {
                        HttpHelper request = new HttpHelper(requestURL, "GET");
                        request.setRequestProperty("Content-Type", "application/json");
                        request.execute();

                        JSONObject object = new JSONObject(request.get());
                        String urlImage = object.getString("message");

                        neverImages.add(new NeverImage(urlImage));
                        adapter.notifyDataSetChanged();
                    } catch (IOException | InterruptedException | ExecutionException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}