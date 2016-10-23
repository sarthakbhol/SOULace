package com.bkcd.soulace.sample;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.bkcd.soulace.sample.models.ImageUrl;

import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.R;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;

    ArrayList<String> mThumbIds= ImageUrl.getUrl();

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.image_item, null);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView);
        Picasso.with(mContext)
                .load(mThumbIds.get(position))
                .into(imageView);
        return imageView;
    }

}