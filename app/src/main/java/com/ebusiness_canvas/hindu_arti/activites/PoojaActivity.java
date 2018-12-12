package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PoojaActivity extends AppCompatActivity {

    private static final String TAG=PoojaActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private TextInputEditText mEdtUserName,mEdtContactNumber,mEdtEmail,mEdtAddress;
    private TextInputLayout mInputUserName,mInputContactNumber,mInputEmail,mInputAddress;
    private ProgressDialog mProgressDialog;
    private FloatingActionButton mFabButton;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooja);

        mToolbar=findViewById(R.id.pooja_toolbar);

        constraintLayout=findViewById(R.id.constraintPooja);

        mEdtUserName=findViewById(R.id.editUserName);
        mEdtContactNumber=findViewById(R.id.editUserPhoneNumber);
        mEdtEmail=findViewById(R.id.editUserEmail);
        mEdtAddress=findViewById(R.id.editUserAddress);

        mInputUserName=findViewById(R.id.inputUserName);
        mInputContactNumber=findViewById(R.id.inputUserPhoneNumber);
        mInputEmail=findViewById(R.id.inputUserEmail);
        mInputAddress=findViewById(R.id.inputUserAddress);

        mFabButton=findViewById(R.id.fabPooja);

        setToolbar();

        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkState=checkNetworkState();

                if (checkState){
                    checkValidation();
                }else {
                    Snackbar snackbar = Snackbar
                            .make(constraintLayout,"Please check internet connection!", Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                }
            }
        });
    }

    private boolean checkNetworkState() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void checkValidation() {
        String StrUserName;
        String StrUserContactNumber;
        String StrUserEmail;
        String StrUserAddress;

        StrUserName=mEdtUserName.getText().toString();
        StrUserContactNumber=mEdtContactNumber.getText().toString();
        StrUserEmail=mEdtEmail.getText().toString();
        StrUserAddress=mEdtAddress.getText().toString();

        if (StrUserName.equals("")&& StrUserContactNumber.equals("") && StrUserEmail.equals("") && StrUserAddress.equals("")){

            mInputUserName.setErrorEnabled(true);
            mInputUserName.setError("Please enter user name");

            mInputContactNumber.setErrorEnabled(true);
            mInputContactNumber.setError("Please enter contact number");

            mInputEmail.setErrorEnabled(true);
            mInputEmail.setError("Please enter e-mail");

            mInputAddress.setErrorEnabled(true);
            mInputAddress.setError("Please enter address");

        }else if(!StrUserEmail.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){

            mInputEmail.setErrorEnabled(true);
            mInputEmail.setError("Please enter correct e-mail");

        }else if(!StrUserContactNumber.matches("\\d{10}")){

            mInputContactNumber.setErrorEnabled(true);
            mInputContactNumber.setError("Please enter 10 digit");

        }else {
            MyPoojaAsyncTask asyncTask=new MyPoojaAsyncTask();
            asyncTask.execute(StrUserName,StrUserEmail,StrUserContactNumber,StrUserAddress);
        }
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar ();
        if (actionBar!=null){
            actionBar.setTitle (R.string.str_pooja);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    class MyPoojaAsyncTask extends AsyncTask<String,Void,Boolean> {

        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            mProgressDialog=new ProgressDialog(PoojaActivity.this);
            mProgressDialog.setMessage("Loading..");
            mProgressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            String StrUserName=strings[0];
            String StrUserEmail=strings[1];
            String StrUserContactNumber=strings[2];
            String StrUserAddress=strings[3];

            RequestBody formBody = new FormBody.Builder()
                    .add("UserName",StrUserName)
                    .add("UserContactNumber",StrUserContactNumber)
                    .add("UserEmail",StrUserEmail)
                    .add("UserAddress",StrUserAddress)
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url(Contract.POST_POOJA_DATA);
            builder.post(formBody);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();

                if (response!=null) {
                    Log.i(TAG,response.body().string());
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
                mProgressDialog.dismiss();
                Snackbar.make(constraintLayout,"Your request is sent.",Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
