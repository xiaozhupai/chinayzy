package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.AddressListModel;
import com.chinayiz.chinayzy.presenter.AddressListPresenter;
import com.chinayiz.chinayzy.views.CheckImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7.收货地址
 */

public class AddressAdaphter extends BaseInectAdaphter<AddressListModel.DataBean> {
    /**
     * 选择地址（大概地址）
     */
    public static final String CLICK_ADDRESS ="AddressAdaphtes_click";

    public AddressAdaphter(Context context, List<AddressListModel.DataBean> list) {
        this.context=context;
        this.lists=list;
        
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            view = View.inflate(context, R.layout.address_list_item, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final AddressListModel.DataBean bean=lists.get(i);
        viewHolder.tv_username.setText(bean.getConsignee());
        viewHolder.tv_address.setText(bean.getArea()+bean.getAddress());
        String phone=bean.getPhone().substring(0,3)+"****"+bean.getPhone().substring(bean.getPhone().length()-4,bean.getPhone().length());
        viewHolder.tv_userphone.setText(phone);
        viewHolder.clickAddress.setTag(R.id.tag_click,bean.getArea());
        viewHolder.clickAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT,CLICK_ADDRESS,v.getTag(R.id.tag_click)));
            }
        });
        if (bean.getIsdefault()!=null){
            if (bean.getIsdefault().equals("1")){
                viewHolder.iv_isdefault.setCheck(true);
                viewHolder.iv_isdefault.setImageResource(R.mipmap.radio_selected);
            }else {
                viewHolder.iv_isdefault.setCheck(false);
                viewHolder.iv_isdefault.setImageResource(R.mipmap.radio_normal);
            }
        }
        viewHolder.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, AddressListPresenter.DELETE,bean.getAddressid()+""));
            }
        });
        viewHolder.ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, AddressListPresenter.EDITER,bean));
            }
        });
        viewHolder.iv_isdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventMessage(EventMessage.INFORM_EVENT, AddressListPresenter.ISDEFAULT,bean.getAddressid()+""));
            }
        });
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public View clickAddress;
        public TextView tv_username;
        public TextView tv_userphone;
        public TextView tv_address;
        public CheckImageView iv_isdefault;
        public LinearLayout ll_edit;
        public LinearLayout ll_delete;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.clickAddress=rootView.findViewById(R.id.ll_address);
            this.tv_username = (TextView) rootView.findViewById(R.id.tv_username);
            this.tv_userphone = (TextView) rootView.findViewById(R.id.tv_userphone);
            this.tv_address = (TextView) rootView.findViewById(R.id.tv_address);
            this.iv_isdefault = (CheckImageView) rootView.findViewById(R.id.iv_isdefault);
            this.ll_edit = (LinearLayout) rootView.findViewById(R.id.ll_edit);
            this.ll_delete = (LinearLayout) rootView.findViewById(R.id.ll_delete);
        }

    }
}
