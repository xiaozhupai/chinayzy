package com.chinayiz.chinayzy.ui.fragment.mine;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.APP;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.EvalueResultPresenter;

/**
 *   交易，评价 结果
 * A simple {@link } subclass.
 */
public class EvalueResultFragment extends BaseFragment<EvalueResultPresenter> implements View.OnClickListener {


    private ImageView iv_result;
    private TextView tv_result;
    private TextView tv_to_others;
    private String title;
    private int type;
    private static final int EVALUE=0;
    private static final int TRADE=1;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onInintData(Bundle bundle) {
        this.title=bundle.getString("title");
        this.type=bundle.getInt("type");
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText(title);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_evalue_result,null);
        iv_result = (ImageView)view.findViewById(R.id.iv_result);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        tv_to_others = (TextView)view.findViewById(R.id.tv_to_others);
        tv_to_others.setOnClickListener(this);
        switch (type){
            case EVALUE:
                tv_result.setText("感谢您宝贵的评价!");
                iv_result.setImageResource(R.mipmap.img_evalue_result);
                break;
            case TRADE:
                tv_result.setText("交易成功!卖家将收到您的付款~");
                iv_result.setImageResource(R.mipmap.img_trade_success);
                break;
        }
        return view;
    }

    @Override
    public EvalueResultPresenter initPresenter() {
        return new EvalueResultPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_others:
                APP.exit();
                break;
        }
    }
}
