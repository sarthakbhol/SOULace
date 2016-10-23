package com.bkcd.soulace.sample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bkcd.soulace.sample.models.VideoUrl;
import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.R;

import java.util.List;

public class VideoAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<VideoUrl> videoItems;
    ImageLoader imageLoader = HttpQueue.getInstance(activity).getImageLoader();

    public VideoAdapter(Activity activity, String videoItems) {
        this.activity = activity;
        this.videoItems = VideoUrl.Url(videoItems);
    }

    @Override
    public int getCount() {
        return videoItems.size();
    }

    @Override
    public Object getItem(int location) {
        return videoItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.web_view_card_thumbnail, null);

        if (imageLoader == null)
            imageLoader = HttpQueue.getInstance(activity).getImageLoader();

        TextView title = (TextView) convertView.findViewById(R.id.web_search_title);
        TextView content = (TextView) convertView.findViewById(R.id.web_search_content);
        android.widget.ImageView imageView=(android.widget.ImageView)convertView.findViewById(R.id.thumbnail);

        // getting movie data for the row
        VideoUrl m = videoItems.get(position);
        title.setText(m.getLabel());
        content.setText(m.getUrl());
        Picasso.with(activity)
                .load(videoItems.get(position).getThumbnail())
                .into(imageView);


        return convertView;
    }

}