package com.chinayiz.chinayzy.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.utils.ShareUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Created by Administrator on 2017/6/22.
 */

public class ShareDialog2 extends DialogUtils.XDialog implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String DEFAULT_URL = "";
    private GridView vShare;
    private TextView vCancel;
    private Adapter2 mAdapter;
    private Context context;
    private int type;
    private String mId;
    private String dis;
    private String url = "";
    private String title;
    private String content;
    private static String shareImageUrl;


    /**
     * 构造分享内容
     *
     * @param content 文字内容
     * @param title   标题
     * @param url     链接
     */
    public ShareDialog2(Context context, String logo, String url, String title, String content) {
        super(context, R.style.Dialog);
        this.context = context;
        this.url = url;
        this.shareImageUrl = logo;
        this.title = title;
        this.content = content;
    }

    public ShareDialog2(Context context, String url, String title, String content) {
        super(context, R.style.Dialog);
        this.context = context;
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public ShareDialog2(Context context) {
        super(context, R.style.Dialog);
        this.context = context;
    }

    /* (non-Javadoc)
     * @see cn.stlc.app.net.task.DialogUtils.XDialog#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.dialog_share2);

        vShare = (GridView) findViewById(R.id.dialog_share_grid);
        vCancel = (TextView) findViewById(R.id.dialog_share_cancel);

        vCancel.setOnClickListener(this);
        vShare.setSelector(new ColorDrawable(0));
        mAdapter = new Adapter2();
        vShare.setAdapter(mAdapter);
        vShare.setOnItemClickListener(this);
        setCanceledOnTouchOutside(true);
    }

    /* (non-Javadoc)
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int shareImg = R.mipmap.ic_launcher;

        dis = content;  //分享的内容
        PlatformActionListener pl;
        pl = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(context, "分享错误", Toast.LENGTH_LONG).show();
              BaseActivity activity= (BaseActivity) context;
                activity.finish();
            }

            @Override
            public void onCancel(Platform platform, int i) {

                Toast.makeText(context, "分享失败", Toast.LENGTH_LONG).show();
                BaseActivity activity= (BaseActivity) context;
                activity.finish();
            }
        };
        ShareUtils.turnToShare(position, dis, title, shareImageUrl, url, pl, context);
        dismiss();
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        dismiss();
    }

    /**
     * @param dis the dis to set
     */
    public void setDis(String dis) {
        this.dis = dis;
    }

    private class Adapter2 extends BaseAdapter {

        private int[] resId = new int[]{R.mipmap.icon_moments, R.mipmap.icon_wechat};
        private String[] share_name = new String[]{"朋友圈", "微信"};

        /* (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount() {
            return 2;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Object getItem(int position) {
            return position;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItemId(int)
         */

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout ll = new LinearLayout(getContext());
            ll.setOrientation(LinearLayout.VERTICAL);
            ImageView image = new ImageView(getContext());
            image.setImageResource(resId[position]);
            TextView tv = new TextView(getContext());
            tv.setText(share_name[position]);
            tv.setTextColor(Color.parseColor("#a9a9a9"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            tv.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 37, 0, 0);

            tv.setLayoutParams(lp);
            ll.addView(image);
            ll.addView(tv);
            return ll;
        }

    }
}