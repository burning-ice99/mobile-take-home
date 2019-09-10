package com.iceprojects.guestlogix_mobile;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class MyHttpsRequest extends AsyncTask<String, Integer, JSONObject> {

    private static final String TAG = "MyHttpsRequest";
    AsyncDelegateInterface delegate = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        String my_url = strings[0];

        HttpURLConnection connection = null;
        BufferedReader reader;
        JSONObject jsonResponse = new JSONObject();

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
            jsonResponse = new JSONObject(stringBuffer.toString());


            // to log the response code of your request
            Log.d("TAG", "MyHttpRequestTask doInBackground : " +connection.getRequestMethod());
            Log.d("TAG", "MyHttpRequestTask doInBackground : " +jsonResponse);
            Log.d("TAG", "MyHttpRequestTask doInBackground : " +connection.getResponseCode());
            // to log the response message from your server after you have tried the request.
            Log.d("TAG", "MyHttpRequestTask doInBackground : " +connection.getResponseMessage());


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // this is done so that there are no open connections left when this task is going to complete
            connection.disconnect();
        }

        return jsonResponse;
    }

    @Override
    protected void onPostExecute(JSONObject jo) {
        super.onPostExecute(jo);

        JSONObject info = new JSONObject();

        try {
            if (jo.has("info")) {
                info = jo.getJSONObject("info");
            }
            if (info.has("next")){
                delegate.nextPageURL(info.getString("next"));
                Log.d(TAG, "onPostExecute: " + info.getString("next"));
            }
            if (info.has("prev")){
                delegate.prevPageURL(info.getString("prev"));
                Log.d(TAG, "onPostExecute: " + info.getString("prev"));
            }
            if (jo.has("results")){
                delegate.processFinishedData(jo.getJSONArray("results"));
                Log.d(TAG, "onPostExecute: " + jo.getJSONArray("results"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
