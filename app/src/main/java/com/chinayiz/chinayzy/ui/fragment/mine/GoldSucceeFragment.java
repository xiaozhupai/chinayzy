package com.chinayiz.chinayzy.ui.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/28 14:15
 * Class GoldSucceeFragment  积分充值成功/提现成功
 */
public class GoldSucceeFragment extends AbsFragment implements View.OnClickListener {
    /**
     * 充值类型
     */
    public static final int TYPE_ONE = 1;
    /**
     * 提现类型
     */
    public static final int TYPE_TOW = 2;
    private int type;
    private String valuel1, valuel2;
    private ImageView iv_back_button;
    private TextView tv_actionbar_title;
    private TextView tv_result;
    private TextView tv_titel;
    private TextView tv_value;
    private TextView tv_titel1;
    private TextView tv_value1;
    private Button bt_inPut;

    public GoldSucceeFragment(int type, String valuel1, String valuel2) {
        this.type = type;
        this.valuel1 = valuel1;
        this.valuel2 = valuel2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goldsuccee, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TYPE_ONE == type) {
            tv_actionbar_title.setText("充值");
            tv_result.setText("恭喜您充值成功");
            tv_titel.setText("充值账号");
            tv_value.setText(valuel1);
            tv_titel1.setText("充值金额");
            tv_value1.setText("￥"+valuel2);
        }else {
            tv_actionbar_title.setText("提现");
            tv_result.setText("恭喜您申请提现成功！");
            tv_titel.setText("提现账号");
            tv_value.setText(valuel1);
            tv_titel1.setText("提现金额");
            tv_value1.setText("￥"+valuel2);
        }
    }

    @Override
    public void disposeNetMsg(EventMessage message) {

    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

    }

    @Override
    protected void initView(View view) {
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        iv_back_button.setOnClickListener(this);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);
        view.findViewById(R.id.cb_edit_button).setVisibility(View.GONE);
        view.findViewById(R.id.iv_shopcart).setVisibility(View.GONE);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        tv_titel = (TextView) view.findViewById(R.id.tv_titel);
        tv_value = (TextView) view.findViewById(R.id.tv_value);
        tv_titel1 = (TextView) view.findViewById(R.id.tv_titel1);
        tv_value1 = (TextView) view.findViewById(R.id.tv_value1);
        bt_inPut = (Button) view.findViewById(R.id.bt_inPut);
        bt_inPut.setOnClickListener(this);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType() == EventMessage.NET_EVENT) {
            disposeNetMsg(message);
        }
        if (message.getEventType() == EventMessage.ERROR_EVENT) {
            BaseActivity.showToast(getActivity(), "未知错误，请重试");
        }
    }


    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType() == EventMessage.INFORM_EVENT) {
            disposeInfoMsg(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button:
            case R.id.bt_inPut:
                getActivity().onBackPressed();
                break;
        }
    }
}
