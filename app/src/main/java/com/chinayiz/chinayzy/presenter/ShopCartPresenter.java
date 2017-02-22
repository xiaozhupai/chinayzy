package com.chinayiz.chinayzy.presenter;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chinayiz.chinayzy.adapter.ShopCartAdaphter;
import com.chinayiz.chinayzy.base.BasePresenter;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.GoodStandardModel;
import com.chinayiz.chinayzy.entity.response.ShopCartModel;
import com.chinayiz.chinayzy.net.Contants;
import com.chinayiz.chinayzy.net.ContentRequestUtils;
import com.chinayiz.chinayzy.ui.fragment.cart.ResultFragment;
import com.chinayiz.chinayzy.ui.fragment.cart.ShopCartFragment;
import com.chinayiz.chinayzy.utils.WindowMagerUntil;
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
    private ContentRequestUtils net=ContentRequestUtils.getRequestUtils();
    private List<ShopCartModel.DataBean> list;
    private int type;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_EDITER = 1;
    private List<ShopCartModel.DataBean.ShoplistBean> list_checked;
    private GoodsStandardPopuWindow    popuWindow;

    @Override
    public void onCreate() {
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
            case Contants.SHOPCART:  //购物车商品列表
                ShopCartModel model= (ShopCartModel) message.getData();
                list=model.getData();
                mView.adaphter.setData(model.getData(),0);
                break;
            case Contants.DELSHOPPINGCAR:   //删除购物车商品
                BaseResponseModel model2= (BaseResponseModel) message.getData();
                if (model2.getCode().equals("100")){  //服务器数据库删除成功
                    for (ShopCartModel.DataBean data:list){
                        data.getShoplist().removeAll(list_checked);
                        if (data.getShoplist().size()==0){
                            list.remove(data);
                        }
                    }
                    Logger.i(list.size()+"list size");
                    mView.adaphter.setData(list,type);
                    UpdateAll();
                }
                Toast.makeText(mView.getActivity(),model2.getMsg(),Toast.LENGTH_SHORT).show();
                break;
            case Contants.UPDATESHOPPINGCAR:   //编辑完成
                BaseResponseModel model3= (BaseResponseModel) message.getData();
                Toast.makeText(mView.getActivity(),model3.getMsg(),Toast.LENGTH_SHORT).show();
                break;
            case Contants.SHOWGOODSSTANDARD:   //购物车商品套餐
                Logger.i(Contants.SHOWGOODSSTANDARD);
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
                popuWindow.showAtLocation(mView.rl_shopcart,Gravity.BOTTOM,0,0);
                popuWindow.setTouchable(true);
                popuWindow.setOutsideTouchable(true);
                WindowMagerUntil.backgroundAlpha(0.5f,mView.getActivity());
                popuWindow.setOnDismissListener(new PopupWindow.OnDismissListener(){
                    @Override
                    public void onDismiss() {
                        WindowMagerUntil.backgroundAlpha(1.0f,mView.getActivity());
                    }
                });
                break;
            case  GoodsStandardPopuWindow.GoodStands:  //商品规格回调给当前页面的数据
                ShopCartModel.DataBean.ShoplistBean goods_bean= (ShopCartModel.DataBean.ShoplistBean) message.getData();
                for (ShopCartModel.DataBean data:list){
                    for (ShopCartModel.DataBean.ShoplistBean data_bean:data.getShoplist()) {
                         if (data_bean.getGoodsid()==goods_bean.getGoodsid()){
                             data_bean.setGoodsstandardid(goods_bean.getGoodsstandardid());
                             data_bean.setStandardname(goods_bean.getStandardname());
                             data_bean.setPrice(goods_bean.getPrice());
                         }
                    }
                }
                mView.adaphter.setData(list,type);
                break;
        }
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
                mView.startFragment(new ResultFragment(),"ResultFragment");
                break;
            case TYPE_EDITER:
                List <ShopCartModel.DataBean.ShoplistBean> list_selected=new ArrayList<>();  //被选中的ITEM
                for (ShopCartModel.DataBean data:list){
                    for (ShopCartModel.DataBean.ShoplistBean bean:data.getShoplist()){
                        if (bean.isChecked()){
                            list_selected.add(bean);
                        }
                    }
                }
                list_checked=list_selected;
                StringBuilder sb=new StringBuilder();
                for (int i=0;i<list_selected.size();i++){
                    ShopCartModel.DataBean.ShoplistBean bean=list_selected.get(i);
                    sb.append(bean.getCarid());
                    if (i!=list_selected.size()-1){
                        sb.append(",");
                    }
                }
                net.getDelCart(sb.toString());
                break;
        }
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
