package com.debut.ellipsis.freehit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class NewsItemAdapter extends ArrayAdapter<NewsItem> {

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
        NewsItem currentnews = (NewsItem) getItem(position);
        System.out.println(this.getPosition(currentnews));
        String imageurl = currentnews.getMurl();

        new DownloadImageFromInternet((ImageView)listItemView.findViewById(R.id.image_view))
                .execute(imageurl);

        TextView studentName = (TextView)listItemView.findViewById(R.id.header_text_view);
        studentName.setText(currentnews.getMheadline());

        TextView subjectTextView = (TextView) listItemView.findViewById(R.id.summary_text_view);
        subjectTextView.setText(currentnews.getMdescription());

        return listItemView;
    }



    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            System.out.println("Downloading image");
            this.imageView = imageView;
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
            imageView.setImageBitmap(result);
        }
    }
}