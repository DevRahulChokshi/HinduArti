package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.adapter.ContainerAdapter;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ContainerActivity extends AppCompatActivity {

    private static final String TAG=ContainerActivity.class.getSimpleName();

    private ArrayList<Category> mListData;
    private ProgressDialog progressDialog;
    private ContainerAdapter containerAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar mContainerToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        ButterKnife.bind(this);

        recyclerView=findViewById(R.id.recyclerItemList);
        mContainerToolBar=findViewById(R.id.container_toolbar);
        layoutManager=new LinearLayoutManager(ContainerActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        setUpToolbar();

        mListData=new ArrayList<>();
        CategoryAsyncTask asyncTask=new CategoryAsyncTask();
        asyncTask.execute();
    }

    class CategoryAsyncTask extends AsyncTask<Void,Void,Boolean>{

        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(ContainerActivity.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            RequestBody formBody = new FormBody.Builder()
                    .add("CATEGORY_ID","1")
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

                        category.setmStrID(CategoryID);
                        category.setmStrTitle(CategoryName);
                        category.setmStrAvatar(CategoryAvatar);

                        mListData.add(category);

                    }

                }else {
                    return false;
                }
                ContainerActivity.this.runOnUiThread(new Runnable() {
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

    private void setRecycler () {
        containerAdapter=new ContainerAdapter(ContainerActivity.this,mListData);
        recyclerView.setAdapter (containerAdapter);
    }

    private void setUpToolbar () {
        setSupportActionBar(mContainerToolBar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.App_name);
        }
    }
}
