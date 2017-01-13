package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12.
 */

public class HeadAdaphter extends BaseInjectHeadAdaphter {
    private Context context;
    public HeadAdaphter(Context context){
        this.context=context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.result_head_layout, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
            Log.i("HeadAdaphter","VIEW");
        } else {
            viewHolder= (ViewHolder) view.getTag();
            Log.i("HeadAdaphter","ISVIEW");
        }
        viewHolder.iv_head_title.setText(section);
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView iv_head_title;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_head_title = (TextView) rootView.findViewById(R.id.iv_head_title);
        }

    }
}
