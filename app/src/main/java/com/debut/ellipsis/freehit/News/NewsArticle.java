package com.debut.ellipsis.freehit.News;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.debut.ellipsis.freehit.R;

public class NewsArticle extends AppCompatActivity {

    private Toolbar toolbar;
    private String news_article_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_article);
        news_article_id=getIntent().getStringExtra("news_article_id");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
