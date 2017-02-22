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
import com.orhanobut.logger.Logger;

/**
 * 个人资料
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends BaseFragment<PersonPresenter> implements View.OnClickListener {

    private ImageView iv_head_right;
    private ImageView iv_person_head;
    private RelativeLayout rl_person_head;
    private TextView tv_person_username;
    private TextView tv_person_email;
    private ImageView iv_email_arrow;
    private RelativeLayout rl_person_email;
    private TextView tv_person_sex;
    private ImageView iv_sex_arrow;
    private RelativeLayout rl_person_sex;
    private TextView tv_person_height;
    private ImageView iv_height_arrow;
    private RelativeLayout rl_person_height;
    private TextView tv_person_weight;
    private ImageView iv_weight_arrow;
    private RelativeLayout rl_person_weight;
    private TextView tv_person_factname;
    private ImageView iv_factname_arrow;
    private RelativeLayout rl_person_factname;
    private TextView tv_person_card;
    private ImageView iv_card_arrow;
    private RelativeLayout rl_person_card;
    private ImageView iv_address_arrow;
    private RelativeLayout rl_person_address;
    private ImageView iv_label_arrow;
    private RelativeLayout rl_person_label;

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
        View view = inflater.inflate(R.layout.fragment_person, null);
        iv_head_right = (ImageView) view.findViewById(R.id.iv_head_right);
        iv_head_right.setOnClickListener(this);
        iv_person_head = (ImageView)view.findViewById(R.id.iv_person_head);
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

    }
}
