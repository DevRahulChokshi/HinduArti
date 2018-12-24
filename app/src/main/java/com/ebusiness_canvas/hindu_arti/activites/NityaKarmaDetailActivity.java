package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NityaKarmaDetailActivity extends AppCompatActivity {

    private static final String TAG=NityaKarmaDetailActivity.class.getSimpleName();

    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private YouTubePlayer youTubePlayer;
    private ProgressDialog progressDialog;
    private ArrayList<Category> categories;
    private TextView mTextDisplay;
    private String mCategoryName;
    private String SubCategoryName;
    private String SubCategoryAvatar;
    private String SubCategoryDiscreption;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_detail_acticity);

        mTextDisplay = findViewById(R.id.txtDetail);
        mToolbar=findViewById(R.id.toolbar_nityakarma_detail);

        checkIntentData();
        setToolBar();
        categories=new ArrayList<>();

        CategoryDetailAsyncTask detailAsyncTask=new CategoryDetailAsyncTask();
        detailAsyncTask.execute(mCategoryName);

        initializeYoutubePlayer();
    }

    private void setToolBar() {
        setSupportActionBar(mToolbar);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.app_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }


    private void checkIntentData () {
        Intent intent=getIntent ();
        if (intent!=null){
            Bundle bundle=intent.getExtras ();
            if (bundle!=null){
                mCategoryName=bundle.getString (Contract.CATEGORY_ID,"N/A");
            }else {
                Log.i (TAG,"Bundle is null");
            }
        }else{
            Log.i (TAG,"Intent is null");
        }
    }

    private void initializeYoutubePlayer() {

        youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.youtube_player_fragment);

        if (youTubePlayerFragment == null)
            return;

        youTubePlayerFragment.initialize("AIzaSyAf8oOA_RPoQXyz5V9PbhJwt0bfwFGT-pA", new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer = player;

                    //set the player style default
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.cueVideo(SubCategoryAvatar);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {

                //print or show error if initialization failed
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }

    class CategoryDetailAsyncTask extends AsyncTask<String,Void,Boolean>{

        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(NityaKarmaDetailActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            String mCategoryName=strings[0];
            Log.i(TAG,mCategoryName);

            RequestBody formBody = new FormBody.Builder()
                    .add(Contract.PAGE_SUB_CONTENT_ID,mCategoryName)
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url(Contract.GET_CONENT_DETAIL);
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

                        Log.i(TAG,SubCategoryName);
                        Log.i(TAG,SubCategoryAvatar);
                        Log.i(TAG,SubCategoryDiscreption);

                        setData(SubCategoryDiscreption);
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

    private void setData(String subCategoryDiscreption) {
        mTextDisplay.setText(subCategoryDiscreption);
    }
}
