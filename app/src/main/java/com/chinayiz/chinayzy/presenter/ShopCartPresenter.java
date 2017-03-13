package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chinayiz.chinayzy.Skip;
import com.chinayiz.chinayzy.adapter.ShopCartAdaphter;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.GoodStandardModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.CommonRequestUtils;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.widget.GoodsStandardPopuWindow;
import com.orhanobut.logger.Logger;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;


/**购物车
 * Created by Administrator on 2017/1/3.
 */

public class ShopCartPresenter extends BasePresenter<ShopCartFragment> {
    private CommonRequestUtils net= CommonRequestUtils.getRequestUtils();
    private List<ShopCartModel.DataBean> list;  //购物车数据
    private int type;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_EDITER = 1;
    private List<ShopCartModel.DataBean.ShoplistBean> list_checked;  //被选中的商品
    private GoodsStandardPopuWindow    popuWindow;

    @Override
    public void onCreate() {
        getData();
    }

    public void getData(){
        net.getShopCart();
    }

    @Override
    public void onDestroy(){

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()==EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }else if (message.getEventType()==EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }
    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {

    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.SHOPCART:  //购物车商品列表

                ShopCartModel model= (ShopCartModel) message.getData();
                list=model.getData();
                mView.adaphter.setData(model.getData(),0);
                if (list==null){
                    mView.lv_boom.setVisibility(View.GONE);
                }
                if (list.size()==0){
                  mView.lv_boom.setVisibility(View.GONE);
                }
                mView.pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                break;
            case Commons.DELSHOPPINGCAR:   //删除购物车商品
                BaseResponseModel model2= (BaseResponseModel) message.getData();
                if (model2.getCode().equals("100")){  //服务器数据库删除成功
                    for (ShopCartModel.DataBean data:list){
                        data.getShoplist().removeAll(list_checked);
                        if (data.getShoplist().size()==0){   //删除头部视图
                            list.remove(data);
                        }
                    }
                    Logger.i(list.size()+"list size");
                    mView.adaphter.setData(list,type);
                    UpdateBoom();
                }
                Toast.makeText(mView.getActivity(),model2.getMsg(),Toast.LENGTH_SHORT).show();
                break;
            case Commons.UPDATESHOPPINGCAR:   //编辑完成
                BaseResponseModel model3= (BaseResponseModel) message.getData();
                Toast.makeText(mView.getActivity(),model3.getMsg(),Toast.LENGTH_SHORT).show();
                break;
            case Commons.SHOWGOODSSTANDARD:   //购物车商品套餐
                Logger.i(Commons.SHOWGOODSSTANDARD);
                GoodStandardModel model4= (GoodStandardModel) message.getData();
                List<GoodStandardModel.DataBean>  lists=model4.getData();
                popuWindow.setData(lists);
                break;
        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        switch (message.getDataType()){
            case ShopCartAdaphter.POPUWINDOW:  //弹出商品规格页面
                ShopCartModel.DataBean.ShoplistBean bean= (ShopCartModel.DataBean.ShoplistBean) message.getData();
                popuWindow=new GoodsStandardPopuWindow(mView.getActivity(),bean);
                popuWindow.show();
                break;
            case  GoodsStandardPopuWindow.GOODSTANDS:  //商品规格回调给当前页面的数据
                Logger.i("确定套餐选择");
                ShopCartModel.DataBean.ShoplistBean goods_bean= (ShopCartModel.DataBean.ShoplistBean) message.getData();
                for (ShopCartModel.DataBean data:list){
                    for (ShopCartModel.DataBean.ShoplistBean data_bean:data.getShoplist()) {
                         if (data_bean.getGoodsid()==goods_bean.getGoodsid()){
                             data_bean.setGoodsstandardid(goods_bean.getGoodsstandardid());
                             data_bean.setStandardname(goods_bean.getStandardname());
                             data_bean.setPrice(goods_bean.getPrice());
                             data_bean.setIcon(goods_bean.getIcon());
                         }
                    }
                }
                mView.adaphter.setData(list,type);
                break;
        }
    }

