package com.ebusiness_canvas.hindu_arti.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.adapter.CustomGridViewAdapter;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG =HomeActivity.class.getSimpleName();

    private GridView mGridItemList;
    private Toolbar mHomeToolbar;

    private int [] imageItemList = {
                R.drawable.ic_icon_nityakarma, R.drawable.ic_icon_bhavishya,
                R.drawable.ic_icon_article, R.drawable.ic_icon_kundali,
                R.drawable.ic_icon_pooja, R.drawable.ic_icon_festival,
            };

    private String [] strItemList={
            "नित्य कर्म","भविष्य",
            "लेख","कुंडली",
            "पूजा","त्यौहार"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mGridItemList=findViewById(R.id.grid_view_image_text);
        mHomeToolbar=findViewById(R.id.home_toolbar);

        setUpToolbar();

        CustomGridViewAdapter customAdapter = new CustomGridViewAdapter(getApplicationContext(),imageItemList,strItemList);
        mGridItemList.setAdapter(customAdapter);

        mGridItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent=new Intent(HomeActivity.this,NityaKarmaActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent=new Intent(HomeActivity.this,BhavishyaActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent=new Intent(HomeActivity.this,ArticleActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3:{
                        Intent intent=new Intent(HomeActivity.this,KundaliActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        Intent intent=new Intent(HomeActivity.this,PoojaActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 5:{
                        Intent intent=new Intent(HomeActivity.this,FestivalActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    private void setUpToolbar () {
        setSupportActionBar(mHomeToolbar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.menu_title);
        }
    }
}

