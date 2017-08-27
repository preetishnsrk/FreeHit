package com.debut.ellipsis.freehit.News;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class NewsItemAdapter extends ArrayAdapter<NewsItem> {

    //    public View listItemView;
    public ArrayList<Bitmap> Images = new ArrayList<Bitmap>();

    public NewsItemAdapter(Context context, ArrayList<NewsItem> items) {


        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_news, parent, false);
        }


        //Initializing the AVLoadingIndicator
        final AVLoadingIndicatorView loader = (AVLoadingIndicatorView) listItemView.findViewById(R.id.avi);

        NewsItem currentnews = (NewsItem) getItem(position);
        System.out.println(currentnews);
        String imageurl = currentnews.getMurlofimage();
        TextView studentName = (TextView) listItemView.findViewById(R.id.header_text_view);
        studentName.setText(currentnews.getMheadline());
        TextView subjectTextView = (TextView) listItemView.findViewById(R.id.summary_text_view);
        subjectTextView.setText(currentnews.getMdescription());
        final ImageView imageToShow = (ImageView) listItemView.findViewById(R.id.image_view);

        //Getting an instance of the ImageLoader (Initialized with global configs in MainActivity)

        ImageLoader imageloader = ImageLoader.getInstance();
        //Defining options for the display, cache is set to false by default so this is necessary.

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();

        //Straight forward abstract classes, loader is optional
        imageloader.displayImage(imageurl, imageToShow,options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                loader.show();
                imageToShow.setImageResource(R.drawable.matches);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                imageToShow.setImageResource(R.drawable.matches);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                loader.hide();
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
        loader.hide();
        return listItemView;

    }
}





//  CODE TO LOAD IMAGES USING ASYNC LOADER, NOT USED ANYMORE

/*

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        int position;

        AVLoadingIndicatorView loader;
        public DownloadImageFromInternet(ImageView imageView, int position, AVLoadingIndicatorView loader) {
            System.out.println("Downloading image");
            this.imageView = imageView;
            this.position = position;
            this.loader=loader;
        }

        @Override
        protected void onPreExecute() {
            loader.show();
        }

        protected Bitmap doInBackground(String... urls) {

            String imageURL = urls[0];
            Bitmap bimage = null;

            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }


        protected void onPostExecute(Bitmap result) {
//
        }



    }


}*/