    public void UpdateBoom(){
        mView.tv_shopcart_all.setText("全选(0)");
        mView.iv_shopcart_radio.setCheck(false);
    }

    //更新底部布局
    public void UpdateAll(){
        if (mView.iv_shopcart_radio.isCheck){
            mView.iv_shopcart_radio.setCheck(false);
        }else {
            mView.iv_shopcart_radio.setCheck(true);
        }
        List<ShopCartModel.DataBean.ShoplistBean> list_selected=new ArrayList<>();
        for (ShopCartModel.DataBean data:list){
            for (int i=0;i<data.getShoplist().size();i++){
                if (!data.getShoplist().get(i).isChecked()){
                    list_selected.add(data.getShoplist().get(i));
                }
                data.getShoplist().get(i).setChecked(mView.iv_shopcart_radio.isCheck);
            }
            data.getShoplist().get(0).setHeadChecked(mView.iv_shopcart_radio.isCheck);
        }
        mView.adaphter.setData(list,type);
        double total=mView.adaphter.UpdateTotal();
        mView.tv_shopcart_price.setText(total+"");
        mView.tv_shopcart_all.setText("全选("+list_selected.size()+")");
    }

    /**
     * 更新adaphter
     * @param type   0编辑前  1编辑后
     */
    public void UpdateUi(int type){
        this.type=type;
        mView.adaphter.setData(list,type);
        if (type==TYPE_NORMAL){
            double total=mView.adaphter.UpdateTotal();
            mView.tv_shopcart_price.setText(total+"");
        }
    }

    /**
     * 提交或者删除
     */
    public void submit(){
        switch (type){
            case TYPE_NORMAL:
                itemChecked();
                if (list_checked.size()==0){
                    BaseActivity.showToast(mView.getActivity(),"请选择要结算的商品");
                    return;
                }
                StringBuilder params=new StringBuilder();
                for (int i = 0; i <list_checked.size() ; i++) {
                    ShopCartModel.DataBean.ShoplistBean bean=list_checked.get(i);
                    params.append(bean.getCarid());
                    if (i!=(list_checked.size()-1)){
                        params.append(",");
                    }
                }
                Skip.toResult(mView.getActivity(),params.toString());
                break;
            case TYPE_EDITER:
                itemChecked();
                StringBuilder sb=new StringBuilder();
                for (int i=0;i<list_checked.size();i++){
                    ShopCartModel.DataBean.ShoplistBean bean=list_checked.get(i);
                    sb.append(bean.getCarid());
                    if (i!=list_checked.size()-1){
                        sb.append(",");
                    }
                }
                net.getDelCart(sb.toString());
                break;
        }
    }

    private void itemChecked(){
        List <ShopCartModel.DataBean.ShoplistBean> list_selected=new ArrayList<>();  //被选中的ITEM
        for (ShopCartModel.DataBean data:list){
            for (ShopCartModel.DataBean.ShoplistBean bean:data.getShoplist()){
                if (bean.isChecked()){
                    list_selected.add(bean);
                }
            }
        }
        list_checked=list_selected;
    }

    public void UpdateShopCart(){
        StringBuilder sb=new StringBuilder();
        for (ShopCartModel.DataBean data:list){
            for (int i=0;i<data.getShoplist().size();i++){
                ShopCartModel.DataBean.ShoplistBean bean= data.getShoplist().get(i);
                sb.append(bean.getCarid());
                sb.append("/");
                sb.append(bean.getNum()+"");
                sb.append("/");
                sb.append(bean.getGoodsstandardid());
                if (i!=data.getShoplist().size()-1){
                    sb.append(",");
                }
            }
        }
        net.getUpdateCart(sb.toString());
    }
}
