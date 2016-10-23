package com.bkcd.soulace.sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bkcd.soulace.sample.models.VideoUrl;
import com.yalantis.contextmenu.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VideoSearchActivity extends AppCompatActivity {

    String abc,query;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_search);
       // Toast.makeText(this, "Video Activity", Toast.LENGTH_SHORT).show();

        list=(ListView)findViewById(R.id.video);

        Intent it=getIntent();
        abc=it.getStringExtra("SEARCH");
        abc=abc.toLowerCase();
       if(abc.equals("anger"))
        {
            abc="Anger Management videos";
        }
        else if (abc.equals("surprise"))
        {
            abc="matthew santoro videos";
        }
        else if (abc.equals("sadness"))
        {
            abc="tvf videos";
        }else if(abc.equals("disgust")){
            abc="travel stories videos";
        }
        else if (abc.equals("fear"))
        {
            abc="fear inspirational videos";
        }
        else if(abc.equalsIgnoreCase("happiness")||abc.equalsIgnoreCase("neutral"))
        {
            abc="what is true happiness videos";
        }

        //Toast.makeText(this, abc, Toast.LENGTH_SHORT).show();
        try {
            query = URLEncoder.encode(abc, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        getData();
    }
    public void getData()
    {
        String tag_json_obj = "json_obj_req";
        final String TAG="Image Error";

        String url = "https://api.cognitive.microsoft.com/bing/v5.0/search?q="+query+"&count=10&mkt=en-IN";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonImageObjReq = new JsonObjectRequest(Request.Method.GET,
                url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());

                       // Toast.makeText(VideoSearchActivity.this, "Recieved", Toast.LENGTH_SHORT).show();
                        new JSONParsing().startVideo(response,abc);

                        list.setAdapter(new VideoAdapter(VideoSearchActivity.this,abc.toLowerCase()));

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                              //  Toast.makeText(VideoSearchActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                                ArrayList<VideoUrl> w= VideoUrl.Url(abc.toLowerCase());
                                VideoUrl q=w.get(position);
                                Intent it =new Intent(VideoSearchActivity.this,WebViewActivity.class);
                                it.putExtra("URL",q.getUrl());
                                startActivity(it);

                            }
                        });


                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }


        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Ocp-Apim-Subscription-Key", "3c3926cfa3974813b06835dfe601d599");
                return headers;
            }


        };

// Adding request to request queue
        HttpQueue.getInstance(getApplicationContext()).addRequest(jsonImageObjReq);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        VideoUrl.makeNull();
    }
}
