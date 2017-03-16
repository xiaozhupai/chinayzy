package com.chinayiz.chinayzy.ui.fragment.mine;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.ScreListAdapter;
import com.chinayiz.chinayzy.base.AbsFragment;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.DealListModel;
import com.chinayiz.chinayzy.net.Commons;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/8 17:00
 * Class DealListFragment  积分账单
 */
public class DealListFragment extends AbsFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private CheckBox cb_dealType;
    private ListView lv_dealList;
    private PopupWindow mPopupWindow;
    private View popupWindowview;
    private ScreListAdapter mAdapter;
    private boolean windIsHide;
    private View mPopwind;
    private View mNotData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deallist, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void initView(View view) {
        mNotData = view.findViewById(R.id.view_notEarnings);
        mProgress = view.findViewById(R.id.ll_progress);
        iv_back_button = (ImageView) view.findViewById(R.id.iv_back_button);
        iv_back_button.setOnClickListener(this);
        tv_actionbar_title = (TextView) view.findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setText("积分账单");
        tv_actionbar_title.setTextColor(Color.BLACK);
        Paint paint = tv_actionbar_title.getPaint();
        paint.setFakeBoldText(false);
        view.findViewById(R.id.iv_more_button).setVisibility(View.GONE);
        cb_dealType = (CheckBox) view.findViewById(R.id.cb_dealType);
        cb_dealType.setOnCheckedChangeListener(this);
        lv_dealList = (ListView) view.findViewById(R.id.lv_dealList);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRequestUtils.getDealList("0");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button:
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            windIsHide = true;
        }
        showDealTypWind(buttonView);
    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()) {
            case Commons.DEAL_LIST: {
                DealListModel model = (DealListModel) message.getData();
                if (model.getData() != null && model.getData().size() > 0) {
                    mAdapter = new ScreListAdapter(getActivity(), (ArrayList<DealListModel.DataBean>) model.getData());
                    lv_dealList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    mNotData.setVisibility(View.GONE);
                }
                mProgress.setVisibility(View.GONE);
                break;
            }
        }
    }

    private void showDealTypWind(CompoundButton buttonView) {
        if (popupWindowview == null) {
            popupWindowview = View.inflate(getActivity(), R.layout.popwind_deal_type, null);
            initPopupWind(popupWindowview);

        }
        PopupWindow popupWindow = new PopupWindow(getActivity());
        /**
         * 获取屏幕尺寸
         */
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        double widt = width / 2.88;
        double heigt = height / 3.66;
        double xoff=width/12;

        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(popupWindowview, (int)widt, (int)heigt, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (windIsHide) {
                        windIsHide = false;
                        cb_dealType.setChecked(false);
                    }
                    return false;
                    // 这里如果返回true的话，touch事件将被拦截
                    // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                }
            });
            //要为popWindow设置一个背景才有效
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        }
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        mPopupWindow.showAsDropDown(buttonView, -(int)xoff, 1);
    }

    /**
     * 初始化popupWindowView
     *
     * @param popupWindowview
     */
    private void initPopupWind(View popupWindowview) {
        RadioGroup group = (RadioGroup) popupWindowview.findViewById(R.id.rg_dealType);
        RadioButton button = (RadioButton) group.findViewById(R.id.rb_all);
        button.setChecked(true);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all: {
                        cb_dealType.setText("全部");
                        mRequestUtils.getDealList("0");
                        mPopupWindow.dismiss();
                        break;
                    }
                    case R.id.rb_chongzhi: {
                        cb_dealType.setText("充值");
                        mRequestUtils.getDealList("1");
                        mPopupWindow.dismiss();
                        break;
                    }
                    case R.id.rb_getdeal: {
                        cb_dealType.setText("提现");
                        mRequestUtils.getDealList("2");
                        mPopupWindow.dismiss();
                        break;
                    }
                    case R.id.rb_qita: {
                        cb_dealType.setText("其他");
                        mRequestUtils.getDealList("3");
                        mPopupWindow.dismiss();
                        break;
                    }
                }

            }
        });
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {

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
}
