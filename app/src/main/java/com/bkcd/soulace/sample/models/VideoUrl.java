package com.bkcd.soulace.sample.models;

import java.util.ArrayList;

/**
 * Created by mahe on 05-10-2016.
 */
public class VideoUrl {
    private String label;
    private String url;
    private String thumbnail;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    static ArrayList<VideoUrl> Videolist=null;

    public static  ArrayList<VideoUrl>  Url(String abc)
    {
        if(Videolist == null)
        {
            Videolist=new ArrayList<VideoUrl>();
            VideoUrl a=new VideoUrl();
            if(abc.equals("anger"))
            {



            }
            else if(abc.equals("happiness"))
            {

                a.setUrl("https://www.youtube.com/watch?v=y6Sxv-sUYtM");
                a.setLabel("ABC");
                Videolist.add(a);
                a=new VideoUrl();
                a.setUrl("https://www.youtube.com/watch?v=d9TpRfDdyU0");
                a.setLabel("CDF");
                Videolist.add(a);


            }
            else if(abc.equals("sadness"))
            {

                a.setUrl("https://www.youtube.com/watch?v=AjZ0KbJcav0");
                a.setLabel("Self Management Techniques");
                Videolist.add(a);
                a=new VideoUrl();
                a.setUrl("https://www.youtube.com/watch?v=UX2tefQHNmk");
                a.setLabel("Self Management Techniques");
                Videolist.add(a);
                a=new VideoUrl();
                a.setUrl("https://www.youtube.com/watch?v=26U_seo0a1g");
                a.setLabel("Self Management Techniques");
                Videolist.add(a);
                a=new VideoUrl();
                a.setUrl("https://www.youtube.com/watch?v=bxXQsf92Nww\n");
                a.setLabel("Self Management Techniques");
                Videolist.add(a);



            }
            else if(abc.equals("fear"))
            {

                a.setUrl("https://www.youtube.com/watch?v=TmkUwToXLEc");
                a.setLabel("Self Management Techniques");
                Videolist.add(a);
                a=new VideoUrl();
                a.setUrl("https://www.youtube.com/watch?v=urzSKs-TmBc");
                a.setLabel("Self Management Techniques");
                Videolist.add(a);
                a=new VideoUrl();
                a.setUrl("https://www.youtube.com/watch?v=hlvZYZXTbwA");
                a.setLabel("Self Management Techniques");
                Videolist.add(a);

            }

        }


        return Videolist;
    }
    public static void makeNull()
    {
        Videolist=new ArrayList<VideoUrl>();
    }


}
