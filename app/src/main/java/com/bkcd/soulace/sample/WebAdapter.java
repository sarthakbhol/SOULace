package com.bkcd.soulace.sample;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bkcd.soulace.sample.models.WebUrl;
import com.yalantis.contextmenu.R;


import java.util.List;

public class WebAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<WebUrl> webItems;
    ImageLoader imageLoader = HttpQueue.getInstance(activity).getImageLoader();

    public WebAdapter(Activity activity, String webItems) {
        this.activity = activity;
        this.webItems = WebUrl.Url(webItems);
    }

    @Override
    public int getCount() {
        return webItems.size();
    }

    @Override
    public Object getItem(int location) {
        return webItems.get(location);
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
            convertView = inflater.inflate(R.layout.web_view_card, null);

        if (imageLoader == null)
            imageLoader = HttpQueue.getInstance(activity).getImageLoader();

        TextView title = (TextView) convertView.findViewById(R.id.news_label);
        TextView content = (TextView) convertView.findViewById(R.id.news_con);


        // getting movie data for the row
        WebUrl m = webItems.get(position);
        title.setText(m.getLabel());
        content.setText(m.getUrl());


        return convertView;
    }

}