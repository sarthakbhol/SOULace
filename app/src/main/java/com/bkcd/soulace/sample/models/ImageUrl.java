package com.bkcd.soulace.sample.models;

import java.util.ArrayList;

/**
 * Created by mahe on 04-10-2016.
 */
public class ImageUrl {
   static ArrayList<String> imagelist=null;

   public static  ArrayList<String>  getUrl()
   {
        if(imagelist == null)
        {
            imagelist=new ArrayList<String>();

        }
       return imagelist;
   }
    public static void makeNull()
    {
        imagelist=new ArrayList<String>();
    }
}
