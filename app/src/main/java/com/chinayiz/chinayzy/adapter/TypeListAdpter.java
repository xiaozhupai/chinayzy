package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ClassifyTypesModel;
import com.orhanobut.logger.Logger;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/28 11:36
 * Class TypeListAdpter 生态农业首页二级分类code列表适配器
 */
public class TypeListAdpter extends BaseAdapter {
    private ClassifyTypesModel mModel;
    private Context mContext;
    private OnItemSeletdListener mItemSeletdListener;
    public boolean isFirstLoad=true;
    private int pos;

    public void setItemSeletdListener(OnItemSeletdListener itemSeletdListener) {
        mItemSeletdListener = itemSeletdListener;
    }

    public TypeListAdpter(Context context) {
        mContext = context;
    }

    public void setModel(ClassifyTypesModel model) {
        this.mModel = model;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mModel == null ? 0 : mModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return mModel == null ? null : mModel.getData().get(position);
    }
    public void changeSelected(int positon){ //刷新方法
        if(positon != pos){
            pos = positon;
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CheckBox mButton;
        convertView = View.inflate(mContext, R.layout.classify_listitem, null);
        mButton = (CheckBox) convertView.findViewById(R.id.btn_listItem);
        mButton.setText(mModel.getData().get(position).getTname());
        mButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (pos!=position){
                        mItemSeletdListener.onSeletItem(position,mModel.getData().get(position));
                    }
                    if (isFirstLoad){
                        mItemSeletdListener.onSeletItem(position,mModel.getData().get(position));
                        isFirstLoad=false;
                    }
                    buttonView.setBackgroundResource(R.mipmap.btn_classify_pre);
                    buttonView.setTextColor(Color.rgb(255,57,81));
                }else {
                    if (pos!=position){
                        buttonView.setBackgroundResource(R.mipmap.btn_classify);
                        buttonView.setTextColor(Color.rgb(40,43,41));
                    }
                }
            }
        });
        if (pos==position) {
            mButton.setChecked(true);
        }else {
            mButton.setChecked(false);
        }

        if (isFirstLoad){
            mButton.setChecked(true);
            isFirstLoad=false;
            Logger.i("第一次加载");
        }
        return convertView;
    }

    public interface OnItemSeletdListener {
        void onSeletItem(int position, ClassifyTypesModel.DataBean data);
    }
}
