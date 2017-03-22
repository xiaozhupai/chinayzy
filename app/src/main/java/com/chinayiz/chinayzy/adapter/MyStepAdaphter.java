package com.chinayiz.chinayzy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.entity.model.BaseResponseModel;
import com.chinayiz.chinayzy.entity.model.EventMessage;
import com.chinayiz.chinayzy.entity.response.MyStepModel;
import com.chinayiz.chinayzy.net.Commons;
import com.chinayiz.chinayzy.net.User.UserNet;
import com.chinayiz.chinayzy.net.callback.EventBusCallback;
import com.chinayiz.chinayzy.views.pullable.PullToRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 我的足迹Adaphter
 * Created by Administrator on 2017/1/12.
 */

public class MyStepAdaphter extends BaseInectAdaphter<MyStepModel.DataBean> implements SectionIndexer,EventBusCallback {
    private static final int TYPE_CATEGORY_ITEM = 0;
    private static final int TYPE_ITEM = 1;
    private TextView tv_title;
    public static final String DELETE="DELETE";
    private int deletePosition;
    private MyStepModel.DataBean.FootmarklistBean deleteBean;

    public MyStepAdaphter(Context context, List<MyStepModel.DataBean> list) {
        this.lists = list;
        this.context = context;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void runUiThread(EventMessage message) {
        if (message.getEventType()== EventMessage.NET_EVENT){
            disposeNetMsg(message);
        }

    }

    @Override
    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    public void runBgThread(EventMessage message) {
        if (message.getEventType()== EventMessage.INFORM_EVENT){
            disposeInfoMsg(message);
        }


    }

    @Override
    public void disposeNetMsg(EventMessage message) {
        switch (message.getDataType()){
            case Commons.SHOWFOOTMARKS:
                MyStepModel model= (MyStepModel) message.getData();
                onResult(model.getData());
                break;
            case Commons.DELETEFOOTMARK:
            BaseResponseModel model1= (BaseResponseModel) message.getData();
                if (model1.getCode().equals("100")){
                    delete();
                }
                break;

        }
    }

    @Override
    public void disposeInfoMsg(EventMessage message) {
        if (message.getDataType()== MyStepAdaphter.DELETE){
            MyStepModel.DataBean.FootmarklistBean bean= (MyStepModel.DataBean.FootmarklistBean) message.getData();

        }
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != lists) {
            //  所有分类中item的总和是ListVIew  Item的总个数
            for (MyStepModel.DataBean groups : lists) {
                count += groups.getFootmarklist().size() + 1;
            }
        }
        return count;
    }

