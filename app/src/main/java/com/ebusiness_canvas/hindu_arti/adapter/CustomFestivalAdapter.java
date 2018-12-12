package com.ebusiness_canvas.hindu_arti.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.activites.FestivalDetailActivity;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;

import java.util.ArrayList;

public class CustomFestivalAdapter extends RecyclerView.Adapter<CustomFestivalAdapter.MyFestivalHolder> {

    private Context context;
    private ArrayList<Category> arrayList;
    private LayoutInflater layoutInflater;

    public CustomFestivalAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyFestivalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.custom_recycler_festival,parent,false);
        MyFestivalHolder myViewHolder=new MyFestivalHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyFestivalHolder holder, int position) {
        if(arrayList!=null){
            Category category=arrayList.get(position);
            if (category!=null){
                holder.mTxtFestivalTitle.setText(category.getmStrTitle());
                holder.mTxtFestivalDescription.setText(category.getmStrDetail());
            }
        }else {
            Log.i(ContainerAdapter.class.getSimpleName(),"Data is null");
        }
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                String strCategoryID=(String)holder.mTxtFestivalTitle.getText ();
                Intent intent=new Intent (context.getApplicationContext (),FestivalDetailActivity.class);
                intent.putExtra (Contract.CATEGORY_ID,strCategoryID);
                context.startActivity (intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyFestivalHolder extends RecyclerView.ViewHolder {

//        private TextView mTxtFestivalID;
        private TextView mTxtFestivalTitle;
        private TextView mTxtFestivalDescription;


        public MyFestivalHolder(View itemView) {
            super(itemView);
            mTxtFestivalTitle=itemView.findViewById(R.id.txtFestivalTitle);
            mTxtFestivalDescription=itemView.findViewById(R.id.txtFestivalDate);
        }
    }
}
