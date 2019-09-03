package com.iceprojects.guestlogix_mobile;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncDelegateInterface{

    RecyclerView recyclerView;
    MyAdapter myAdapter = null;
    private static final String URL = "https://rickandmortyapi.com/api/episode/";
    String[] strings = {"Baby 1", "Baby 2", "Baby 3", "Baby 4", "Baby 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getAllEpisodes(URL);
        initUI();

    }

    private void initUI(){

        //myAdapter = new MyAdapter(this, null);
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(myAdapter);

    }

    private void getAllEpisodes(String url){

        MyHttpsRequest myHttpsRequest = new MyHttpsRequest();
        myHttpsRequest.delegate = this;
        myHttpsRequest.execute(url);

    }

    @Override
    public void processFinishedData(JSONArray jo) {

        ArrayList<EpisodeModel> episodeList = new ArrayList<>();

        try {
            if(jo!=null && jo.length()>0) {
                for (int i = 0; i < jo.length(); i++) {
                    EpisodeModel episode = new EpisodeModel();
                    JSONObject object = jo.optJSONObject(i);
                    episode.setiD(object.has("id")? object.getInt("id") : -1);
                    episode.setName(object.has("name")? object.getString("name") : "");
                    episode.setAirdate(object.has("air_date")? object.getString("air_date") : "");
                    episode.setDetailsURL(object.has("url")? object.getString("url") : "");
                    episode.setEpisode(object.has("episode")? object.getString("episode") : "");
                    episode.setCharacters(toStringArray(object.has("characters")? object.getJSONArray("characters") : null));

                    episodeList.add(episode);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        myAdapter = new MyAdapter(this, episodeList);
        //myAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void nextPageURL(String url) {

        getAllEpisodes(url);

    }

    public static String[] toStringArray(JSONArray array) {
        if(array==null)
            return null;

        String[] arr=new String[array.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.optString(i);
        }
        return arr;
    }
}
