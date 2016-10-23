package com.bkcd.soulace.sample;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.contextmenu.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DiaryEntryActivity extends AppCompatActivity {

    TextView tv;
    EditText ed;
    String dateStr="10/05/1997";
    Button save;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);

        db=openOrCreateDatabase("system",MODE_PRIVATE,null);
        db.execSQL("create table if not exists diary (date varchar(10),content varchar(300))");

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        tv=(TextView)findViewById(R.id.date);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c.getTime());
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Angelic Peace.ttf");
        tv.setTypeface(custom_font);
        tv.setText(formattedDate);
        save=(Button)findViewById(R.id.save);

        ed=(EditText) findViewById(R.id.text_diary);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/MotionPicture_PersonalUseOnly.ttf");
        ed.setTypeface(custom_font2);
        ed.setText("\n");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("date",formattedDate.toString());
                cv.put("content",ed.getText().toString());
                db.insert("diary",null,cv);
                Toast.makeText(DiaryEntryActivity.this, "Saved !!", Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });
    }
}
