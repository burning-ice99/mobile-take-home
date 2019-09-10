package com.iceprojects.guestlogix_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EpisodesActivity extends AppCompatActivity implements AsyncDelegateInterface{


    EpisodeModel mEpisode;
    TextView headerTxt;
    RecyclerView rvAlive, rvDead;
    MyAdapter myAliveAdapter = null, myDeadAdapter = null;
    private final String charURLPrefix = "https://rickandmortyapi.com/api/character/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        mEpisode = getIncomingIntent();
        initUI();
        getCharacters(formCharacterURL());

    }

    private void initUI(){
        headerTxt = findViewById(R.id.episode_header_txt);
        headerTxt.setText(mEpisode.getEpisode() + " : " + mEpisode.getName());
        rvAlive = findViewById(R.id.recycler_alive);
        rvAlive.setLayoutManager(new LinearLayoutManager(this));
        rvDead = findViewById(R.id.recycler_dead);
        rvDead.setLayoutManager(new LinearLayoutManager(this));
    }

    private EpisodeModel getIncomingIntent(){

        Intent intent = getIntent();
        return (EpisodeModel) intent.getSerializableExtra("episode");

    }

    public void getCharacters(String URL){
        MyEpisodeHttpsRequest myHttpsRequest = new MyEpisodeHttpsRequest();
        myHttpsRequest.delegate = this;
        Log.d("TAG", "getCharacters: " + URL);
        myHttpsRequest.execute(URL);
    }

    public String formCharacterURL(){

        String[] charArray = mEpisode.getCharacters();
        String finalURL = charURLPrefix;
        for (int i = 0; i < charArray.length; i++){
            finalURL += charArray[i].substring(charArray[i].lastIndexOf('/') + 1);
            if (i == charArray.length-1){
                break;
            }
            finalURL += ",";
        }
        return finalURL;
    }

    @Override
    public void processFinishedData(JSONArray jo) {

        ArrayList<CharacterModel> charactersAlive = new ArrayList<>();
        ArrayList<CharacterModel> charactersDead = new ArrayList<>();

        try {
            if(jo != null && jo.length() > 0) {
                for (int i = 0; i < jo.length(); i++) {

                    CharacterModel character = new CharacterModel();
                    JSONObject object = jo.optJSONObject(i), subObject;

                    character.setId(object.has("id")? object.getInt("id") : -1);
                    character.setName(object.has("name")? object.getString("name") : "");
                    character.setStatus(object.has("status")? object.getString("status") : "");
                    character.setSpecies(object.has("species")? object.getString("species") : "");
                    character.setGender(object.has("gender")? object.getString("gender") : "");
                    character.setImageURL(object.has("image")? object.getString("image") : "");

                    subObject = object.has("origin")?object.getJSONObject("origin") : new JSONObject();
                    character.setOriginName(subObject.has("name")? subObject.getString("name") : "");

                    subObject = object.has("location")?object.getJSONObject("location") : new JSONObject();
                    character.setLocationName(subObject.has("name")? subObject.getString("name") : "");

                    if (character.getStatus().equalsIgnoreCase("alive")){
                        charactersAlive.add(character);
                    }
                    else if (character.getStatus().equalsIgnoreCase("dead")){
                        charactersDead.add(character);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        myAliveAdapter = new MyAdapter(charactersAlive, this);
        myDeadAdapter = new MyAdapter(charactersDead, this);
        //myAdapter.notifyDataSetChanged();
        rvAlive.setAdapter(myAliveAdapter);
        rvDead.setAdapter(myDeadAdapter);
    }

    @Override
    public void nextPageURL(String url) {

    }

    @Override
    public void prevPageURL(String url) {

    }
}