    @Override
    public MyStepModel.DataBean.FootmarklistBean getItem(int position) {

        // 异常情况处理
        if (null == lists || position < 0 || position > getCount()) {
            return null;
        }

        // 同一分类内，第一个元素的索引值
        int categroyFirstIndex = 0;

        for (MyStepModel.DataBean category : lists) {
            int size = category.getFootmarklist().size() + 1;
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            // item在当前分类内
            if (categoryIndex < size) {

                return category.getItem(categoryIndex);
            }

            // 索引移动到当前分类结尾，即下一个分类第一个元素索引
            categroyFirstIndex += size;
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // 异常情况处理
        if (null == lists || position < 0 || position > getCount()) {
            return TYPE_ITEM;
        }
        int categroyFirstIndex = 0;
        for (MyStepModel.DataBean category : lists) {
            int size = category.getFootmarklist().size() + 1;
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            if (categoryIndex == 0) {
                return TYPE_CATEGORY_ITEM;
            }
            categroyFirstIndex += size;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != TYPE_CATEGORY_ITEM;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    //通过组来获得位置
    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    //通过位置获得组
    @Override
    public int getSectionForPosition(int position) {
        int group = 0;
        int FirstPostion = 0;
        int LastPosition = 0;
        for (MyStepModel.DataBean groups : lists) {
            int size = groups.getFootmarklist().size() + 1;
            FirstPostion += LastPosition;
            LastPosition += size;
            if (position < LastPosition) {
                if (position > FirstPostion || position == FirstPostion) {
                    return group;
                }
            }
            group++;
        }
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MyStepModel.DataBean.FootmarklistBean bean=getItem(i);
        switch (getItemViewType(i)) {
            case TYPE_CATEGORY_ITEM:
                ViewHeadHolder viewHeadHolder = null;
                if (view == null) {
                    view = View.inflate(context, R.layout.mystep_list_head, null);
                    viewHeadHolder = new ViewHeadHolder(view);
                    view.setTag(viewHeadHolder);
                } else {
                    viewHeadHolder = (ViewHeadHolder) view.getTag();
                }
                viewHeadHolder.tv_title.setText(bean.getLt());
                break;
            case TYPE_ITEM:
                ViewHolder viewHolder = null;
                if (view == null) {
                    view = View.inflate(context, R.layout.mystep_list_item, null);
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                Glide.with(context).load(bean.getPic()).into(viewHolder.iv_mystep_image);
                viewHolder.tv_mystep_title.setText(bean.getGname());
                viewHolder.tv_mystep_price.setText(bean.getPrice());
                viewHolder.iv_mystep_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UserNet.getNet().getdeletefootmark(bean.getGoodsid()+"");
//                       listener.delete(i,bean);
                        deletePosition=i;
                        deleteBean=bean;
                    }
                });
                break;
        }
        return view;
    }

    public void delete(){
        int section=getSectionForPosition(deletePosition);
        lists.get(section).getFootmarklist().remove(deleteBean);
        setData(lists);
    }



    @Override
    public void AddData(List<MyStepModel.DataBean> lists) {
        MyStepModel.DataBean newgroup=lists.get(0);
        MyStepModel.DataBean.FootmarklistBean bean=newgroup.getFootmarklist().get(0);
        MyStepModel.DataBean group=this.lists.get(this.lists.size()-1);
        MyStepModel.DataBean.FootmarklistBean newbean=group.getFootmarklist().get(0);
        if (newbean.getLt().equals(bean.getLt())){  //如果上拉加载的数据是上一页的组的数组
            group.getFootmarklist().addAll(newgroup.getFootmarklist());
            lists.remove(newgroup);
        }
        this.lists.addAll(lists);
        notifyDataSetChanged();


    }

    @Override
    public void onResult(List<MyStepModel.DataBean> lists) {
        if (page>1){  //上拉加载
            AddData(lists);
        }else {  //下拉刷新
            setData(lists);
        }
        int count = 0;
        //  所有分类中item的总和是ListVIew  Item的总个数
        for (MyStepModel.DataBean groups : lists) {
            count += groups.getFootmarklist().size();
        }

        if (count<10){
            pullrefresh.loadmoreView.setVisibility(View.GONE);
            pullrefresh.setLoadMoreVisiable(false);
        }else {
            pullrefresh.loadmoreView.setVisibility(View.VISIBLE);
            pullrefresh.setLoadMoreVisiable(true);
        }
        pullrefresh.refreshFinish(PullToRefreshLayout.SUCCEED);
        pullrefresh.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onGetData(int pageindex) {
        UserNet.getNet().getshowFootMarks(pageindex + "", "10");
    }

    public static class ViewHeadHolder{
        public View rootView;
        public TextView tv_title;
        public ViewHeadHolder(View rootView){
            this.rootView=rootView;
            this.tv_title= (TextView) rootView.findViewById(R.id.tv_title);
        }
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_mystep_image;
        public TextView tv_mystep_title;
        public TextView tv_mystep_price;
        public ImageView iv_mystep_delete;


        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_mystep_image = (ImageView) rootView.findViewById(R.id.iv_mystep_image);
            this.tv_mystep_title = (TextView) rootView.findViewById(R.id.tv_mystep_title);
            this.tv_mystep_price = (TextView) rootView.findViewById(R.id.tv_mystep_price);
            this.iv_mystep_delete = (ImageView) rootView.findViewById(R.id.iv_mystep_delete);

        }

    }


}
