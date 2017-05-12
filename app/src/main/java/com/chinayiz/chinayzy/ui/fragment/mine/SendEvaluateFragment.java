package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.imageSelected.view.MyGridView;
import com.chinayiz.chinayzy.presenter.SendEvaluatePresenter;
import com.chinayiz.chinayzy.widget.RatingBar;

/**   发表评价
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class SendEvaluateFragment extends BaseFragment<SendEvaluatePresenter> implements View.OnClickListener {
    private ImageView iv_order_image;
    private TextView tv_order_title;
    private TextView tv_order_norm;
    private TextView tv_order_price;
    private TextView tv_order_num;
    private EditText et_suggest;
    private MyGridView gv_photo;
    private RatingBar rb_described;
    private RatingBar rb_speed;
    private RatingBar rb_service;
    private ImageView iv_evaluate;
    private TextView tv_evaluate_submit;
    private LinearLayout lv_boom;

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
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_send_evaluate, container, false);
        iv_order_image = (ImageView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.iv_order_image);
        tv_order_title = (TextView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.tv_order_title);
        tv_order_norm = (TextView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.tv_order_norm);
        tv_order_price = (TextView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.tv_order_price);
        tv_order_num = (TextView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.tv_order_num);
        et_suggest = (EditText) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.et_suggest);
        gv_photo = (MyGridView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.gv_photo);
        rb_described = (RatingBar) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.rb_described);
        rb_speed = (RatingBar) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.rb_speed);
        rb_service = (RatingBar) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.rb_service);
        iv_evaluate = (ImageView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.iv_evaluate);
        tv_evaluate_submit = (TextView) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.tv_evaluate_submit);
        lv_boom = (LinearLayout) inflater.inflate(R.layout.fragment_send_evaluate, container, false).findViewById(R.id.lv_boom);
        iv_evaluate.setOnClickListener(this);
        tv_evaluate_submit.setOnClickListener(this);
        return view;

    }

    @Override
    public SendEvaluatePresenter initPresenter() {
        return new SendEvaluatePresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    private void submit() {
        // validate
        String suggest = et_suggest.getText().toString().trim();
        if (TextUtils.isEmpty(suggest)) {
            Toast.makeText(getActivity(), "发表您对商品的评价,看法和建议", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.iv_evaluate:   //是否匿名

                 break;
             case R.id.tv_evaluate_submit:   //提交评价

                 break;
         }
    }
}
