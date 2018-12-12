package com.ebusiness_canvas.hindu_arti.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.activites.ArticleDetailActivity;
import com.ebusiness_canvas.hindu_arti.model.Category;
import com.ebusiness_canvas.hindu_arti.model.Contract;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomArticleAdapter extends RecyclerView.Adapter<CustomArticleAdapter.MyViewHolder> {

    private ArrayList<Category>categories;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomArticleAdapter(Context context, ArrayList<Category> mListData) {
        this.context=context;
        this.categories=mListData;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.custom_article_list,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        if(categories!=null){
            Category category=categories.get(position);
            if (category!=null){
                holder.mTextTitle.setText(category.getmStrTitle());
                holder.mTextID.setText(category.getmStrID());
                holder.mTextDetail.setText(category.getmStrDetail());
                Picasso.get().load(category.getmStrAvatar()).into(holder.mImageAvatar);
            }
        }else {
            Log.i(ContainerAdapter.class.getSimpleName(),"Data is null");
        }
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                String strCategoryID=(String)holder.mTextID.getText ();
                    Intent intent=new Intent (context.getApplicationContext (),ArticleDetailActivity.class);
                    intent.putExtra (Contract.CATEGORY_ID,strCategoryID);
                context.startActivity (intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageAvatar;
        TextView mTextTitle;
        TextView mTextDetail;
        TextView mTextID;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageAvatar=itemView.findViewById(R.id.article_image);
            mTextTitle=itemView.findViewById(R.id.article_title);
            mTextDetail=itemView.findViewById(R.id.article_content);
            mTextID=itemView.findViewById(R.id.txtArticleID);
        }
    }
}
