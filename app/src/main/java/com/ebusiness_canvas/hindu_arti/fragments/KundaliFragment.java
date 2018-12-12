package com.ebusiness_canvas.hindu_arti.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class KundaliFragment extends Fragment {

    private FloatingActionButton mFabButton;
    private ProgressDialog progressDialog;
    private TextInputEditText mInEditUserName,mInEditBirthPlace,mInEditBirthDate,mInEditBirthTime;
    private TextInputLayout mInLayUserName,mInLayBirthPlace,mInLayBirthDate,mInLayBirthTime;
    private ConstraintLayout constraintLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.kundali_fragment,container,false);

            mFabButton =view.findViewById(R.id.fabKundali);

            constraintLayout=view.findViewById(R.id.kundali_frag);

            mInEditUserName=view.findViewById(R.id.editKundaliUserName);
            mInEditBirthPlace=view.findViewById(R.id.editKundaliBirthPlace);
            mInEditBirthDate=view.findViewById(R.id.editKundaliBirthDate);
            mInEditBirthTime=view.findViewById(R.id.editKundaliTime);

            mInLayUserName=view.findViewById(R.id.inputKundaliUserName);
            mInLayBirthPlace=view.findViewById(R.id.inputKundaliBirthPlace);
            mInLayBirthDate=view.findViewById(R.id.inputKundaliDate);
            mInLayBirthTime=view.findViewById(R.id.inputKundaliTime);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean netWorkState=checkNetworkState();

                if (netWorkState){
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
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }


    private void checkValidation() {
        String StrUserName=mInEditUserName.getText().toString();
        String StrBirthPlace=mInEditBirthPlace.getText().toString();
        String StrBirthDate=mInEditBirthDate.getText().toString();
        String StrBirthTime=mInEditBirthTime.getText().toString();

        if (StrUserName.equals("")&& StrBirthPlace.equals("")&& StrBirthDate.equals("") && StrBirthTime.equals("")){

            mInLayUserName.setError("Please enter user name!");
            mInLayUserName.setErrorEnabled(true);

            mInLayBirthPlace.setError("Please enter birth place!");
            mInLayBirthPlace.setErrorEnabled(true);

            mInLayBirthDate.setError("Please enter birth date!");
            mInLayBirthDate.setErrorEnabled(true);

            mInLayBirthTime.setError("Please enter birth time!");
            mInLayBirthTime.setErrorEnabled(true);

        }else {
            KundaliMatchingAsyncTask asyncTask=new KundaliMatchingAsyncTask();
            asyncTask.execute(StrUserName,StrBirthPlace,StrBirthDate,StrBirthTime);
        }
    }

    class KundaliMatchingAsyncTask extends AsyncTask<String,Void,Boolean>{

        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            String StrUserName=strings[0];
            String StrBirthPlace=strings[1];
            String StrBirthDate=strings[2];
            String StrBirthTime=strings[3];

            RequestBody formBody = new FormBody.Builder()
                    .add("UserName",StrUserName)
                    .add("BirthPlace",StrBirthPlace)
                    .add("BirthDate",StrBirthDate)
                    .add("BirthTime",StrBirthTime)
                    .build();

            Request.Builder builder = new Request.Builder();
            builder.url(Contract.POST_KUNDALI_DATA);
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
                progressDialog.dismiss();
                Snackbar.make(constraintLayout,"Your request is sent.",Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
