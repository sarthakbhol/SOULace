package com.bkcd.soulace.sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bkcd.soulace.sample.models.ImageUrl;
import com.yalantis.contextmenu.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ImageSearchActivity extends AppCompatActivity {

    String abc ;
    ListView listView;
    ImageAdapter adapter;
    String query;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);

        gridView = (GridView) findViewById(R.id.grid_view);
        Intent it=getIntent();
        abc=it.getStringExtra("SEARCH");
        //abc="be like bro ";
        abc.toLowerCase();
        if(abc.equalsIgnoreCase("anger"))
        {
            abc="tranquil places";
        }
        else if (abc.equalsIgnoreCase("surprise"))
        {
            abc="amazing images";
        }
        else if (abc.equalsIgnoreCase("sadness"))
        {
            abc="be like bro";
        }else if(abc.equalsIgnoreCase("disgust")){
            abc="beautiful sceneries";
        }
        else if (abc.equalsIgnoreCase("fear"))
        {
            abc="inspiring quotes";
        }
        else if(abc.equalsIgnoreCase("happiness")||abc.equalsIgnoreCase("neutral"))
        {
            abc="happiness quotes";
        }
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
                        new JSONParsing().startImage(response);


                        // Instance of ImageAdapter Class
                        gridView.setAdapter(new ImageAdapter(ImageSearchActivity.this));
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent it =new Intent(ImageSearchActivity.this,FullScreenImage.class);
                                it.putExtra("id",position+"");
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
                headers.put("Ocp-Apim-Subscription-Key", "b6f9385fe4384448bd2cfaa15ebd06e4");
                return headers;
            }


        };




// Adding request to request queue
        HttpQueue.getInstance(getApplicationContext()).addRequest(jsonImageObjReq);




    }

    @Override
    public void onBackPressed() {

        ImageUrl.makeNull();
        this.finish();
    }
}



