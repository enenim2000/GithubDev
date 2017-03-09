package com.example.dq_bassey.github_users;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by dq-bassey on 3/7/17.
 */

public class Common {
    public  final static String USER_KEY = "user";
    public final static String USER_API = "https://api.github.com/search/users?q=developers+language:java+location:lagos";
    //public final static String ROOT_URL = "https://github.com";

    public static void loadImageIntoView(User user, ImageView imageView, Context activity, int imageWidth, int imageHeight){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new RoundedBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                activity)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageView.getLayoutParams().height = imageHeight;
        imageView.getLayoutParams().width = imageWidth;

        //download and display image from url
        imageLoader.displayImage(user.getPhotoUrl(), imageView, defaultOptions);

    }
}
