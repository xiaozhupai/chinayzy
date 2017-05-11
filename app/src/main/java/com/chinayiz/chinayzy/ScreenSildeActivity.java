package com.chinayiz.chinayzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScreenSildeActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ViewPager guide_vp;
    private TextView guide_ib_start;
    private int []imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screenslide);
        initView();

        initViewPager();
    }
    //加载ViewPager
    private void initViewPager() {
        //实例化图片资源
        imageIdArray = new int[]{R.mipmap.guide1,R.mipmap.guide2,R.mipmap.guide3};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }
        //View集合初始化好后，设置Adapter
        guide_vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        guide_vp.setOnPageChangeListener(this);
    }

    private void initView() {
        guide_vp = (ViewPager) findViewById(R.id.guide_vp);
        guide_ib_start = (TextView) findViewById(R.id.guide_ib_start);
        guide_ib_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide_ib_start:
                SharedPreferences sp=getSharedPreferences("slide", Context.MODE_PRIVATE);
                SharedPreferences.Editor editer=sp.edit();
                editer.putString("isfirst","1");
                editer.commit();
                finish();
                Intent intent=new Intent(this,NewMainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            guide_ib_start.setVisibility(View.VISIBLE);
        }else {
            guide_ib_start.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class GuidePageAdapter extends PagerAdapter {

        private List<View> viewList;

        public GuidePageAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        /**
         * @return 返回页面的个数
         */
        @Override
        public int getCount() {
            if (viewList != null){
                return viewList.size();
            }
            return 0;
        }

        /**
         * 判断对象是否生成界面
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化position位置的界面
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
