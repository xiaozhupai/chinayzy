package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;

import java.util.List;

/**   博文收藏adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ArticleAdaphter extends BaseInectAdaphter {
    public ArticleAdaphter(Context context, List list) {
        this.context = context;
        this.lists = list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.article_keep_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_articlekeep_imag;
        public TextView tv_articlekeep_title;
        public TextView tv_articlekeep_content;
        public LinearLayout lv_center;
        public ImageView iv_arrow_right;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_articlekeep_imag = (ImageView) rootView.findViewById(R.id.iv_articlekeep_imag);
            this.tv_articlekeep_title = (TextView) rootView.findViewById(R.id.tv_articlekeep_title);
            this.tv_articlekeep_content = (TextView) rootView.findViewById(R.id.tv_articlekeep_content);

            this.lv_center = (LinearLayout) rootView.findViewById(R.id.lv_center);
            this.iv_arrow_right = (ImageView) rootView.findViewById(R.id.iv_arrow_right);
        }

    }
}
