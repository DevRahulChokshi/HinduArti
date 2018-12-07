package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import org.json.JSONArray;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FestivalDetailActivity extends AppCompatActivity {

    private static final String TAG=FestivalDetailActivity.class.getSimpleName();

    private TextView mFestivalDetailTitle;
    private TextView mFestivalDetailDate;
    private TextView mFestivalDetail;
    private ProgressDialog progressDialog;
    private String   mStrFestivalTitle;
    private Toolbar  mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_detail);

        mFestivalDetailTitle=findViewById(R.id.festival_title);
        mFestivalDetailDate=findViewById(R.id.festival_title_Date_Time);
        mFestivalDetail=findViewById(R.id.txtFestivalDetail);
        mToolbar=findViewById(R.id.festival_detail_toolbar);

        setToolbar();
        getIntentData();

        FestivalDetailAsyncTask asyncTask=new FestivalDetailAsyncTask();
        asyncTask.execute();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.str_festival_detail);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void getIntentData() {
        Intent intent=getIntent ();
        if (intent!=null){
            Bundle bundle=intent.getExtras ();
            if (bundle!=null){
                mStrFestivalTitle=bundle.getString ("CATEGORY_ID","N/A");
                Log.i(TAG,"Position:"+mStrFestivalTitle);
            }else {
                Log.i (TAG,"Bundle is Null");
            }
        }
        else {
            Log.i (TAG,"Intent is Null");
        }
    }


    class FestivalDetailAsyncTask extends AsyncTask<Void,Void,Boolean> {

        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(FestivalDetailActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            RequestBody formBody = new FormBody.Builder()
                    .add("sc_name",mStrFestivalTitle)
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url(Contract.GET_FESTIVAL_DETAIL);
            builder.post(formBody);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                if (response!=null) {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i=0;i<jsonArray.length();i++){

                        final String CategoryID=jsonArray.getJSONObject(i).getString("id");
                        final String CategoryName=jsonArray.getJSONObject(i).getString("sc_name");
                        final String CategoryDescription=jsonArray.getJSONObject(i).getString("sc_dis");

                        Log.i(TAG,CategoryID);
                        Log.i(TAG,CategoryName);
                        Log.i(TAG,CategoryDescription);

                        FestivalDetailActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                setDisplayData(CategoryID,CategoryName,CategoryDescription);
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

    private void setDisplayData(String categoryID, String categoryName, String categoryDescription) {
        mFestivalDetailTitle.setText(categoryName);
        mFestivalDetailDate.setText(categoryID);
        mFestivalDetail.setText(categoryDescription);
    }
}
