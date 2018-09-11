package com.ebusiness_canvas.hindu_arti.activites;

import android.app.ActionBar;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import com.ebusiness_canvas.hindu_arti.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DetailActivity extends YouTubeBaseActivity {

    private TextView mTextDisplay;
    private Resources resources;
    private YouTubePlayerView playerView ;
    private Toolbar toolbar;
    private String[] mNityaKarma;
    private String[] mShlockShtrot;
    private String[] mArti;
    private String[] mSvan;
    private String[] mSwamiSamarth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        playerView=findViewById(R.id.player);
        mTextDisplay=findViewById(R.id.txtDetail);
        toolbar = findViewById(R.id.toolbar);
        resources=getResources();
        setUpToolbar();
        mNityaKarma =resources.getStringArray(R.array.nityakarma);
        mShlockShtrot =resources.getStringArray(R.array.shlok);
        mArti =resources.getStringArray(R.array.arti);
        mSvan =resources.getStringArray(R.array.san);
        mSwamiSamarth =resources.getStringArray(R.array.swamisamarth);
        getBundleData();


        playerView.initialize("AIzaSyAEJPgLBDaSxk9r-hFQ55-0DKYE_LvziMs",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo("LUx8wlA_dk8");
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
    }

    private void setUpToolbar() {
        String mChild="";
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null) {
           mChild= (String) bundle.get("ListChildTitle");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setActionBar(toolbar);
            ActionBar actionBar=getActionBar();
            if (actionBar!=null){
                actionBar.setTitle(mChild);
            }
        }
    }

    public void getBundleData(){
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            String mChild= (String) bundle.get("ListChildTitle");
            Log.i(DetailActivity.class.getSimpleName(),"Data :-"+mChild);
            if (mChild!=null){
                switch (mChild) {
                    case "कर प्रार्थना":
                        mTextDisplay.setText(mNityaKarma[0]);
                        break;
                    case "भूमी वंदना":
                        mTextDisplay.setText(mNityaKarma[1]);
                        setVideoData("Ns47xq0gXP8");
                        break;
                    case "स्नान प्रार्थना":
                        mTextDisplay.setText(mNityaKarma[2]);
                        break;
                    case "अन्नग्रहण प्रार्थना":
                        mTextDisplay.setText(mNityaKarma[3]);
                        setVideoData("K86NG1fBnHE");
                        break;
                    case "शुभंकरोती":
                        mTextDisplay.setText(mNityaKarma[4]);
                        setVideoData("MaXPyjMXK-M");
                        break;
                    case "झोपताना म्हणावयाचा श्लोक":
                        mTextDisplay.setText(mNityaKarma[5]);
                        setVideoData("f9l0WPjExO0");
                        break;
                    case "गणेश वंदना":
                        mTextDisplay.setText(mShlockShtrot[0]);
                        setVideoData("S4xPAdsvEjA");
                        break;
                    case "गणपती स्तोत्र":
                        mTextDisplay.setText(mShlockShtrot[1]);
                        setVideoData("C7aWF8X6sD0");
                        break;
                    case "भीम रूपीं":
                        mTextDisplay.setText(mShlockShtrot[2]);
                        setVideoData("A1I4zwUD--Y");
                        break;
                    case "राम रक्षा":
                        mTextDisplay.setText(mShlockShtrot[3]);
                        setVideoData("7Pjl1EFIW6E");
                        break;
                    case "सरस्वती वंदना":
                        mTextDisplay.setText(mShlockShtrot[4]);
                        setVideoData("138CwbWms8U");
                        break;
                    case "महालक्ष्मी वंदना":
                        mTextDisplay.setText(mShlockShtrot[5]);
                        setVideoData("fooftZf7PWs");
                        break;
                    case "अथर्व शीर्ष":
                        mTextDisplay.setText(mShlockShtrot[6]);
                        setVideoData("NjwVd6twMwg");
                        break;
                    case "गायत्री मंत्र":
                        mTextDisplay.setText(mShlockShtrot[6]);
                        setVideoData("SarlTxrAbIY");
                        break;
                    case "पसायदान":
                        mTextDisplay.setText(mShlockShtrot[7]);
                        setVideoData("f9l0WPjExO0");
                        break;
                    case "श्री गणपती आरती":
                        mTextDisplay.setText(mArti[0]);
                        setVideoData("w0W8Wh-8UCg");
                        break;
                    case "श्री शंकराची आरती":
                        mTextDisplay.setText(mArti[1]);
                        setVideoData("1XtXAMqnR2M");
                        break;
                    case "श्री दत्त आरती":
                        mTextDisplay.setText(mArti[2]);
                        setVideoData("5Hc3weOOAZA");
                        break;
                    case "देवीची आरती":
                        mTextDisplay.setText(mArti[3]);
                        setVideoData("xElU1vzNqFo");
                        break;
                    case "हनुमानाची आरती":
                        mTextDisplay.setText(mArti[4]);
                        setVideoData("IUPey3fTpDQ");
                        break;
                    case "श्री कृष्णाची आरती":
                        mTextDisplay.setText(mArti[5]);
                        setVideoData("Xu05B14rxPY");
                        break;
                    case "बालाजीची आरती":
                        mTextDisplay.setText(mArti[6]);
                        setVideoData("v24-Y7cDvWE");
                        break;
                    case "सद्गुरूची आरती":
                        mTextDisplay.setText(mArti[7]);
                        setVideoData("MAUSwPMZxbQ");
                        break;
                    case "श्री विट्ठलाची आरती":
                        mTextDisplay.setText(mArti[8]);
                        setVideoData("j0J35iDFnK0");
                        break;
                    case "कर्पुरारती":
                        mTextDisplay.setText(mArti[9]);
                        setVideoData("4IMrtapskCU");
                        break;
                    case "मंत्र पुष्पांजली":
                        mTextDisplay.setText(mArti[10]);
                        setVideoData("OOYJLs8wW7I");
                        break;
                    case "मकरसंक्रांत":
                        mTextDisplay.setText(mSvan[0]);
                        break;
                    case "महाशिवरात्री":
                        mTextDisplay.setText(mSvan[1]);
                        break;
                    case "होळी उस्तव":
                        mTextDisplay.setText(mSvan[2]);
                        break;
                    case "गुढी पाडवा":
                        mTextDisplay.setText(mSvan[3]);
                        break;
                    case "श्रीरामनवमी":
                        mTextDisplay.setText(mSvan[4]);
                        break;
                    case "अक्षय तृतीया":
                        mTextDisplay.setText(mSvan[5]);
                        break;
                    case "वटपौर्णिमा":
                        mTextDisplay.setText(mSvan[6]);
                        break;
                    case "गुरूपौर्णिमा":
                        mTextDisplay.setText(mSvan[7]);
                        break;
                    case "आषाढी एकादशी":
                        mTextDisplay.setText(mSvan[8]);
                        break;
                    case "नागपंचमी":
                        mTextDisplay.setText(mSvan[9]);
                        break;
                    case "नारळी पौर्णिमा":
                        mTextDisplay.setText(mSvan[10]);
                        break;
                    case "श्रीकृष्ण जन्माष्टमी":
                        mTextDisplay.setText(mSvan[11]);
                        break;
                    case "पोळा":
                        mTextDisplay.setText(mSvan[12]);
                        break;
                    case "हरितालिका":
                        mTextDisplay.setText(mSvan[13]);
                        break;
                    case "ऋषि पंचमी":
                        mTextDisplay.setText(mSvan[14]);
                        break;
                    case "श्रीगणेश चतुर्थी":
                        mTextDisplay.setText(mSvan[15]);
                        break;
                    case "अनंत चतुर्दशी":
                        mTextDisplay.setText(mSvan[16]);
                        break;
                    case "नवरात्री":
                        mTextDisplay.setText(mSvan[17]);
                        break;
                    case "दसरा - विजयादशमी":
                        mTextDisplay.setText(mSvan[18]);
                        break;
                    case "दीपावली":
                        mTextDisplay.setText(mSvan[19]);
                        break;
                    case "श्री स्वामी समर्थ प्रकट दिन":
                        mTextDisplay.setText(mSwamiSamarth[0]);
                        setVideoData("oBu7plhqr1E");
                        break;
                    case "श्री स्वामी समर्थ तारक मंत्र":
                        mTextDisplay.setText(mSwamiSamarth[2]);
                        setVideoData("IBjQTFV1yY4");
                        break;
                    case "श्री स्वामी समर्थ मालामंत्र":
                        mTextDisplay.setText(mSwamiSamarth[3]);
                        setVideoData("-c0BiLhkuFw");
                        break;
                    case "श्री स्वामी समर्थ स्तवन":
                        mTextDisplay.setText(mSwamiSamarth[4]);
                        setVideoData("IdpKqrGUGaM");
                        break;
                    case "श्री स्वामी समर्थाष्टक":
                        mTextDisplay.setText(mSwamiSamarth[5]);
                        setVideoData("JKXNgjQWd1w");
                        break;
                    case "श्री स्वामी समर्थ अभिषेक पद":
                        mTextDisplay.setText(mSwamiSamarth[6]);
                        setVideoData("zWRYxIieef0");
                        break;
                    case "श्री स्वामी समर्थ मानस पूजा":
                        mTextDisplay.setText(mSwamiSamarth[7]);
                        setVideoData("N-xP6mc8m3U");
                        break;
                    case "औदुंबर प्रदक्षिणा":
                        mTextDisplay.setText(mSwamiSamarth[8]);
                        break;
                    case "श्री स्वामी समर्थ आरती संग्रह":
                        mTextDisplay.setText(mSwamiSamarth[9]);
                        setVideoData("MrnPzpVllz4");
                        break;
                }
            }
        }
    }

    private void setVideoData(final String strData) {

        playerView.initialize("AIzaSyAEJPgLBDaSxk9r-hFQ55-0DKYE_LvziMs",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(strData);
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
