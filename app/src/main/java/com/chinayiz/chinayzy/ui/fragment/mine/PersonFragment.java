package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.PersonPresenter;
import com.chinayiz.chinayzy.ui.activity.MineActivity;
import com.chinayiz.chinayzy.views.CircleImageView;
import com.chinayiz.chinayzy.widget.TagListView;
import com.orhanobut.logger.Logger;

/**
 * 个人资料
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends BaseFragment<PersonPresenter> implements View.OnClickListener {

    public ImageView iv_head_right;
    public CircleImageView iv_person_head;
    public RelativeLayout rl_person_head;
    public TextView tv_person_username;
    public TextView tv_person_email;
    public ImageView iv_email_arrow;
    public RelativeLayout rl_person_email;
    public TextView tv_person_sex;
    public ImageView iv_sex_arrow;
    public RelativeLayout rl_person_sex;
    public TextView tv_person_height;
    public ImageView iv_height_arrow;
    public RelativeLayout rl_person_height;
    public TextView tv_person_weight;
    public ImageView iv_weight_arrow;
    public RelativeLayout rl_person_weight;
    public TextView tv_person_factname;
    public ImageView iv_factname_arrow;
    public RelativeLayout rl_person_factname;
    public TextView tv_person_card;
    public ImageView iv_card_arrow;
    public RelativeLayout rl_person_card;
    public ImageView iv_address_arrow;
    public RelativeLayout rl_person_address;
    public ImageView iv_label_arrow;
    public RelativeLayout rl_person_label;
    public TagListView tlv_list;
    public  MineActivity activity;

    @Override
    public void onStart() {
        super.onStart();
        Logger.i("start ----PersonFragment");

    }

    @Override
    protected void onVisible() {
        MineActivity activity= (MineActivity) getActivity();
        activity.mTvActionBarTitle.setText("个人资料");
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         activity= (MineActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_person, null);
        iv_head_right = (ImageView) view.findViewById(R.id.iv_head_right);
        iv_head_right.setOnClickListener(this);
        iv_person_head = (CircleImageView)view.findViewById(R.id.iv_person_head);
        iv_person_head.setOnClickListener(this);
        rl_person_head = (RelativeLayout) view.findViewById(R.id.rl_person_head);
        rl_person_head.setOnClickListener(this);
        tv_person_username = (TextView) view.findViewById(R.id.tv_person_username);
        tv_person_username.setOnClickListener(this);
        tv_person_email = (TextView) view.findViewById(R.id.tv_person_email);
        tv_person_email.setOnClickListener(this);
        iv_email_arrow = (ImageView) view.findViewById(R.id.iv_email_arrow);
        iv_email_arrow.setOnClickListener(this);
        rl_person_email = (RelativeLayout) view.findViewById(R.id.rl_person_email);
        rl_person_email.setOnClickListener(this);
        tv_person_sex = (TextView)view.findViewById(R.id.tv_person_sex);
        tv_person_sex.setOnClickListener(this);
        iv_sex_arrow = (ImageView) view.findViewById(R.id.iv_sex_arrow);
        iv_sex_arrow.setOnClickListener(this);
        rl_person_sex = (RelativeLayout) view.findViewById(R.id.rl_person_sex);
        rl_person_sex.setOnClickListener(this);
        tv_person_height = (TextView) view.findViewById(R.id.tv_person_height);
        tv_person_height.setOnClickListener(this);
        iv_height_arrow = (ImageView) view.findViewById(R.id.iv_height_arrow);
        iv_height_arrow.setOnClickListener(this);
        rl_person_height = (RelativeLayout) view.findViewById(R.id.rl_person_height);
        rl_person_height.setOnClickListener(this);
        tv_person_weight = (TextView)view.findViewById(R.id.tv_person_weight);
        tv_person_weight.setOnClickListener(this);
        iv_weight_arrow = (ImageView) view.findViewById(R.id.iv_weight_arrow);
        iv_weight_arrow.setOnClickListener(this);
        rl_person_weight = (RelativeLayout) view.findViewById(R.id.rl_person_weight);
        rl_person_weight.setOnClickListener(this);
        tv_person_factname = (TextView) view.findViewById(R.id.tv_person_factname);
        tv_person_factname.setOnClickListener(this);
        iv_factname_arrow = (ImageView) view.findViewById(R.id.iv_factname_arrow);
        iv_factname_arrow.setOnClickListener(this);
        rl_person_factname = (RelativeLayout) view.findViewById(R.id.rl_person_factname);
        rl_person_factname.setOnClickListener(this);
        tv_person_card = (TextView) view.findViewById(R.id.tv_person_card);
        tv_person_card.setOnClickListener(this);
        iv_card_arrow = (ImageView) view.findViewById(R.id.iv_card_arrow);
        iv_card_arrow.setOnClickListener(this);
        rl_person_card = (RelativeLayout) view.findViewById(R.id.rl_person_card);
        rl_person_card.setOnClickListener(this);
        iv_address_arrow = (ImageView) view.findViewById(R.id.iv_address_arrow);
        iv_address_arrow.setOnClickListener(this);
        rl_person_address = (RelativeLayout) view.findViewById(R.id.rl_person_address);
        rl_person_address.setOnClickListener(this);
        iv_label_arrow = (ImageView) view.findViewById(R.id.iv_label_arrow);
        iv_label_arrow.setOnClickListener(this);
        rl_person_label = (RelativeLayout) view.findViewById(R.id.rl_person_label);
        rl_person_label.setOnClickListener(this);
        tlv_list= (TagListView) view.findViewById(R.id.tlv_list);
        return view;
    }

    @Override
    public PersonPresenter initPresenter() {
        return new PersonPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case  R.id.rl_person_email:
               Logger.i("email");
               activity.addFragment(new EmailFragment(tv_person_email.getText().toString()));
           break;
           case R.id.rl_person_sex:
               Logger.i("性别");
               activity.addFragment(new SexFragment(tv_person_sex.getText().toString()));
               break;
           case R.id.rl_person_factname:
               Logger.i("真实姓名");
               activity.addFragment(new TrueNameFragment(tv_person_factname.getText().toString()));
               break;
           case R.id.rl_person_height:
               activity.addFragment(new HeightFragment(tv_person_height.getText().toString()));
               Logger.i("身高");
               break;
           case R.id.rl_person_weight:
               activity.addFragment(new WeightFragment(tv_person_weight.getText().toString()));
               Logger.i("体重");
               break;
           case R.id.rl_person_card:
               activity.addFragment(new CardFragment(tv_person_card.getText().toString()));
               Logger.i("身份证");
               break;
           case R.id.rl_person_label:
               activity.addFragment(new LabelFragment(mPresenter.tags_list));
               Logger.i("标签");
               break;
           case R.id.rl_person_address:
               Logger.i("收货地址");
               break;
           case R.id.rl_person_head:
               Logger.i("头像");

               break;
       }
    }
}
