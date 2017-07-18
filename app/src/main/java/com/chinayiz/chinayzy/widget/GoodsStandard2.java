package com.chinayiz.chinayzy.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.GoodStandardModel;
import com.chinayiz.chinayzy.entity.response.GoodsDetailModel;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2. 商品套餐/规格选择
 */

public class GoodsStandard2 extends DialogUtils.XDialog implements View.OnClickListener,EventBusCallback {
    private GoodsDetailModel.DataBean bean;
    private Context context;
    public ImageView iv_goodstandard;
    public TextView tv_price;
    private ImageView iv_close;
    public TagListView tlv_list;
    public TextView tv_submit;
    public static final String GOODSTANDS="GoodStands";
    /**
     * 选中的套餐信息
     */
    public static final String STANDAR_INFO="GoodStands_INFO";

    private List<Tag> tagList=new ArrayList<>();
    private List<GoodStandardModel.DataBean> lists;
    private CommonRequestUtils net= CommonRequestUtils.getRequestUtils();
    private ImageView iv_add,iv_decrease;
    private TextView tv_num;
    private int num;
    private String defaultPrice;
    private String defaultPid;
    public static final String BACK="GoodsStandard2";

    /**
     *
     * @param context 上下文
     * @param goodstandardid  商品规格ID
     * @param shopid    店铺ID
     * @param goodsid      商品ID
     */
    public GoodsStandard2(Context context,String goodstandardid,String shopid,String goodsid) {
        super(context, R.style.Dialog);
        this.bean=new GoodsDetailModel.DataBean();
        this.bean.setGoodsid(Integer.parseInt(goodsid));
        this.bean.setGoodsstandardid(Integer.parseInt(goodstandardid));
        this.bean.setShopid(Integer.parseInt(shopid));
        this.context = context;
        EventBus.getDefault().register(this);
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
                bean.setRepertorytotal(data.getRepertory());
                bean.setIcon(data.getStanderpic());
                bean.setPrice(data.getPrice()+"");
            }else {
                tag.setChecked(false);
            }
            tagList.add(tag);
        }
        tlv_list.setTags(tagList);
        Glide.with(context).load(bean.getIcon()).into(iv_goodstandard);
        tv_price.setText(bean.getPrice()+"");
        if (bean.getRepertorytotal()<1 ||bean.getRepertorytotal()==1){
            iv_add.setImageResource(R.mipmap.icon_right_add);
          isNone();
        }else {
            iv_add.setImageResource(R.mipmap.icon_right_add_clickable);
        }
    }

    //设置数据
    private void getData() {
        net.getShopGoodStandard(bean.getGoodsid()+"");
        Logger.i("设置数据");
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
        iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_decrease  = (ImageView) findViewById(R.id.iv_decrease);
        tv_num= (TextView) findViewById(R.id.tv_num);
        tv_submit.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_decrease.setOnClickListener(this);
        setCanceledOnTouchOutside(true);
        num=Integer.parseInt(tv_num.getText().toString().trim());
        tv_submit.setText("加入购物车");
        tlv_list.setTagViewTextColorRes(Color.BLACK);
        tlv_list.setTagViewBackgroundRes(R.drawable.tag_normal);
        tlv_list.setmTagViewSelectedBackgroundResid(R.drawable.tag_pressed);
        tlv_list.setmTagViewSelectedTextColorResId(Color.WHITE);
        tlv_list.setOnTagClickListener(new TagListView.OnTagClickListener() {  //标签点击事件
            @Override
            public void onTagClick(TagView tagView, Tag tag) {
                GoodStandardModel.DataBean data= (GoodStandardModel.DataBean) tag.getData();
                bean.setGoodsstandardid(data.getGoodsstandardid());
                bean.setStandardname(data.getStandardname());
                bean.setIcon(data.getStanderpic());
                bean.setRepertorytotal(data.getRepertory());
                setData(lists);
                tv_price.setText(data.getPrice()+"");
                Glide.with(context).load(bean.getIcon()).into(iv_goodstandard);

                if (num>data.getRepertory()){  //超出库存
                    isNone();
                }else {
                    isFull();
                }
            }
        });
    }

    private void isFull(){
        tv_submit.setText("加入购物车");
        tv_submit.setBackgroundColor(Color.parseColor("#ff3951"));
        tv_submit.setEnabled(true);
        iv_add.setImageResource(R.mipmap.icon_right_add_clickable);
        iv_decrease.setImageResource(R.mipmap.icon_left_decrease_clickable);
    }

    private void isNone(){
        tv_submit.setText("暂无库存");
        tv_submit.setBackgroundColor(Color.parseColor("#d9d6d6"));
        tv_submit.setEnabled(false);
        iv_add.setImageResource(R.mipmap.icon_right_add);
        iv_decrease.setImageResource(R.mipmap.icon_left_decrease_clickable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:  //关闭
                Logger.i("关闭");
                todismiss();
                break;
            case R.id.tv_submit:  //加入购物车
                if (!"0".equals(APP.sUserid)){//是否登录
                    CommonRequestUtils.getRequestUtils()
                            .getJoinCart(bean.getShopid()+"",String.valueOf(bean.getGoodsstandardid()),String.valueOf(num));

                }else {
                    Skip.toLogin(context);
                }
                break;
            case R.id.iv_add:   //数量添加
                Logger.i("添加");
                if (num==bean.getRepertorytotal() || num>bean.getRepertorytotal()){
                    return;
                }
                if (num==1){
                    iv_decrease.setImageResource(R.mipmap.icon_left_decrease_clickable);
                }
                if (num==(bean.getRepertorytotal()-1)){
                    iv_add.setImageResource(R.mipmap.icon_right_add);
                }

                num++;
                tv_num.setText(num+"");
                break;
            case R.id.iv_decrease: //数量减少
                Logger.i("减少");
                if (num==1){
                    return;
                }
                if (num==2){
                    iv_decrease.setImageResource(R.mipmap.icon_left_decrease);

                }
                if (num==bean.getRepertorytotal()){
                    iv_add.setImageResource(R.mipmap.icon_right_add_clickable);
                }
                num--;
                tv_num.setText(num+"");
                if (num<bean.getRepertorytotal() || num==bean.getRepertorytotal()){
                    tv_submit.setText("加入购物车");
                    tv_submit.setBackgroundColor(Color.parseColor("#ff3951"));
                    tv_submit.setEnabled(true);
                }

                break;
        }
    }

    private void todismiss(){
//        EventBus.getDefault().unregister(this);
        dismiss();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)

    public void runUiThread(EventMessage message) {
        switch (message.getDataType()){
            case Commons.SHOWGOODSSTANDARD://商品套餐
                GoodStandardModel model4= (GoodStandardModel) message.getData();
                List<GoodStandardModel.DataBean>  lists=model4.getData();
                setData(lists);
                break;
            case  Commons.ADDSHOPPINGCAR://加入购物车
                BaseResponseModel model= (BaseResponseModel) message.getData();
                BaseActivity.showToast(context,model.getMsg());
                EventBus.getDefault().post(new EventMessage(EventMessage.NET_EVENT,STANDAR_INFO,bean));
                todismiss();
                CommonRequestUtils.getRequestUtils().getShoppingCarCount();
                Logger.i("点击获取购物车数量请求");
                break;
        }

    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }
}
