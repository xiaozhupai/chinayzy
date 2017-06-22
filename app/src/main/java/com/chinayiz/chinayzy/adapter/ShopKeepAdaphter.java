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
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.model.ResponseModel;
import com.chinayiz.chinayzy.entity.response.ShopCollectModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.ui.fragment.ListFragment;
import com.chinayiz.chinayzy.widget.MessageDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**   店铺收藏Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class ShopKeepAdaphter extends BaseInectAdaphter implements EventBusCallback {
    private int deleteposition;
    public ShopKeepAdaphter(Context context, List list) {
        this.lists = list;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.shop_keep_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        ShopCollectModel.DataBean bean= (ShopCollectModel.DataBean) lists.get(i);
        Glide.with(context).load(bean.getPic()).into(viewHolder.iv_shopkeep_list_image);
        viewHolder.tv_shopkeep_title.setText(bean.getSname());
        viewHolder.tv_shopkeep_num.setText(bean.getCollectnum()+"人收藏");
        viewHolder.tv_shopkeep_evaluate.setText("综合评价"+bean.getSynthesizepoint());
        return view;
    }


    public void delete(){
        lists.remove(deleteposition);
        notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
        deleteposition=i;
        final    ShopCollectModel.DataBean bean= (ShopCollectModel.DataBean) lists.get(i);
        final MessageDialog dialog=new MessageDialog(context);
        dialog.message.setText("是否取消店铺收藏");
        dialog.setButton1("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setButton2("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequestUtils.getRequestUtils().doUnAttentionStore(bean.getShopid()+"");
                dialog.dismiss();
            }
        });
        dialog.show();
        return true;
    }

    @Override
    public void onGetData(int pageindex) {
        UserNet.getNet().getshowShopCollect(pageindex+"","10");
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }else if (message.getEventType()== EventMessage.ERROR_EVENT){
            if (pullrefresh!=null){
                pullResult();
            }
        }

    }

    @Override
    public void onNone(ListFragment fragment) {
        fragment.iv_none.setImageResource(R.mipmap.bg_no_goods);
        fragment.tv_none.setText("您还没有收藏过店铺");
    }

    @Override
    public void onItemClick(int position) {
     BaseActivity activity= (BaseActivity) context;
        Logger.i("点击每一个商品");
        ShopCollectModel.DataBean bean= (ShopCollectModel.DataBean) lists.get(position);
        Skip.toStore(context,bean.getShopid()+"");
    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {


    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.SHOWSHOPCOLLECT:
                ShopCollectModel model= (ShopCollectModel) message.getData();
                Logger.i("ShopKeepAdaphter");
                onResult(model.getData());
                break;
            case Commons.UNATTENTION_STORE:
                ResponseModel model1= (ResponseModel) message.getData();
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
        public ImageView iv_shopkeep_list_image;
        public TextView tv_shopkeep_title;
        public TextView tv_shopkeep_num;
        public TextView tv_shopkeep_evaluate;
        public LinearLayout lv_center;
        public ImageView iv_arrow_right;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_shopkeep_list_image = (ImageView) rootView.findViewById(R.id.iv_shopkeep_list_image);
            this.tv_shopkeep_title = (TextView) rootView.findViewById(R.id.tv_shopkeep_title);
            this.tv_shopkeep_num = (TextView) rootView.findViewById(R.id.tv_shopkeep_num);
            this.tv_shopkeep_evaluate = (TextView) rootView.findViewById(R.id.tv_shopkeep_evaluate);
            this.lv_center = (LinearLayout) rootView.findViewById(R.id.lv_center);
            this.iv_arrow_right = (ImageView) rootView.findViewById(R.id.iv_arrow_right);
        }
    }


}
