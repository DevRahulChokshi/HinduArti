package com.ebusiness_canvas.hindu_arti.activites;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.adapter.PagerEditTradingDetails;

public class KundaliActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private static final String TAG=KundaliActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali);

        mToolbar=findViewById(R.id.kundali_toolbar);
        tabLayout=findViewById(R.id.tabLayout_trading_tab_view);
        viewPager=findViewById(R.id.pager_trading_tab_view);

        tabLayout.addTab(tabLayout.newTab().setText("Kundali"));
        tabLayout.addTab(tabLayout.newTab().setText("Kundali-Matching"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerEditTradingDetails adapter = new PagerEditTradingDetails(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);

        setToolBar();
    }

    private void setToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.kundali_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        String tabStr= (String) tab.getText ();
        Log.i (TAG,tabStr);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        String tabStr= (String) tab.getText ();
        Log.i (TAG,tabStr);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
