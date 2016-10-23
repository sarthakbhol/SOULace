package com.bkcd.soulace.sample.models;

import java.util.ArrayList;

/**
 * Created by mahe on 05-10-2016.
 */
public class WebUrl {

    private String label;
    private String url;
    private String snippet;

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
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

    static ArrayList<WebUrl> weblist=null;

    public static  ArrayList<WebUrl>  Url(String abc)
    {
        if(weblist == null)
        {
            weblist=new ArrayList<WebUrl>();
            if(abc.equals("angry"))
            {
              /* WebUrl a=new WebUrl();
                a.setUrl("http://www.skillsyouneed.com/ps/anger-management.html");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/anger-management/art-20045434");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/anger-management/art-20045434");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.blissful-wisdom.com/anger-management.html");
                a.setLabel("Self Management Techniques");
                weblist.add(a);*/



            }
            else if(abc.equals("happy"))
            {
               /* WebUrl a=new WebUrl();
                a.setUrl("http://www.kidsworldfun.com/short-story-the-secret-of-happiness.php");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.inspirationalstories.eu/stories/inspirational-stories-about-happiness/");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.speakingtree.in/allslides/learn-these-4-secrets-of-chanakya-for-a-happier-life");
                a.setLabel("Self Management Techniques");
                weblist.add(a);*/

            }
            else if(abc.equals("sad"))
            {
               /* WebUrl a=new WebUrl();
                a.setUrl("http://www.brainyquote.com/quotes/topics/topic_inspirational.html");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.oprah.com/spirit/cures-for-sadness-learn-something-new");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.lifehack.org/articles/lifestyle/fifteen-simple-ways-overcome-depression-and-sadness.html");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.positivityblog.com/index.php/2013/08/20/happiness-quotes/");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.sunnyskyz.com/feel-good-stories");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
*/

            }
            else if(abc.equals("fear"))
            {
                /*WebUrl a=new WebUrl();
                a.setUrl("http://elitedaily.com/life/motivation/feeling-afraid-five-good-reasons-move-forward-anyway/802579/");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("https://www.qimacros.com/knowware-articles/fear-motivation/");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("https://www.psychologytoday.com/blog/the-main-ingredient/200909/the-most-powerful-motivator");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.positivityblog.com/index.php/2007/05/11/22-inspirational-quotes-on-fear/");
                a.setLabel("Self Management Techniques");
                weblist.add(a);
                a.setUrl("http://www.brainyquote.com/quotes/topics/topic_fear.html");
                a.setLabel("Self Management Techniques");
                weblist.add(a);*/

            }

        }
        return weblist;
    }
    public static void makeNull()
    {
        weblist=new ArrayList<WebUrl>();
    }
}
