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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.activites.PoojaActivity;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KundaliMatchingFragment extends Fragment {

    private static final String TAG=KundaliMatchingFragment.class.getSimpleName();
    private TextInputLayout mTextInputName,mTextInputBirthPlace,mTextInputBirthDate,mTextInputBirthTime;
    private TextInputEditText mTextEditName,mTextEditBirthPlace,mTextEditBirthDate,mTextEditBirthTime;
    private FloatingActionButton mFloatButton;
    private ConstraintLayout constraintLayout;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.kundali_matching_fragment,container,false);

        mTextInputName=view.findViewById(R.id.inputKundaliMatchingUserName);
        mTextInputBirthPlace=view.findViewById(R.id.inputKundaliMatchingBirthPlace);
        mTextInputBirthDate=view.findViewById(R.id.inputKundaliMatchingDate);
        mTextInputBirthTime=view.findViewById(R.id.inputKundaliMatchingTime);

        mTextEditName=view.findViewById(R.id.editKundaliMatchingUserName);
        mTextEditBirthPlace=view.findViewById(R.id.editKundaliMatchingBirthPlace);
        mTextEditBirthDate=view.findViewById(R.id.editKundaliMatchingBirthDate);
        mTextEditBirthTime=view.findViewById(R.id.editKundaliMatchingTime);

        constraintLayout=view.findViewById(R.id.constrainKundaliMatching);

        mFloatButton=view.findViewById(R.id.fabKundaliMatching);

        return view;
    }

    @Override
    public void onResume() {
        mFloatButton.setOnClickListener(new View.OnClickListener() {
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
        super.onResume();
    }

    private boolean checkNetworkState() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void checkValidation() {
        String StrUserName;
        String StrBirthPlace;
        String StrBirthDate;
        String StrBirthTime;

        StrUserName=mTextEditName.getText().toString();
        StrBirthPlace=mTextEditBirthPlace.getText().toString();
        StrBirthDate=mTextEditBirthDate.getText().toString();
        StrBirthTime=mTextEditBirthTime.getText().toString();

        if (StrUserName.equals("")&& StrBirthPlace.equals("") && StrBirthDate.equals("") && StrBirthTime.equals("")){

            mTextInputName.setErrorEnabled(true);
            mTextInputName.setError("Please enter user name");

            mTextInputBirthPlace.setErrorEnabled(true);
            mTextInputBirthPlace.setError("Please enter birth place");

            mTextInputBirthDate.setErrorEnabled(true);
            mTextInputBirthDate.setError("Please enter birth date");

            mTextInputBirthTime.setErrorEnabled(true);
            mTextInputBirthTime.setError("Please enter birth time");

        }else {

//            FragmentManager fragmentManager=getFragmentManager();
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            fragmentTransaction.re
//

//            MyKundaliMatchingAsyncTask asyncTask=new MyKundaliMatchingAsyncTask();
//            asyncTask.execute(StrUserName,StrBirthPlace,StrBirthDate,StrBirthTime);

        }
    }

    class MyKundaliMatchingAsyncTask extends AsyncTask<String,Void,Boolean>{

        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            mProgressDialog=new ProgressDialog(getContext());
            mProgressDialog.setMessage("Loading..");
            mProgressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            String StrName=strings[0];
            String StrBirthPlace=strings[1];
            String StrBirthDate=strings[2];
            String StrBirthTime=strings[3];

            RequestBody formBody = new FormBody.Builder()
                    .add("UserContactNumber",StrBirthPlace)
                    .add("UserName",StrName)
                    .add("UserEmail",StrBirthDate)
                    .add("UserAddress",StrBirthTime)
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
            super.onPostExecute(aBoolean);
        }
    }
}
