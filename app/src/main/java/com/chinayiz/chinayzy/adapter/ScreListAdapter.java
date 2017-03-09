package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.DealListModel;
import com.chinayiz.chinayzy.entity.response.ImGoldModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/8 15:28
 * Class ScreListAdapter  积分收益列表
 */
public class ScreListAdapter extends BaseAdapter {
    /**
     * 积分首页，积分分红记录
     */
    public static final int DATA_TYPE_GOLDS=1;
    /**
     * 消费记录
     */
    public static final int DATA_TYPE_DEALS=0;

    private List<ImGoldModel.DataBean.EarningslistBean> goldList;
    private List<DealListModel.DataBean> dealList;
    private ViewHolder mHolder;
    private Context mContext;
    private int type=-1;
    public ScreListAdapter(Context context,List<ImGoldModel.DataBean.EarningslistBean> earningslist) {
        type=DATA_TYPE_GOLDS;
        this.goldList = earningslist;
        this.mContext=context;
    }
    public ScreListAdapter(Context context,ArrayList<DealListModel.DataBean> datas) {
        type=DATA_TYPE_DEALS;
        this.dealList = datas;
        this.mContext=context;
    }
    @Override
    public int getCount() {
        return type==DATA_TYPE_GOLDS
                ? goldList.size(): dealList.size();
    }

    @Override
    public Object getItem(int position) {
        return type==DATA_TYPE_GOLDS
                ? goldList.get(position): dealList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            mHolder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.score_list_item,null);
            mHolder.titel= (TextView) convertView.findViewById(R.id.tv_score_title);
            mHolder.sum= (TextView) convertView.findViewById(R.id.tv_scoreNum);
            mHolder.time= (TextView) convertView.findViewById(R.id.tv_score_time);
            convertView.setTag(mHolder);
        }else {
            mHolder= (ViewHolder) convertView.getTag();
        }
        if (type==DATA_TYPE_GOLDS) {
            mHolder.titel.setText(goldList.get(position).getName());
            mHolder.time.setText(goldList.get(position).getOperatetime());
            mHolder.sum.setText(goldList.get(position).getPoint());
        }else {
            mHolder.time.setText(dealList.get(position).getOperatetime()+"");
            mHolder.sum.setText(dealList.get(position).getPoint());
            mHolder.titel.setText(dealList.get(position).getName());
        }
        return convertView;
    }
    private class ViewHolder{
        TextView titel,time,sum;
    }
}
