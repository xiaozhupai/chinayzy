package com.chinayiz.chinayzy.adapter.viewHolder;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.adapter.NongYeHomeRecylAdapter;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/1/11 17:00
 * Class NongYe_HomeTypeMenu 生态农业首页类型菜单
 */
public class NY_HomeTypeMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
    public LinearLayout mLyTypeBtn1;
    public LinearLayout mLyTypeBtn2;
    public LinearLayout mLyTypeBtn3;
    public LinearLayout mLyTypeBtn4;
    public LinearLayout mLyTypeBtn5;
    public NY_HomeTypeMenu(View itemView) {
        super(itemView);
        mLyTypeBtn1 = (LinearLayout) itemView.findViewById(R.id.ly_type_btn1);
        mLyTypeBtn2 = (LinearLayout) itemView.findViewById(R.id.ly_type_btn2);
        mLyTypeBtn3 = (LinearLayout) itemView.findViewById(R.id.ly_type_btn3);
        mLyTypeBtn4 = (LinearLayout) itemView.findViewById(R.id.ly_type_btn4);
        mLyTypeBtn5 = (LinearLayout) itemView.findViewById(R.id.ly_type_btn5);

        mLyTypeBtn1.setOnClickListener(this);
        mLyTypeBtn2.setOnClickListener(this);
        mLyTypeBtn3.setOnClickListener(this);
        mLyTypeBtn4.setOnClickListener(this);
        mLyTypeBtn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_type_btn1://野生农业
                EventBus.getDefault()
                        .post(new EventMessage(EventMessage.INFORM_EVENT,
                                NongYeHomeRecylAdapter.CLICK_MENU,"1"));
                Logger.i("野生农业");
                break;
            case R.id.ly_type_btn2://富硒农业
                EventBus.getDefault()
                        .post(new EventMessage(EventMessage.INFORM_EVENT,
                                NongYeHomeRecylAdapter.CLICK_MENU,"2"));
                Logger.i("富硒农业");
                break;
            case R.id.ly_type_btn3://有机农业
                EventBus.getDefault()
                        .post(new EventMessage(EventMessage.INFORM_EVENT,
                                NongYeHomeRecylAdapter.CLICK_MENU,"3"));
                Logger.i("有机农业");
                break;
            case R.id.ly_type_btn4://食品组合
                EventBus.getDefault()
                        .post(new EventMessage(EventMessage.INFORM_EVENT,
                                NongYeHomeRecylAdapter.CLICK_MENU,"4"));
                Logger.i("食品组合");
                break;
            case R.id.ly_type_btn5://关于硒
                EventBus.getDefault()
                        .post(new EventMessage(EventMessage.INFORM_EVENT,
                                NongYeHomeRecylAdapter.CLICK_MENU,"5"));
                break;
        }
    }
}
