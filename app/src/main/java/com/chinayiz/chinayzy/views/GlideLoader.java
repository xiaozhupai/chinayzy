package com.chinayiz.chinayzy.views;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageLoader;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/20 16:49
 * Class GlideLoader
 */

public class GlideLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(com.jaiky.imagespickers.R.drawable.global_img_default)
                .centerCrop()
                .into(imageView);
    }
}
