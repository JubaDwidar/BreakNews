package com.juba.breaknews;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class NewDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    private ImageView imageView;
    private TextView appbarTitle,appbarSubTitle,date,time,title;
    private Boolean isHidetoolbarview=false;
    private  AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String  mUrl,mImge,mTitle,mSource,mtitle,mDate;
    private FrameLayout date_behaviour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        date_behaviour=findViewById(R.id.date_behavior);
        appbarTitle=findViewById(R.id.title_on_appbar);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        title=findViewById(R.id.title);



        appBarLayout=findViewById(R.id.appbar);

        appBarLayout.addOnOffsetChangedListener(this);
    }

    private void initWebView(String url)
    {
        WebView webView=findViewById(R.id.webView);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setDomStorageEnabled(true);
                webView.getSettings().setSupportZoom(true);
                webView.loadUrl(url);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
       int maxScroll= appBarLayout.getTotalScrollRange();
        float per=(Float)Math.abs(i/(float)maxScroll);
        if (per == 1f && isHidetoolbarview) {
            date_behaviour.setVisibility(View.GONE);
            appbarTitle.setVisibility(View.VISIBLE);
            isHidetoolbarview =!isHidetoolbarview;
        }
        if (per < 1f && isHidetoolbarview) {
            date_behaviour.setVisibility(View.VISIBLE);
            appbarTitle.setVisibility(View.GONE);
            isHidetoolbarview =!isHidetoolbarview;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }
}
