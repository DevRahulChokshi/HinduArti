package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.adapter.CustomArticleAdapter;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ArticleActivity extends AppCompatActivity {

    private static final String TAG=ArticleActivity.class.getSimpleName();

    private RecyclerView mRecyclerArticleList;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private ArrayList<Category> mListData;
    private Toolbar mToolbarArticleList;
    private CustomArticleAdapter containerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mRecyclerArticleList=findViewById(R.id.recyclerArticleList);
        mToolbarArticleList=findViewById(R.id.article_toolbar);
        layoutManager=new LinearLayoutManager(ArticleActivity.this);
        mRecyclerArticleList.setLayoutManager(layoutManager);

        setUpToolbar();
        mListData=new ArrayList<>();


        ArticleAsyncTask articleAsyncTask=new ArticleAsyncTask();
        articleAsyncTask.execute();
    }

    private void setUpToolbar () {
        setSupportActionBar(mToolbarArticleList);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.article_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    class ArticleAsyncTask extends AsyncTask<String,Void,Boolean>{

        private OkHttpClient client=new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(ArticleActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            RequestBody formBody = new FormBody.Builder()
                    .add("CATEGORY_ID","3")
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url(Contract.GET_SUB_CATEGORY);
            builder.post(formBody);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();

                if (response!=null) {

                    JSONArray jsonArray = new JSONArray(response.body().string());

                    for (int i=0;i<jsonArray.length();i++){

                        Category category=new Category();

                        String CategoryID=jsonArray.getJSONObject(i).getString("id");
                        String CategoryName=jsonArray.getJSONObject(i).getString("sc_name");
                        String CategoryAvatar=jsonArray.getJSONObject(i).getString("image");
                        String CategoryDetail=jsonArray.getJSONObject(i).getString("sc_dis");

                        category.setmStrID(CategoryID);
                        category.setmStrTitle(CategoryName);
                        category.setmStrAvatar(CategoryAvatar);
                        category.setmStrDetail(CategoryDetail);

                        mListData.add(category);
                    }
                }else {
                    return false;
                }
                ArticleActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        setRecycler();
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

    private void setRecycler() {
        containerAdapter=new CustomArticleAdapter(ArticleActivity.this,mListData);
        mRecyclerArticleList.setAdapter (containerAdapter);
    }
}
