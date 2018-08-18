package com.ebusiness_canvas.hindu_arti.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ebusiness_canvas.hindu_arti.R;


public class SplashActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mImageView=findViewById(R.id.deep_bg);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkSharedPreference();
            }
        }, 2000);
    }

    private void checkSharedPreference() {
//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCE_KEY, MODE_PRIVATE);
//        Boolean aBoolean = sharedPreferences.contains(Constants.EMP_ID);

//        if (!aBoolean) {
//            Intent intent = new Intent(this, ActivityLogin.class);
//            startActivity(intent);
//            finish();
//        } else {
//            Intent intent = new Intent(this, ActivityContainer.class);
//            startActivity(intent);
//            finish();
//        }

        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
