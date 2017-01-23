package com.chinayiz.chinayzy.adapter.viewHolder;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import com.chinayiz.chinayzy.R;
import com.orhanobut.logger.Logger;

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
            case R.id.ly_type_btn1://自营茶叶
                Logger.i("自营茶叶");
                break;
            case R.id.ly_type_btn2://新鲜水果
                Logger.i("新鲜水果");
                break;
            case R.id.ly_type_btn3://有机蔬菜
                Logger.i("有机蔬菜");
                break;
            case R.id.ly_type_btn4://精选生鲜
                Logger.i("精选生鲜");
                break;
            case R.id.ly_type_btn5://更多类型
                Logger.i("更多类型");
                break;
        }
    }
}
