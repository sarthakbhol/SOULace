package com.bkcd.soulace.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yalantis.contextmenu.R;

public class MainFragment extends Fragment {


   String emotion;

    String emotionResult,emotionAnalysis;
    TextView tv1,tv2,tv3;
    ImageView im;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tv1=(TextView) rootView.findViewById(R.id.emotion_result);
        tv2=(TextView) rootView.findViewById(R.id.emotion_analysis);
        tv3=(TextView) rootView.findViewById(R.id.suggestions);
        im=(ImageView) rootView.findViewById(R.id.mood_image);
        Intent i=getActivity().getIntent();
         emotionAnalysis=i.getStringExtra("Emotion Analysis");
         emotionResult=i.getStringExtra("Emotion Result");
        if (emotionResult.equalsIgnoreCase("Anger"))
        {
            tv1.setText("Seems like you had a rough day! Here is our analysis of your expressions:\n");
            tv2.setText(emotionAnalysis);
            im.setImageResource(R.drawable.w_s_s);
            tv3.setText("\n\nOkay then! Lets turn that frown upside down!! Click on the Navigation icon on the right, and choose what can cool you down!\n\nP.S: You don't look nice when angry! Cheer up kiddo!!\n\n");
        }

        if (emotionResult.equalsIgnoreCase("Disgust"))
        {
            tv1.setText("Ugh! Disgusting!! Here is our analysis of your expressions:\n");
            tv2.setText(emotionAnalysis);
            im.setImageResource(R.drawable.disgust);
            tv3.setText("\n\nLets try to forget that disgusting stuff! Click on the Navigation icon on the right, and choose what can cool you down!\n\nP.S: Your disgust face is a hoot!\n\n");        }

        if (emotionResult.equalsIgnoreCase("Fear"))
        {
            tv1.setText("Saw a terrible horror movie? A ghost? Or was it a nerve racking roller-coaster ride? Here is our analysis of your expressions:\n");
            tv2.setText(emotionAnalysis);
            im.setImageResource(R.drawable.fear);
            tv3.setText("\n\nBeing scared is stupid! Click on the Navigation icon on the right, and choose what can cool you down!\n" +
                    "\nP.S: You didn't seem like a person who could get scared easily!\n\n");
        }

        if (emotionResult.equalsIgnoreCase("Happiness"))
        {
            tv1.setText("The prettiest face ever! Your smile made my day!! Here is our analysis of your expressions:\n");
            tv2.setText(emotionAnalysis);
            im.setImageResource(R.drawable.happy);
            tv3.setText("\n\nWell, you seem happy already! Lets celebrate the joyous occasion! Click on the Navigation icon on the right, and choose something cool. Don't forget to mention your awesome day in your diary!\n\nP.S: I fell in love with that smile!\n\n");
        }

        if (emotionResult.equalsIgnoreCase("Neutral"))
        {
            tv1.setText("Neither happy nor sad.. What might have caused that? Here is our analysis of your expressions:\n");
            tv2.setText(emotionAnalysis);
            im.setImageResource(R.drawable.neutral);
            tv3.setText("\n\nSay cheese! Time to make you smile! Click on the Navigation icon on the right, and choose something cool!");
        }

        if (emotionResult.equalsIgnoreCase("Sadness"))
        {
            tv1.setText("Oh Gosh! You seem pretty upset! Here is our analysis of your expressions:\n");
            tv2.setText(emotionAnalysis);
            im.setImageResource(R.drawable.sad);
            tv3.setText("\n\nOkay then! Lets turn that frown upside down!! Click on the Navigation icon on the right, and choose something to take your mind off the troubles...!\n" +
                    "\n" +
                    "P.S: You don't look nice when sad!Cheer up kiddo!!");
        }

        if (emotionResult.equalsIgnoreCase("Surprise"))
        {
            tv1.setText("You seem surprised? Here is our analysis of your expressions:\n");
            tv2.setText(emotionAnalysis);
            im.setImageResource(R.drawable.surprise);
            tv3.setText("\n\nIt seems like you are not surpised enough. To get your mind blown, check out the Navigation icon to your right!");
        }
        return rootView;
    }
}

