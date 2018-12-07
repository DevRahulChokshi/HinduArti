package com.ebusiness_canvas.hindu_arti.activites;

import android.os.Bundle;
import android.util.Log;

import com.ebusiness_canvas.hindu_arti.R;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class DetailActivity extends YouTubeBaseActivity {

    private static final String TAG=DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Log.i(TAG,"onCreate");
    }



}

