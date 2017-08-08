package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ShowClassifyCodeModel;
import com.orhanobut.logger.Logger;


import java.util.List;

/**
 * 红包专场商品分类适配器
 * Created by Administrator on 2017/7/31. NewClassifyFragment
 */

public class RedpacketListViewAdapter extends BaseAdapter {

    Context context;
    List<ShowClassifyCodeModel.DataBean> list;
    private int position1;
    public boolean isFirstLoad = true;
    public OnItemSeletdListener mItemSeletdListener;

    public RedpacketListViewAdapter(Context context, List<ShowClassifyCodeModel.DataBean> list) {
        this.context = context;
        this.list = list;
        Logger.i("list:" + list);
    }

    public List<ShowClassifyCodeModel.DataBean> getList() {
        return list;
    }


    public void setList(List<ShowClassifyCodeModel.DataBean> list) {
        this.list = list;
        Logger.i("list:" + list);
        notifyDataSetChanged();
    }

    public void changeSelected(int positon) { //刷新方法
        if (positon != position1) {
            position1 = positon;
            notifyDataSetChanged();
        }
    }

    public void setItemSeletdListener(OnItemSeletdListener itemSeletdListener) {
        mItemSeletdListener = itemSeletdListener;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.classify_listitem, null);
        CheckBox mButton;
        mButton = (CheckBox) convertView.findViewById(R.id.btn_listItem);
        ShowClassifyCodeModel.DataBean bean = list.get(position);
        mButton.setText(bean.getItemname());
        Logger.i("bean.getItemname():" + bean.getItemname());
        mButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (position1 != position) {
                        mItemSeletdListener.onSeletItem(position, list.get(position));

                    }
                    if (isFirstLoad) {
                        mItemSeletdListener.onSeletItem(position, list.get(position));
                        isFirstLoad = false;
                    }
                    buttonView.setBackgroundResource(R.mipmap.btn_classify_pre);
                    buttonView.setTextColor(Color.rgb(255, 57, 81));
                } else {
                    if (position1 != position) {
                        buttonView.setBackgroundResource(R.mipmap.btn_classify);
                        buttonView.setTextColor(Color.rgb(40, 43, 41));
                    }
                }
            }
        });
        if (position1 == position) {
            mButton.setChecked(true);
        } else {
            mButton.setChecked(false);
        }
        if (isFirstLoad) {
            mButton.setChecked(true);
            isFirstLoad = false;
            Logger.i("第一次加载");
        }

        return convertView;
    }

    public interface OnItemSeletdListener {
        void onSeletItem(int position, ShowClassifyCodeModel.DataBean data);
    }
}
