package com.ebusiness_canvas.hindu_arti.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ebusiness_canvas.hindu_arti.R;

public class DetailActivity extends AppCompatActivity {

    private String childTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getBundleData();
    }

    public void getBundleData(){
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            String mChild= (String) bundle.get("ListChildTitle");
            Log.i(DetailActivity.class.getSimpleName(),"Data :-"+mChild);
            // loadDataList(mChild);
            if (mChild!=null){
                if (mChild.equals("नित्य कर्म")){
                    Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

//    private void loadDataList(String mChild) {
//        switch (mChild){
//            case "नित्य कर्म":
//                Toast.makeText(getApplicationContext(),"One",Toast.LENGTH_SHORT).show();
//                break;
//            case "श्लोक/स्तोत्र":
//                Toast.makeText(getApplicationContext(),"Two",Toast.LENGTH_SHORT).show();
//                break;
//            case "आरती संग्रह":
//                Toast.makeText(getApplicationContext(),"Three",Toast.LENGTH_SHORT).show();
//                break;
//            case "सण-उत्सव":
//                Toast.makeText(getApplicationContext(),"Four",Toast.LENGTH_SHORT).show();
//                break;
//            case "श्री स्वामी समर्थ प्रकट दिन विशेष":
//                Toast.makeText(getApplicationContext(),"Five",Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

}
