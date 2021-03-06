package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.ArticleModel;
import com.chinayiz.chinayzy.entity.response.FindListModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.ui.fragment.ListFragment;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**   博文收藏adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ArticleAdaphter extends BaseInectAdaphter implements EventBusCallback {
    private int deleteposition;
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
        ArticleModel.DataBean bean= (ArticleModel.DataBean) lists.get(position);
        FindListModel.DataBean dataBean=new FindListModel.DataBean();
         dataBean.setBid(bean.getBlogid());
        Skip.toFindDetail(context,dataBean);
        Logger.i("点击每一个商品");
    }

    @Override
    public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
        deleteposition=i;
       final ArticleModel.DataBean bean= (ArticleModel.DataBean) lists.get(i);
        final MessageDialog dialog=new MessageDialog(context);
        dialog.message.setText("是否取消博文收藏");
        dialog.setButton1("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setButton2("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserNet.getNet().getCollection(bean.getBlogid()+"","0");
                dialog.dismiss();
            }
        });
        dialog.show();
        return true;
    }

    public void delete(){
        lists.remove(deleteposition);
        notifyDataSetChanged();
    }

    @Override
    public void onGetData(int pageindex) {
        UserNet.getNet().getshowBlogCollect(pageindex+"","10");
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }else if (message.getEventType()== EventMessage.ERROR_EVENT){
//            if (pullrefresh!=null){
//                pullResult();
//            }
            if (mSmartRefresh!=null){
                pullResult();
            }
        }

    }
    @Override
    public void onNone(ListFragment fragment) {
        fragment.iv_none.setImageResource(R.mipmap.bg_no_goods);
        fragment.tv_none.setText("您还没有收藏过博文");
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.SHOWBLOGCOLLECT:
                ArticleModel model= (ArticleModel) message.getData();

                Logger.i("ArticleAdaphter");
                onResult(model.getData());
                break;
            case Commons.CANCELCOLLECT:
               BaseResponseModel model1= (BaseResponseModel) message.getData();
                if (model1.getCode().equals("100")){
                    delete();
                }
                break;
        }
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
