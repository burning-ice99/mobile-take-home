package com.iceprojects.guestlogix_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class CharacterDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtDetails;
    CharacterModel characterModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        initUI();
        characterModel = getIncomingIntent();
        loadImage(characterModel.getImageURL());
        formatCharacterDetails(characterModel);

    }

    void initUI(){

        imageView = findViewById(R.id.img_character_dp);
        txtDetails = findViewById(R.id.character_details_txt);

    }

    CharacterModel getIncomingIntent(){

        Intent intent = getIntent();
        return (CharacterModel) intent.getSerializableExtra("character");

    }

    void loadImage(String URL){

        new DownloadImageTask(imageView).execute(URL);

    }

    void formatCharacterDetails(CharacterModel character){

        txtDetails.setText(character.toString());

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
