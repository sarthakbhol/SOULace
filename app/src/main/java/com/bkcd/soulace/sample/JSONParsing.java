package com.bkcd.soulace.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;

import com.bkcd.soulace.sample.models.ImageUrl;
import com.bkcd.soulace.sample.models.VideoUrl;
import com.bkcd.soulace.sample.models.WebUrl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mahe on 04-10-2016.
 */
public class JSONParsing {

    Activity activity;

    AlertDialog.Builder builder;

    public void startImage(JSONObject data)
    {
        try{
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++ IMAGES +++++++++++++++++++++++++++++++++");
            JSONObject a=data.getJSONObject("images");
            JSONArray url=a.getJSONArray("value");
            ArrayList<String> imageurl= ImageUrl.getUrl();
           int i;

            for(i=0;i<url.length();i++)
            {
                imageurl.add(url.getJSONObject(i).getString("contentUrl"));
                Log.e("++++Image List +++++++",url.getJSONObject(i).getString("contentUrl"));
            }
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

           // Toast.makeText(activity, "done", Toast.LENGTH_SHORT).show();




        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void startWeb(JSONObject data,String emotion)
    {
        try{
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++ WEBPAGES +++++++++++++++++++++++++");
            JSONObject a=data.getJSONObject("webPages");
            JSONArray url=a.getJSONArray("value");
            ArrayList<WebUrl> weburl=WebUrl.Url(emotion.toLowerCase());
            int i;

            for(i=0;i<url.length();i++)
            {
               WebUrl web=new WebUrl();
                web.setLabel(url.getJSONObject(i).getString("name"));
                web.setUrl(url.getJSONObject(i).getString("displayUrl"));
                weburl.add(web);

            }
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            // Toast.makeText(activity, "done", Toast.LENGTH_SHORT).show();




        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void startVideo(JSONObject data,String emotion)
    {
        try{
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++ Videos +++++++++++++++++++++++++");
            JSONObject a=data.getJSONObject("videos");
            JSONArray url=a.getJSONArray("value");
            ArrayList<VideoUrl> weburl=VideoUrl.Url(emotion.toLowerCase());
            Log.e("-------------","+++++++++++++++++++++++++++++ Videos +++++++++++++++++++++++++");
            int i;

            for(i=0;i<url.length();i++)
            {
                VideoUrl web=new VideoUrl();
                web.setLabel(url.getJSONObject(i).getString("name"));
                web.setUrl(url.getJSONObject(i).getString("contentUrl"));
                web.setThumbnail(url.getJSONObject(i).getString("thumbnailUrl"));
                web.setDesc(url.getJSONObject(i).getString("description"));
                weburl.add(web);

            }
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.e("-------------","+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            // Toast.makeText(activity, "done", Toast.LENGTH_SHORT).show();




        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}



