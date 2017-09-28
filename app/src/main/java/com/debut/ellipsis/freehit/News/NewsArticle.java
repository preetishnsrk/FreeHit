package com.debut.ellipsis.freehit.News;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import static com.debut.ellipsis.freehit.News.NewsArticleLoader.news;

public class NewsArticle extends AppCompatActivity implements LoaderManager.LoaderCallbacks<NewsArticleItem> {

    private Toolbar toolbar;
    private int news_article_id;
    public static final String LOG_TAG = NewsArticle.class.getSimpleName();

    public View loadingIndicator;
    //the website url of the api
    private static String URL =
            "http://freehit-api.herokuapp.com/news?id=";

    private static final int NEWS_ARTICLE_LOADER_ID = 1;

    private NewsArticleItem newsItem;
    public TextView mEmptyStateTextView;
    private ProgressBar mProgressBar;

    public NewsArticle() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_article);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        news_article_id = getIntent().getIntExtra("news_article_id", 0);
        URL =
                "http://freehit-api.herokuapp.com/news?id=";
        URL += news_article_id;

        Log.e(LOG_TAG, URL);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //removing text from collapsing toolbar
        setTitle(" ");

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).

            loaderManager.initLoader(NEWS_ARTICLE_LOADER_ID, null, this).forceLoad();
            Log.i(LOG_TAG, "TEST:Calling initLoader() ....");
        }

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        int colorCodeDark = Color.parseColor("#F44336");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProgressBar.setIndeterminateTintList(ColorStateList.valueOf(colorCodeDark));
        }


    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        NewsArticle.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

    @Override
    public Loader<NewsArticleItem> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsArticleLoader(getApplicationContext(), URL);

    }

    @Override
    public void onLoadFinished(final Loader<NewsArticleItem> loader, NewsArticleItem News) {

        mProgressBar.setVisibility(View.GONE);

        TextView headline = (TextView) findViewById(R.id.news_article_heading);
        headline.setText(news.getMheadline());

        TextView article_description = (TextView) findViewById(R.id.news_article_description);
        article_description.setText(news.getMnewsArticle());

        TextView date = (TextView) findViewById(R.id.news_date);
        date.setText(news.getMdate());

        TextView news_tag_1 = (TextView) findViewById(R.id.news_tag);
        news_tag_1.setText(news.getmTag1());

        TextView news_tag_2 = (TextView) findViewById(R.id.news_tag_1);
        news_tag_2.setText(news.getmTag2());

        TextView news_tag_3 = (TextView) findViewById(R.id.news_tag_2);
        news_tag_3.setText(news.getmTag3());

        ImageView tag_1 = (ImageView) findViewById(R.id.news_tag_image);
        tag_1.setVisibility(View.VISIBLE);

        ImageView tag_2 = (ImageView) findViewById(R.id.news_tag_image_1);
        tag_2.setVisibility(View.VISIBLE);

        ImageView tag_3 = (ImageView) findViewById(R.id.news_tag_image_2);
        tag_3.setVisibility(View.VISIBLE);

        final ImageView articleImage = (ImageView) findViewById(R.id.news_article_image);

        final String ImageURL = news.getMurlofimage();

        ImageLoader imageloader = ImageLoader.getInstance();
        //Defining options for the display, cache is set to false by default so this is necessary.

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();

        //Straight forward abstract classes, loader is optional
        imageloader.displayImage(ImageURL, articleImage, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                articleImage.setImageResource(R.drawable.matches);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                articleImage.setImageResource(R.drawable.matches);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    @Override
    public void onLoaderReset(Loader<NewsArticleItem> loader) {
        loader = null;

    }
}
