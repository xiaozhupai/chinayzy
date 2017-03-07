package com.chinayiz.chinayzy.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.views.PhotoView.Info;
import com.chinayiz.chinayzy.views.PhotoView.PhotoView;
import com.orhanobut.logger.Logger;

public class PictureActivity extends AppCompatActivity implements View.OnClickListener {

    private View progress;
    private PhotoView mPhotoView;
    private String url;
    private Info mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        Intent intent = getIntent();
        url=intent.getStringExtra("url");
        initView();
    }
    private void initView() {
        mPhotoView= (PhotoView) findViewById(R.id.pv_photoView);
        progress=findViewById(R.id.ll_progress);
        mPhotoView.setMaxScale(3);
        mPhotoView.setOnClickListener(this);
        mPhotoView.enable();
        Glide.with(this)
                .load(url)
                .into(new GlideDrawableImageViewTarget(mPhotoView) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        Logger.i("加载完成");
                        progress.setVisibility(View.GONE);
                    }
                    });

    }
    @Override
    public void onClick(View v) {
        finish();
    }
}
