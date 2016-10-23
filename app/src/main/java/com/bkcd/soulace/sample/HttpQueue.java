package com.bkcd.soulace.sample;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bkcd.soulace.sample.Utils.LruBitmapCache;


/**
 * Created by SarThak Bhol on 22-07-2016.
 */
public class HttpQueue {
    private static RequestQueue requestQueue;
    private static HttpQueue httpQueue;
    private static Context context;
    private ImageLoader mImageLoader;

    private HttpQueue(Context context)
    {
        this.context=context;
        requestQueue=getRequestQueue();
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(requestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized HttpQueue getInstance(Context context)
    {
        if(httpQueue == null)
        {
            httpQueue=new HttpQueue(context);
        }
        return httpQueue;
    }
    public void cancelPendingRequests() {
        requestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                Log.d("DEBUG","request running: "+request.getTag().toString());
                return true;
            }
        });

    }

    public<T> void addRequest(Request<T> request)
    {
        requestQueue.add(request);
    }

}
