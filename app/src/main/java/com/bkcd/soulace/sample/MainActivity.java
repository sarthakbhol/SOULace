package com.bkcd.soulace.sample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.contextmenu.R;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    String emotionResult, emotionAnalysis;
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();


        Intent i= getIntent();
        emotionResult=i.getStringExtra("Emotion Result");
        emotionAnalysis=i.getStringExtra("Emotion Analysis");
      //  Toast.makeText(this, emotionResult, Toast.LENGTH_LONG).show();
       /* tv1=(TextView) findViewById(R.id.emotion_result);
        tv2=(TextView) findViewById(R.id.emotion_analysis);
        tv3=(TextView) findViewById(R.id.suggestions);*/
        addFragment(new MainFragment(), true, R.id.container);
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("Videos");
        send.setResource(R.drawable.v_c_icon);

        MenuObject like = new MenuObject("Images");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Articles");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.a_icon));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("Diary Entry");
        addFav.setResource(R.drawable.d_icon);

        MenuObject dia = new MenuObject("Previous Diary Entries");
        dia.setResource(R.drawable.wallet);

        MenuObject block = new MenuObject("Rate us on Google Play Store!!");
        block.setResource(R.drawable.icn_2);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(dia);
        menuObjects.add(block);
        return menuObjects;
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolBarTextView.setText("Face Analysis Report");
    }

    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
       // Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        if(position==1)
        {
            Intent i1=new Intent(MainActivity.this, VideoSearchActivity.class);
            i1.putExtra("SEARCH",emotionResult);
            startActivity(i1);
        }
        else if (position == 2)
        {
            Intent i1=new Intent(MainActivity.this, ImageSearchActivity.class);
            i1.putExtra("SEARCH",emotionResult);
            startActivity(i1);

        }
        else if (position==3)
        {
            Intent i1=new Intent(MainActivity.this, BingSearchActivity.class);
            i1.putExtra("SEARCH",emotionResult);
            startActivity(i1);

        }
        else if(position == 4)
        {
            Intent i2=new Intent(MainActivity.this, DiaryEntryActivity.class);
            startActivity(i2);
        }
        else if(position == 5)
        {
            Intent i2=new Intent(MainActivity.this, PreviousDiary.class);
            startActivity(i2);
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
       // Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }
}
