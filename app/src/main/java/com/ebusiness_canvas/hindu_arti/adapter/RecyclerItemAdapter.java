package com.ebusiness_canvas.hindu_arti.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebusiness_canvas.hindu_arti.R;

import java.util.HashMap;
import java.util.List;

public class RecyclerItemAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private int [] mImgGroupImage;
    private int [] mImageChildImage;
    private int [] mImageChildImageTwo;
    private int [] mImageChildImageThree;
    private int [] mImageChildImageFour;
    private int [] mImageChildImageFive;

    public RecyclerItemAdapter(Context context, List<String> expandableListTitle, HashMap<String, List<String>> expandableListDetail, int[] imgResource, int[] imgChildImage, int[] imgChildTwo, int[] imgChildThree, int[] imgChildFour, int[] imgChildFive) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.mImgGroupImage = imgResource;
        this.mImageChildImage = imgChildImage;
        this.mImageChildImageTwo = imgChildTwo;
        this.mImageChildImageThree = imgChildThree;
        this.mImageChildImageFour = imgChildFour;
        this.mImageChildImageFive = imgChildFive;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition,expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.recycler_item_childe_list, null);
        }

        if (listPosition==0){
            if(mImageChildImage !=null){
                for (int i=0;i<expandableListTitle.size();i++){
                    ImageView  imageView=convertView.findViewById(R.id.imgItem);
                    imageView.setImageResource(mImageChildImage[expandedListPosition]);
                }
            }
        }
        else if (listPosition==1) {
            if (mImageChildImageTwo != null) {
                for (int i = 0; i < expandableListTitle.size(); i++) {
                    ImageView imageView = convertView.findViewById(R.id.imgItem);
                    imageView.setImageResource(mImageChildImageTwo[expandedListPosition]);
                }
            }
        }
        else if (listPosition==2) {
            if (mImageChildImageThree != null) {
                for (int i = 0; i < expandableListTitle.size(); i++) {
                    ImageView imageView = convertView.findViewById(R.id.imgItem);
                    imageView.setImageResource(mImageChildImageThree[expandedListPosition]);
                }
            }
        }
        else if (listPosition==3) {
            if (mImageChildImageFour != null) {
                for (int i = 0; i < expandableListTitle.size(); i++) {
                    ImageView imageView = convertView.findViewById(R.id.imgItem);
                    imageView.setImageResource(mImageChildImageFour[expandedListPosition]);
                }
            }
        }
        else if (listPosition==4) {
            if (mImageChildImageFive != null) {
                for (int i = 0; i < expandableListTitle.size(); i++) {
                    ImageView imageView = convertView.findViewById(R.id.imgItem);
                    imageView.setImageResource(mImageChildImageFive[expandedListPosition]);
                }
            }
        }


        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.txtViewTitle);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
            return this.expandableListTitle.get(listPosition);
        }

        @Override
        public int getGroupCount() {
            return this.expandableListTitle.size();
        }

        @Override
        public long getGroupId(int listPosition) {
            return listPosition;
        }

        @Override
        public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String listTitle = (String) getGroup(listPosition);
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.recycler_item_list, null);
            }

            if(mImgGroupImage !=null){
                for (int i=0;i<expandableListTitle.size();i++){
                    ImageView  imageView=convertView.findViewById(R.id.imgItem);
                    imageView.setImageResource(mImgGroupImage[listPosition]);
                }
            }
            TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.txtViewTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}