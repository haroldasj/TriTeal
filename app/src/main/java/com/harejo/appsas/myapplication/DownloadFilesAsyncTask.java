package com.harejo.appsas.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Haroldas on 2015-04-23.
 */
public class DownloadFilesAsyncTask extends AsyncTask<String, Void, Bitmap> {


    final static int cacheSize = 100000;

    ImageView imageView;

    public DownloadFilesAsyncTask(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        imageView.setImageBitmap(null);
    }

    protected Bitmap doInBackground(String... params) {
        try {
            final String imageKey = params[0];
            final Bitmap bitmap = getBitmapFromMemCache(imageKey);

            if (bitmap != null) {
                return bitmap;
            }else {
                java.net.URL url = new java.net.URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                addBitmapToMemoryCache(String.valueOf(params[0]), myBitmap);
                return myBitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(Bitmap ret) {
        imageView.setImageBitmap(ret);
    }

    public static LruCache<String, Bitmap> mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            // The cache size will be measured in bytes rather than number of items.
            return bitmap.getByteCount();
        }
    };

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}