package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.StoreInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/2/7 15:40
 * Class GoodsTypeMeunAdapter 商品类型列表适配器
 */
public class GoodsTypeMeunAdapter extends BaseAdapter {
    private List<StoreInfoModel.DataBean.TypecodeBean> mTypecodeList = new ArrayList<>();
    private Context mContext;
    public GoodsTypeMeunAdapter(Context context) {
        mContext = context;
    }

    public void setTypecodeList(List<StoreInfoModel.DataBean.TypecodeBean> typecodeList) {
        mTypecodeList = typecodeList;
    }
    public List<StoreInfoModel.DataBean.TypecodeBean> getTypecodeList() {
        return mTypecodeList;
    }
    @Override
    public int getCount() {
        return mTypecodeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTypecodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodre hodre=null;

        if (convertView == null) {
            convertView=View.inflate(mContext, R.layout.goods_type_item, null);
            hodre=new ViewHodre();
            hodre.mGoodsName= (TextView) convertView.findViewById(R.id.tv_GoodsTypeName);
            convertView.setTag(hodre);
        } else {
            hodre = (ViewHodre) convertView.getTag();
        }
        hodre.mGoodsName.setText(mTypecodeList.get(position).getTypename());
        return convertView;
    }
    private  class ViewHodre{
        TextView mGoodsName;
    }
}
