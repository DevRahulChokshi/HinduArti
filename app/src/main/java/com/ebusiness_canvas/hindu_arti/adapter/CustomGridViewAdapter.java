package com.ebusiness_canvas.hindu_arti.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;

import java.util.ArrayList;

/**
 * Created by EBC003 on 3/13/2018.
 */

public class CustomGridViewAdapter extends BaseAdapter {

    private Context context;
    private int [] imageItemList;
    private String [] strItemList;
    private LayoutInflater inflter;

    public CustomGridViewAdapter(Context context, int[] imageItemList, String[] strItemList) {
        this.context = context;
        this.imageItemList = imageItemList;
        this.strItemList = strItemList;
        inflter=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return imageItemList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflter.inflate(R.layout.customize_gridview,null);

//        ImageView icon = convertView.findViewById(R.id.imgItem);
        TextView txtTitle = convertView.findViewById(R.id.txtTitleItem);

//        icon.setImageResource(imageItemList[position]);
        txtTitle.setText(strItemList[position]);
        return convertView;
    }
}