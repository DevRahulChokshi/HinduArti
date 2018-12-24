package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BhavishyaDetailActivity extends AppCompatActivity {

    private TextView mTxtBhavishyaTitle;
    private TextView mTxtBhavishyaDetail;
    private TextView mTxtDate;

    private Toolbar mToolbarBhavishya;
    private FloatingActionButton actionButton;
    private ArrayList<String> mStrList;
    private ProgressDialog progressDialog;

    private String SubCategoryName;
    private String SubCategoryAvatar;
    private String SubCategoryDiscreption;
    private String SubCategoryDate;
    private String BhavishyaName;
    private ImageView mImageView;

    private static final String TAG=BhavishyaDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle   savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhavishya_detail);

        mTxtBhavishyaTitle=findViewById(R.id.txtTitle);
        mTxtBhavishyaDetail=findViewById(R.id.txtBhavishyaDetail);
        mTxtDate=findViewById(R.id.txtDate);
        mToolbarBhavishya=findViewById(R.id.bhavishyaDetailToolbar);
        mImageView=findViewById(R.id.imgIcons);
        actionButton=findViewById(R.id.fabBhavishyaDetail);

        setUpToolbar();

        mStrList=new ArrayList<>();
        mStrList.add("Maish");
        mStrList.add("Vrish");
        mStrList.add("Mithun");
        mStrList.add("Kark");
        mStrList.add("Singh");
        mStrList.add("Kanya");
        mStrList.add("Vrishchik");
        mStrList.add("Tula");
        mStrList.add("Dhanu");
        mStrList.add("Makar");
        mStrList.add("Kumbh");
        mStrList.add("Meen");

        getIntentData();

        BhavishyaDetailAsyncTask detailAsyncTask=new BhavishyaDetailAsyncTask();
        detailAsyncTask.execute();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, SubCategoryName);
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, SubCategoryDiscreption);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            }
        });
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbarBhavishya);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowTitleEnabled (true);
            actionBar.setTitle (R.string.bhavishaya_detail);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void getIntentData() {
        Intent intent=getIntent ();
        if (intent!=null){
            Bundle bundle=intent.getExtras ();
            if (bundle!=null){
                int strPosition=bundle.getInt ("Position",0);
                Log.i(TAG,"Position:"+strPosition);
                BhavishyaName=mStrList.get(strPosition);
                setImageView(strPosition);
            }else {
                Log.i (TAG,"Bundle is Null");
            }
        }
        else {
            Log.i (TAG,"Intent is Null");

        }
    }

    private void setImageView(int strPosition) {
        switch (strPosition){
            case 0:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_aries));
                break;
            }
            case 1:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_taurus));
                break;
            }
            case 2:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_gemini_zodiac));
                break;
            }
            case 3:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_cancer));
                break;
            }
            case 4:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_leo));
                break;
            }
            case 5:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_virgo));
                break;
            }
            case 6:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_icon_libra));
                break;
            }
            case 7:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_scorpio));
                break;
            }
            case 8:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_sagittarius_sign));
                break;
            }
            case 9:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_capricorn));
                break;
            }
            case 10:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_aquarius));
                break;
            }
            case 11:{
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_pisces));
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    class BhavishyaDetailAsyncTask extends AsyncTask<String,Void,Boolean> {

        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(BhavishyaDetailActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            RequestBody formBody = new FormBody.Builder()
                    .add(Contract.BHAVISHYA_NAME,BhavishyaName)
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url(Contract.GET_CONENT_BHAVISHYA_DETAIL);
            builder.post(formBody);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();

                if (response!=null) {
                    JSONArray jsonArray = new JSONArray(response.body().string());

                    for (int i=0;i<jsonArray.length();i++){

                        SubCategoryName=jsonArray.getJSONObject(i).getString("p_name");
                        SubCategoryAvatar=jsonArray.getJSONObject(i).getString("video");
                        SubCategoryDiscreption=jsonArray.getJSONObject(i).getString("p_dis");
                        SubCategoryDate=jsonArray.getJSONObject(i).getString("record_date");

                        Log.i(TAG,SubCategoryName);
                        Log.i(TAG,SubCategoryAvatar);
                        Log.i(TAG,SubCategoryDiscreption);
                        Log.i(TAG,SubCategoryDate);

                        BhavishyaDetailActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                              setData(SubCategoryName,SubCategoryAvatar,SubCategoryDiscreption,SubCategoryDate);
                            }
                        });
                    }
                }else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean){
                progressDialog.dismiss();
            }
        }
    }

    private void setData(String subCategoryName, String subCategoryAvatar, String subCategoryDescription, String subCategoryDate) {
        mTxtBhavishyaTitle.setText(subCategoryName);
        mTxtDate.setText(subCategoryDate);
        mTxtBhavishyaDetail.setText(subCategoryDescription);
    }
}