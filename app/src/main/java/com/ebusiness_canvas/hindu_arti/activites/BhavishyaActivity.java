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
            R.drawable.ic_leo_astrological_sign, R.drawable.ic_libra_scale_balance_symbol,
            R.drawable.ic_leo_astrological_sign, R.drawable.ic_libra_scale_balance_symbol,
            R.drawable.ic_leo_astrological_sign, R.drawable.ic_libra_scale_balance_symbol,
            R.drawable.ic_leo_astrological_sign, R.drawable.ic_libra_scale_balance_symbol,
            R.drawable.ic_leo_astrological_sign, R.drawable.ic_libra_scale_balance_symbol,
            R.drawable.ic_leo_astrological_sign, R.drawable.ic_libra_scale_balance_symbol,
    };

    private String [] strItemList={
            "Maish","Vrish","Mithun","Kark",
            "Singh","Kanya",
            "Vrishchik","Tula",
            "Dhanu", "Makar",
            "Kumbh","Meen"
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
