package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ArticleDetailActivity extends AppCompatActivity {

    private ImageView mImgAvatar;

    private TextView  mTxtArticleDetailTitle;
    private TextView  mTxtArticleDetailContent;
    private TextView  mTxtArticleDate;

    private String CategoryID;
    private String CategoryName;
    private String CategoryAvatar;
    private String CategoryDetail;
    private String CategoryDate;
    private String strPosition;

    private Toolbar   mToolbar;
    private ArrayList<Category> mListData;
    private ProgressDialog progressDialog;

    private static final String TAG=ArticleDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        mImgAvatar=findViewById(R.id.imgArticleDetail);
        mTxtArticleDetailTitle=findViewById(R.id.txtArticleDetailTitle);
        mTxtArticleDetailContent=findViewById(R.id.txtArticleDetailContent);
//        mTxtArticleDate=findViewById(R.id.txtArticleDate);
        mToolbar=findViewById(R.id.article_detail_toolbar);

        setUpToolbar();
        getIntentData();
        mListData=new ArrayList<>();
        ArticleDetailAsyncTask articleDetailAsyncTask=new ArticleDetailAsyncTask();
        articleDetailAsyncTask.execute(strPosition);

    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.article_detail);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void getIntentData() {
        Intent intent=getIntent ();
        if (intent!=null){
            Bundle bundle=intent.getExtras ();
            if (bundle!=null){
                strPosition=bundle.getString ("CATEGORY_ID","N/A");
                Log.i(TAG,"Position:"+strPosition);
            }else {
                Log.i (TAG,"Bundle is Null");
            }
        }
        else {
            Log.i (TAG,"Intent is Null");
        }
    }

    class ArticleDetailAsyncTask extends AsyncTask<String,Void,Boolean>{

        OkHttpClient client=new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(ArticleDetailActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            String PageContentCategory=strings[0];

            RequestBody formBody = new FormBody.Builder()
                    .add("psc_name",PageContentCategory)
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

                        CategoryID=jsonArray.getJSONObject(i).getString("pc_id");
                        CategoryName=jsonArray.getJSONObject(i).getString("p_name");
                        CategoryAvatar=jsonArray.getJSONObject(i).getString("image");
                        CategoryDetail=jsonArray.getJSONObject(i).getString("p_dis");
                        CategoryDate=jsonArray.getJSONObject(i).getString("record_date");

                        Log.i(TAG,CategoryName);
                        Log.i(TAG,CategoryAvatar);
                        Log.i(TAG,CategoryDetail);
                        Log.i(TAG,CategoryDate);

                    }
                }else {
                    return false;
                }
                ArticleDetailActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        setUpData(CategoryName,CategoryAvatar,CategoryDetail,CategoryDate);
                    }
                });
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

    private void setUpData(String categoryName,String categoryAvatar,String categoryDetail,String categoryDate) {
        mTxtArticleDetailTitle.setText(categoryName);
        mTxtArticleDetailContent.setText(categoryDetail);
//        mTxtArticleDate.setText(categoryDate);
        Picasso.get().load(categoryAvatar).into(mImgAvatar);
    }
}
