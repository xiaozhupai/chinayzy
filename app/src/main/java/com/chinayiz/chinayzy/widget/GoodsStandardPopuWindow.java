package com.chinayiz.chinayzy.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.GoodStandardModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.net.ContentRequestUtils;
import com.chinayiz.chinayzy.net.NongYe.Net;
import com.orhanobut.logger.Logger;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.商品规格弹出框
 */

public class GoodsStandardPopuWindow extends DialogUtils.XDialog  implements View.OnClickListener {
    private ShopCartModel.DataBean.ShoplistBean bean;
    private Context context;
    public ImageView iv_goodstandard;
    public TextView tv_price;
    private ImageView iv_close;
    public TagListView tlv_list;
    public TextView tv_submit;
    public static final String GoodStands="GoodStands";
    private  List<Tag> tagList=new ArrayList<>();
    private List<GoodStandardModel.DataBean> lists;
      private ContentRequestUtils net=ContentRequestUtils.getRequestUtils();

    public GoodsStandardPopuWindow(Context context, ShopCartModel.DataBean.ShoplistBean bean) {
        super(context, R.style.Dialog);
        this.bean = bean;
        this.context = context;
        initView();
        getData();
    }

    public void setData(List<GoodStandardModel.DataBean> lists){
        this.lists=lists;
        tagList.clear();
        for (GoodStandardModel.DataBean data:lists){
            Tag tag=new Tag();
            tag.setData(data);
            tag.setTitle(data.getStandardname()+data.getStandardvalue());
            if (bean.getGoodsstandardid()==data.getGoodsstandardid()){
                tag.setChecked(true);
            }else {
                tag.setChecked(false);
            }
            tagList.add(tag);
        }
        tlv_list.setTags(tagList);
    }

    //请求数据
    private void getData() {
        Glide.with(context).load(bean.getIcon()).into(iv_goodstandard);
        tv_price.setText(bean.getPrice()+"");
        net.getShopGoodStandard(bean.getGoodsid()+"");
    }

    //布局初始化
    private void initView() {
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.goodstandard_layout);

        iv_goodstandard= (ImageView)findViewById(R.id.iv_goodstandard);
        tv_price= (TextView) findViewById(R.id.tv_price);
        iv_close= (ImageView) findViewById(R.id.iv_close);
        tlv_list= (TagListView) findViewById(R.id.tlv_list);
        tv_submit= (TextView)findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        setCanceledOnTouchOutside(true);

//        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.Animation);

        tlv_list.setOnTagClickListener(new TagListView.OnTagClickListener() {
            @Override
            public void onTagClick(TagView tagView, Tag tag) {
                GoodStandardModel.DataBean data= (GoodStandardModel.DataBean) tag.getData();
                bean.setGoodsstandardid(data.getGoodsstandardid());
                bean.setStandardname(data.getStandardname());
                bean.setStandardvalue(data.getStandardvalue());
                bean.setPrice(data.getPrice());
                bean.setIcon(data.getStanderpic());
                setData(lists);
                tv_price.setText(data.getPrice()+"");
                Glide.with(context).load(bean.getIcon()).into(iv_goodstandard);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                Logger.i("关闭");
                dismiss();
                break;
            case R.id.tv_submit:
                Logger.i("确定");
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,GoodStands,bean));
                dismiss();
                break;
        }
    }
}
