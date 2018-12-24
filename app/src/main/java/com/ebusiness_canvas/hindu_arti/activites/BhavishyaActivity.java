package com.ebusiness_canvas.hindu_arti.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.adapter.CustomBhavishyaAdapter;

public class BhavishyaActivity extends AppCompatActivity {

    private static final String TAG=BhavishyaActivity.class.getSimpleName();
    private GridView gridView;
    private Toolbar mToolbarBhavishya;

    private int [] imageItemList = {
            R.drawable.icon_aries, R.drawable.icon_taurus,
            R.drawable.icon_gemini_zodiac, R.drawable.icon_cancer,
            R.drawable.icon_leo,R.drawable.icon_virgo,
            R.drawable.icon_library_demo,R.drawable.icon_scorpio,
            R.drawable.icon_sagittarius_sign,R.drawable.icon_capricorn,
            R.drawable.icon_aquarius,R.drawable.icon_pisces,
    };

    private String [] strItemList={
            "मेष","वृषभ",
            "मिथुन","कर्क",
            "सिंह","कन्या",
            "तुला","वृश्चिक",
            "धनु", "मकर",
            "कुंभ","मीन"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhavishya);

        gridView=findViewById(R.id.grid_view_bhavishya);
        mToolbarBhavishya=findViewById(R.id.bhavishya_toolbar);

        setToolbar();

        CustomBhavishyaAdapter customAdapter = new CustomBhavishyaAdapter(getApplicationContext(),imageItemList,strItemList);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        Log.i(TAG,"Position:"+position);
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }

                    case 3:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 5:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 6:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 7:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 8:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 9:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 10:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                    case 11:{
                        Intent intent=new Intent(BhavishyaActivity.this,BhavishyaDetailActivity.class);
                        Log.i(TAG,"Position:"+position);
                        Log.i(TAG,"View Name:"+parent.getItemAtPosition(position));
                        intent.putExtra("Position",position);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(mToolbarBhavishya);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowTitleEnabled (true);
            actionBar.setTitle (R.string.bhavishaya);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
