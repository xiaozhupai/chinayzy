package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ArticleModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**   博文收藏adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ArticleAdaphter extends BaseInectAdaphter implements EventBusCallback {
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
        ArticleModel.DataBean bean= (ArticleModel.DataBean) lists.get(i);
        Glide.with(context).load(bean.getPicpath()).into(viewHolder.iv_articlekeep_imag);
        viewHolder.tv_articlekeep_title.setText(bean.getTitle());
        viewHolder.tv_articlekeep_content.setText(bean.getSynopsis());
        return view;
    }

    @Override
    public void onItemClick(int position) {
        BaseActivity activity= (BaseActivity) context;
        Logger.i("点击每一个商品");
    }

    @Override
    public void onGetData(int pageindex) {
        UserNet.getNet().getshowBlogCollect(pageindex+"","10");
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getDataType()== Commons.SHOWBLOGCOLLECT){

            ArticleModel model= (ArticleModel) message.getData();

            Logger.i("ArticleAdaphter");
            onResult(model.getData());
        }
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

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
