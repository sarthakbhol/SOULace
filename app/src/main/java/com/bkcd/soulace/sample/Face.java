package com.bkcd.soulace.sample;

public class Face {
    private String emotion;
    private double val;

    public Face(String n,double v)
    {
        emotion=n;
        val=v;
    }
    public String getEmotion()
    {
        return emotion;
    }
    public double getVal()
    {
        return val;
    }
    public void setEmotion(String n)
    {
        emotion=n;
    }
    public void setVal(double v)
    {
        val=v;
    }
}