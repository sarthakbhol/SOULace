package com.bkcd.soulace.sample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yalantis.contextmenu.R;

import java.util.ArrayList;

public class PreviousDiary extends AppCompatActivity {

    SQLiteDatabase db;
    ArrayList<Info> info;
    MyAdapter adapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_diary);

        db = openOrCreateDatabase("system", MODE_PRIVATE, null);
        db.execSQL("create table if not exists diary (date varchar(10),content varchar(300))");

        listview=(ListView)findViewById(R.id.list);

        info=new ArrayList<Info>();
        Cursor cursor=db.rawQuery("select * from diary",null);



        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String desc = cursor.getString(cursor.getColumnIndex("date"));
                String status = cursor.getString(cursor.getColumnIndex("content"));

                Info infodata = new Info();
                infodata.setDate(desc);
                infodata.setContent(status);

                info.add(infodata);
            }
        }

        adapter=new MyAdapter(PreviousDiary.this,info);
        listview.setAdapter(adapter);


    }
}

    class MyAdapter extends BaseAdapter {

        Context context;
        ArrayList<Info> info;
        private static LayoutInflater inflater = null;

        MyAdapter(Context context, ArrayList<Info> info) {
            this.context = context;
            this.info = info;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return info.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                 convertView=inflater.inflate(R.layout.diary_prev,null);
            }
           TextView desc = (TextView) convertView.findViewById(R.id.news_label);
            TextView status = (TextView) convertView.findViewById(R.id.news_con);
            Info infodata;
            infodata = info.get(position);
            desc.setText(infodata.getDate());
            status.setText(infodata.getContent());

            return convertView;
        }
    }

    class Info {
        private String date, content;

        public String getDate() {
            return date;
        }

        public void setDate(String desc) {
            this.date = desc;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String status) {
            this.content = status;
        }

    }

