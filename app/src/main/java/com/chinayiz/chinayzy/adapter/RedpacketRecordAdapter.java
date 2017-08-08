package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.entity.response.ExpenseCalendarModel;

import java.util.List;

/**
 * 消费记录adapter
 * Created by Administrator on 2017/7/31.
 */

public class RedpacketRecordAdapter extends BaseInectAdaphter {

    public RedpacketRecordAdapter(Context context, List<ExpenseCalendarModel.DataBean.ListBean> list) {
        this.context = context;
        this.lists = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item_redpacket_pay_record,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ExpenseCalendarModel.DataBean.ListBean model= (ExpenseCalendarModel.DataBean.ListBean) lists.get(position);
        viewHolder.tv_time.setText(model.getCreatetime());
        viewHolder.tv_count.setText("-"+model.getPoint());
        viewHolder.tv_pay.setText( model.getMessageRecode()+"");
        return convertView;
    }

    public static class ViewHolder{
        public View rootView;
        public TextView tv_pay,tv_time,tv_count;

        public ViewHolder(View rootView){
            this.rootView=rootView;
            this.tv_count= (TextView) rootView.findViewById(R.id.tv_count);
            this.tv_pay= (TextView) rootView.findViewById(R.id.tv_pay);
            this.tv_time= (TextView) rootView.findViewById(R.id.tv_time);
        }
    }

}
