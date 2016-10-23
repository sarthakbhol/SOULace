package com.bkcd.soulace.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import com.bkcd.soulace.sample.models.ImageUrl;
import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.R;

public class FullScreenImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);


        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = Integer.parseInt(i.getStringExtra("id"));

        android.widget.ImageView imageView = (android.widget.ImageView) findViewById(R.id.full_image_view);
        Picasso.with(getApplicationContext())
                .load(ImageUrl.getUrl().get(position))
                .into(imageView);

    }
}
