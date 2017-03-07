package com.chinayiz.chinayzy.adapter.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.ui.common.PictureActivity;
import com.chinayiz.chinayzy.views.PhotoView.PhotoView;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/6 17:20
 * Class PhotosHolder
 */
public class PhotosHolder implements Holder<String>, View.OnClickListener {
    private PhotoView imageView;
    private Context mContext;
    private String url;
    @Override
    public View createView(Context context) {
        imageView = new PhotoView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setClickable(true);
        imageView.setOnClickListener(this);
        return imageView;
    }
    @Override
    public void UpdateUI(Context context, int position, String data) {
        url=data;
        mContext=context;
        Glide.with(context)
                .load(data)
                .into(imageView);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(mContext, PictureActivity.class);
        intent.putExtra("url",url);
        mContext.startActivity(intent);
    }
}
