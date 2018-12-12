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
import com.ebusiness_canvas.hindu_arti.adapter.CustomFestivalAdapter;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FestivalActivity extends AppCompatActivity {

    private static final String TAG=FestivalActivity.class.getSimpleName();

    private RecyclerView mRecyclerFestival;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Category> mListData;
    private ProgressDialog progressDialog;
    private CustomFestivalAdapter customFestivalAdapter;
    private Toolbar mContainerToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival);

        mContainerToolBar=findViewById(R.id.festival_toolbar);
        mRecyclerFestival=findViewById(R.id.RecyclerFestival);
        layoutManager=new LinearLayoutManager(FestivalActivity.this);
        mRecyclerFestival.setLayoutManager(layoutManager);
        setUpToolbar();
        mListData=new ArrayList<>();
        FestivalAsyncTask  asyncTask=new FestivalAsyncTask();
        asyncTask.execute();
    }

    private void setUpToolbar() {
        setSupportActionBar(mContainerToolBar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.str_festival);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    class FestivalAsyncTask extends AsyncTask<Void,Void,Boolean>{


        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(FestivalActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            RequestBody formBody = new FormBody.Builder()
                    .add("CATEGORY_ID","6")
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
                        String CategoryDescription=jsonArray.getJSONObject(i).getString("sc_dis");

                        category.setmStrID(CategoryID);
                        category.setmStrTitle(CategoryName);
                        category.setmStrDetail(CategoryDescription);

                        mListData.add(category);
                    }

                }else {
                    return false;
                }
                FestivalActivity.this.runOnUiThread(new Runnable() {
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
        customFestivalAdapter=new CustomFestivalAdapter(FestivalActivity.this,mListData);
        mRecyclerFestival.setAdapter(customFestivalAdapter);
    }
}
