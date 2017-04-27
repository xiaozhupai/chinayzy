package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.BrandModel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SearchPopuAdaphter extends BaseInectAdaphter {

    public SearchPopuAdaphter(Context context, List<BrandModel.DataBean> list) {
        this.lists = list;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.search_popuwindow_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final BrandModel.DataBean bean = (BrandModel.DataBean) lists.get(i);
        viewHolder.tv_name.setText(bean.getBrand());
        if (bean.isChecked()){   //如果被选中
            viewHolder.v_line.setVisibility(View.VISIBLE);
            viewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.search_selected));
            viewHolder.iv_checked.setVisibility(View.VISIBLE);
        }else {
            viewHolder.v_line.setVisibility(View.INVISIBLE);
            viewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.search_normal));
            viewHolder.iv_checked.setVisibility(View.INVISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.rl_layout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
           setBean(bean,finalViewHolder);
          }
      });

        return view;
    }

    //设置ITEM的点击事件
    private void setBean(BrandModel.DataBean bean,ViewHolder finalViewHolder){
        if (bean.isChecked()){
            bean.setChecked(false);
            finalViewHolder.v_line.setVisibility(View.INVISIBLE);
            finalViewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.search_normal));
            finalViewHolder.iv_checked.setVisibility(View.INVISIBLE);
        }else{
            bean.setChecked(true);
            finalViewHolder.v_line.setVisibility(View.VISIBLE);
            finalViewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.search_selected));
            finalViewHolder.iv_checked.setVisibility(View.VISIBLE);
        }
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public ImageView iv_checked;
        public View v_line;
        public RelativeLayout rl_layout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.iv_checked = (ImageView) rootView.findViewById(R.id.iv_checked);
            this.v_line = (View) rootView.findViewById(R.id.v_line);
            this.rl_layout = (RelativeLayout) rootView.findViewById(R.id.rl_layout);
        }

    }
}
