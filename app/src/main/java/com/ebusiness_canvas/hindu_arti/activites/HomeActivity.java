package com.ebusiness_canvas.hindu_arti.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.ebusiness_canvas.hindu_arti.R;
import com.ebusiness_canvas.hindu_arti.adapter.RecyclerItemAdapter;
import com.ebusiness_canvas.hindu_arti.model.ListDataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private HashMap<String, List<String>> mExpandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mExpandableListView =  findViewById(R.id.expandableListView);

        setToolbar();

        int [] imgGroupImage=ListDataItem.getGroupImage() ;
        int [] imgChildOne=ListDataItem.getChildImageOne() ;
        int [] imgChildTwo=ListDataItem.getChildImageTwo() ;
        int [] imgChildThree=ListDataItem.getChildImageThree() ;
        int [] imgChildFour=ListDataItem.getChildImageFour() ;
        int [] imgChildFive=ListDataItem.getChildImageFive() ;

        mExpandableListDetail = ListDataItem.getData();
        mExpandableListTitle = new ArrayList<String>(mExpandableListDetail.keySet());
        mExpandableListAdapter = new RecyclerItemAdapter(this, mExpandableListTitle, mExpandableListDetail,imgGroupImage,imgChildOne,imgChildTwo,imgChildThree,imgChildFour,imgChildFive);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) { }


        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Intent intent=new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("ListChildTitle",mExpandableListDetail.get(mExpandableListTitle.get(groupPosition)).get(childPosition));
                startActivity(intent);
                return false;
            }
        });
    }

    private void setToolbar() {
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null)
            actionBar.setTitle(R.string.title_home);
    }
}

