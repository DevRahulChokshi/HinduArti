package com.ebusiness_canvas.hindu_arti.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ebusiness_canvas.hindu_arti.R;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerItem;


    // drawable id for Binding view
    private int [] ingeResource={
            R.drawable.btn_google_dark_focus,
            R.drawable.btn_google_dark_focus,
            R.drawable.btn_google_dark_focus,
            R.drawable.btn_google_dark_focus,
            R.drawable.btn_google_dark_focus
    };

    // string for title for Binding view
    private String [] strItems={
           "साधने ऑनलाइन वापरून पहा",
           "साधने ऑनलाइन वापरून पहा",
           "साधने ऑनलाइन वापरून पहा",
           "साधने ऑनलाइन वापरून पहा",
           "साधने ऑनलाइन वापरून पहा"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initiate View
        onInitiateView();

        //


    }

    private void onInitiateView() {
        mRecyclerItem=findViewById(R.id.recyclerItemList);
    }
}
