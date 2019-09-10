package com.iceprojects.guestlogix_mobile;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyEpisodeHttpsRequest extends AsyncTask<String, Integer, JSONArray> {

    private static final String TAG = "MyEpisodeHttpsRequest";
    AsyncDelegateInterface delegate = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(String... strings) {

        String my_url = strings[0];

        HttpURLConnection connection = null;
        BufferedReader reader;
        JSONArray jsonResponse = new JSONArray();

        try {

            URL url = new URL(my_url);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            StringBuffer stringBuffer = new StringBuffer();
            String responseString = "";

            while((responseString = reader.readLine()) != null)
            {
                stringBuffer.append(responseString);
            }
            jsonResponse = new JSONArray(stringBuffer.toString());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // this is done so that there are no open connections left when this task is going to complete
            connection.disconnect();
        }

        return jsonResponse;
    }

    @Override
    protected void onPostExecute(JSONArray jo) {
        super.onPostExecute(jo);

        delegate.processFinishedData(jo);
        Log.d(TAG, "onPostExecute: " + jo);

    }

}
