package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
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
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.PersonPresenter;
import com.chinayiz.chinayzy.views.CircleImageView;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;
import com.chinayiz.chinayzy.widget.TagListView;
import com.orhanobut.logger.Logger;

/**
 * 个人资料
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PersonFragment extends BaseFragment<PersonPresenter> implements View.OnClickListener {

    public ImageView iv_head_right;
    public CircleImageView iv_person_head;
    public RelativeLayout rl_person_head;
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

    public TextView tv_person_username,tv_person_birthday,tv_person_usualplace,tv_person_ismarriage,tv_person_education,tv_person_politics;
    public RelativeLayout rl_person_username;
    public PullToRefreshLayout refresh_view;
    private RelativeLayout  rl_person_birthday,rl_person_ismarriage,rl_person_education,rl_person_politics,rl_person_usualplace;

    @Override
    public void onStart() {
        super.onStart();
        Logger.i("start ----PersonFragment");

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
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("个人资料");
        activity.mCbActionBarEdit.setVisibility(View.GONE);
        Logger.i("onInitActionBar------------PersonFragment");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person, null);
        refresh_view= (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        iv_head_right = (ImageView) view.findViewById(R.id.iv_head_right);
        iv_head_right.setOnClickListener(this);
        iv_person_head = (CircleImageView)view.findViewById(R.id.iv_person_head);
        iv_person_head.setOnClickListener(this);
        rl_person_head = (RelativeLayout) view.findViewById(R.id.rl_person_head);
        rl_person_head.setOnClickListener(this);
        tv_person_username = (TextView) view.findViewById(R.id.tv_person_username);
        tv_person_username.setOnClickListener(this);
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
        iv_address_arrow = (ImageView) view.findViewById(R.id.iv_address_arrow);
        iv_address_arrow.setOnClickListener(this);
        rl_person_address = (RelativeLayout) view.findViewById(R.id.rl_person_address);
        rl_person_address.setOnClickListener(this);
        rl_person_birthday= (RelativeLayout) view.findViewById(R.id.rl_person_birthday);
        rl_person_ismarriage= (RelativeLayout) view.findViewById(R.id.rl_person_ismarriage);
        rl_person_education= (RelativeLayout) view.findViewById(R.id.rl_person_education);
        rl_person_politics= (RelativeLayout) view.findViewById(R.id.rl_person_politics);
        rl_person_usualplace= (RelativeLayout) view.findViewById(R.id.rl_person_usualplace);
        tv_person_birthday= (TextView) view.findViewById(R.id.tv_person_birthday);
        tv_person_usualplace= (TextView) view.findViewById(R.id.tv_person_usualplace);
        tv_person_ismarriage= (TextView) view.findViewById(R.id.tv_person_ismarriage);
        tv_person_education= (TextView) view.findViewById(R.id.tv_person_education);
        tv_person_politics= (TextView) view.findViewById(R.id.tv_person_politics);

        rl_person_birthday.setOnClickListener(this);
        rl_person_ismarriage.setOnClickListener(this);
        rl_person_education.setOnClickListener(this);
        rl_person_politics.setOnClickListener(this);
        rl_person_usualplace.setOnClickListener(this);

//        iv_label_arrow = (ImageView) view.findViewById(R.id.iv_label_arrow);
//        iv_label_arrow.setOnClickListener(this);
//        rl_person_label = (RelativeLayout) view.findViewById(R.id.rl_person_label);
//        rl_person_label.setOnClickListener(this);
        tv_person_username= (TextView) view.findViewById(R.id.tv_person_username);
        rl_person_username= (RelativeLayout) view.findViewById(R.id.rl_person_username);
        rl_person_username.setOnClickListener(this);
        tlv_list= (TagListView) view.findViewById(R.id.tlv_list);
        refresh_view.setLoadMoreVisiable(false);
        refresh_view.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mPresenter.getData();

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
          view.findViewById(R.id.loadlayout).setVisibility(View.GONE);
//        tlv_list.setTagViewBackgroundRes(R.drawable.label_black);
//        tlv_list.setTagViewTextColorRes(Color.BLACK);

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
            case R.id.rl_person_sex:
                Logger.i("性别");
                mPresenter.toSex();
                break;

            case R.id.rl_person_height:
                mPresenter.toHeight();
                Logger.i("身高");
                break;
            case R.id.rl_person_weight:
                mPresenter.toWeight();
                Logger.i("体重");
                break;
            case R.id.rl_person_birthday:
                Logger.i("出生日期");
                mPresenter.toBirthday();
                break;
            case R.id.rl_person_ismarriage:
                Logger.i("婚姻状况");
                mPresenter.toIsmarriage();
                break;
            case R.id.rl_person_education:
                Logger.i("学历");
                mPresenter.toEducation();
                break;
            case R.id.rl_person_politics:
                Logger.i("政治面貌");
                mPresenter.toPolitics();
                break;
            case R.id.rl_person_usualplace:
                Logger.i("常驻地");
                mPresenter.toUsualplace();
                break;

//            case R.id.rl_person_label:
//                mPresenter.toLabel();
//                Logger.i("标签");
//                break;
            case R.id.rl_person_address:
                Logger.i("收货地址");
                mPresenter.toAddress();
                break;
            case R.id.rl_person_head:
                Logger.i("头像");
                mPresenter.toHead();
                break;
            case R.id.rl_person_username:
                Logger.i("用户名");
                mPresenter.toUsername();
                break;
        }
    }
}
