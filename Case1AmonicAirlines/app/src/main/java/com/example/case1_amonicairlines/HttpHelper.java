package com.example.case1_amonicairlines;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class HttpHelper extends AsyncTask<Void, Void, String> {
    String url;
    String method;
    HttpURLConnection connection;

    public HttpHelper(String urlParam, String methodParam) throws IOException {
        this.url = urlParam;
        this.method = methodParam;

        connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(method);
    }

    public void setRequestProperty(String key, String value) {
        this.connection.setRequestProperty(key, value);
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder result = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while(true){
                String line = reader.readLine();
                if(line == null) {
                    break;
                }
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
