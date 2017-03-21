package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.net.NongYe.Net;

import java.util.List;

/**发现Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class FindAdaphter extends BaseInectAdaphter<FindListModel.DataBean> {
    private String type;
    public FindAdaphter(Context context, List<FindListModel.DataBean> lists, String type) {
        this.context=context;
        this.lists=lists;
        this.type=type;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.find_item_layout, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
          viewHolder= (ViewHolder) view.getTag();
        }
       FindListModel.DataBean bean=lists.get(i);
        Glide.with(context).load(bean.getPicpath()).into(viewHolder.iv_find_item_image);
        Glide.with(context).load(bean.getPic()).into(viewHolder.iv_find_item_logo);
        viewHolder.tv_find_item_title.setText(bean.getTitle());
        viewHolder.tv_find_item_content.setText(bean.getSynopsis());
        return view;
    }


    @Override
    public void onGetData(int pageindex) {
        Net.getNet().getFindBlogByType(type,pageindex+"","10");
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_find_item_image;
        public TextView tv_find_item_title;
        public TextView tv_find_item_content;
        public ImageView iv_find_item_logo;
        public TextView tv_find_item_logo_text;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_find_item_image = (ImageView) rootView.findViewById(R.id.iv_find_item_image);
            this.tv_find_item_title = (TextView) rootView.findViewById(R.id.tv_find_item_title);
            this.tv_find_item_content = (TextView) rootView.findViewById(R.id.tv_find_item_content);
            this.iv_find_item_logo = (ImageView) rootView.findViewById(R.id.iv_find_item_logo);
            this.tv_find_item_logo_text = (TextView) rootView.findViewById(R.id.tv_find_item_logo_text);
        }

    }
}
