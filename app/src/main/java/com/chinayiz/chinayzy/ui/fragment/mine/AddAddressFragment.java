package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.response.AddressListModel;
import com.chinayiz.chinayzy.presenter.AddAddressPresenter;
import com.chinayiz.chinayzy.widget.PickView;

/** 添加/编辑收货地址
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AddAddressFragment extends BaseFragment<AddAddressPresenter> implements View.OnClickListener {
    public  EditText et_username;
    public  EditText et_phone;
    public  TextView tv_address;
    public  TextView tv_part;
    public  ImageView iv_address;
    public  EditText et_address;
    public TextView tv_delete;

    public int type;  //0 新增   1是编辑
    public static final int ADD=0;
    public static final int EDIT=1;
    public AddressListModel.DataBean bean;
    private PickView pickView;
    public int index;


    public AddAddressFragment(int type,int index) {
        this.type = type;
        this.index=index;
    }

    public AddAddressFragment(int type , AddressListModel.DataBean bean,int index){
        this.type=type;
        this.bean=bean;
        this.index=index;
    }

    public AddAddressFragment() {
    }

    @Override
    protected void onVisible() {

    }


    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onInitActionBar(BaseActivity mineActivity) {
        if (type==ADD){
            mineActivity.mTvActionBarTitle.setText("增加新地址");
        }else {
            mineActivity.mTvActionBarTitle.setText("编辑地址");
        }
        mineActivity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        mineActivity.mCbActionBarEdit.setText("保存");
        mineActivity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.submit();
            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_address,null);
        et_username = (EditText) view.findViewById(R.id.et_username);
        et_phone = (EditText)view. findViewById(R.id.et_phone);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_part = (TextView) view.findViewById(R.id.tv_part);
        iv_address = (ImageView)view. findViewById(R.id.iv_address);
        et_address = (EditText)view. findViewById(R.id.et_address);
        tv_delete = (TextView) view.findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(this);
        tv_part.setOnClickListener(this);
        if (type==ADD){
            tv_delete.setVisibility(View.GONE);
        }else {
            tv_delete.setVisibility(View.VISIBLE);
            mPresenter.specificaddress=bean.getSpecificaddress();
        }
        if (bean!=null){
            setData();
        }
        return view;
    }

    private void setData() {
        et_username.setText(bean.getConsignee());
        et_phone.setText(bean.getPhone());
        et_address.setText(bean.getAddress());
        tv_part.setText(bean.getArea());
        tv_part.setTextColor(Color.BLACK);
    }

    @Override
    public AddAddressPresenter initPresenter() {
        return new AddAddressPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_delete:
                mPresenter.delete();
                break;
            case R.id.tv_part:
                if (pickView==null){
                    pickView=new PickView(getActivity());
                }
                pickView.show();

                break;
        }
    }
}
