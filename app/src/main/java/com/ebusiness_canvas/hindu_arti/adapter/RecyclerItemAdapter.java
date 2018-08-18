package com.ebusiness_canvas.hindu_arti.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;

public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.MyViewHolder>{

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //LayoutInflater.from(context).inflate(R.layout.image_corner,parent);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImgItem;
        private TextView mTxtItem;

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
