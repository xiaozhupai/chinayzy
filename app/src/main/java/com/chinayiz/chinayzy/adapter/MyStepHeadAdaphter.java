package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

/**  我的足迹标题Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class MyStepHeadAdaphter extends BaseInjectHeadAdaphter {
    private Context context;

    public MyStepHeadAdaphter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.mystep_list_head, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_title;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        }

    }
}
