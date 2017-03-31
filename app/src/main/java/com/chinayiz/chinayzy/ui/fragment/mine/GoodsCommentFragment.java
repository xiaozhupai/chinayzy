package com.chinayiz.chinayzy.ui.fragment.mine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.entity.request.CommentGoodsModel;
import com.chinayiz.chinayzy.presenter.GoodsCommentPresenter;
import com.chinayiz.chinayzy.views.GlideLoader;
import com.hedgehog.ratingbar.RatingBar;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageSelector;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/20 10:17
 * Class GoodsCommentFragment 商品评论
 */
@SuppressLint("ValidFragment")
public class GoodsCommentFragment extends BaseFragment<GoodsCommentPresenter>
        implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, RatingBar.OnRatingChangeListener {
    public static final int REQUEST_CODE = 0x0001;
    public CommentGoodsModel mRequestModel;
    private ImageConfig imageConfig;
    public ArrayList<String> path = new ArrayList<>();
    public static final String COMMENT_DATA="CommentGoodsActivity_DATA";
    public ViewHolder mHolder;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comment, container,false);
        mHolder=new ViewHolder();
        initHolder(view);
        return view;
    }

    private void initHolder(View view) {
        mHolder.ll_images= (LinearLayout) view.findViewById(R.id.ll_images);
        mHolder.ll_images.setOnClickListener(this);
        mHolder.iv_goodsPic = (ImageView) view.findViewById(R.id.iv_goodsPic);
        mHolder.tv_goodsPrice = (TextView) view.findViewById(R.id.tv_goodsPrice);
        mHolder.tv_goodsName = (TextView) view.findViewById(R.id.tv_goodsName);
        mHolder.tv_goodsCount = (TextView) view.findViewById(R.id.tv_goodsCount);
        mHolder.tv_goodsGroup = (TextView) view.findViewById(R.id.tv_goodsGroup);
        mHolder.edit_comment = (EditText) view.findViewById(R.id.edit_comment);

        mHolder.rb_describe = (RatingBar) view.findViewById(R.id.rb_describe);
        mHolder.rb_describe.setOnRatingChangeListener(this);
        mHolder.cb_isCryptonym = (CheckBox) view.findViewById(R.id.cb_isCryptonym);
        mHolder.cb_isCryptonym.setOnCheckedChangeListener(this);
        mHolder.btn_sendComment = (Button) view.findViewById(R.id.btn_sendComment);
        mHolder.btn_sendComment.setOnClickListener(this);
    }

    private void setData() {
        if (mRequestModel==null){
            return;
        }
        Glide.with(this)
                .load(mRequestModel.getgPic())
                .into(mHolder.iv_goodsPic);
        mHolder.tv_goodsPrice.setText(mRequestModel.getPice());
        mHolder.tv_goodsName.setText(mRequestModel.getGoodsName());
        mHolder.tv_goodsCount.setText(mRequestModel.getGoodsCount());
        mHolder.tv_goodsGroup.setText(mRequestModel.getsName());
    }
    public void submit(){
        if (mHolder.edit_comment==null) {
            Logger.i("文本框等于空");
        }else {
            if (!TextUtils.isEmpty(mHolder.edit_comment.getText().toString())){
                String content=mHolder.edit_comment.getText().toString();
                mRequestModel.setCommentscontent(content);
            }
        }
        mPresenter.mRequestUtils.commentGoods(mRequestModel);
    }

    @Override
    public void onRatingChange(float RatingCount) {
        mRequestModel.setDescpoint(String.valueOf(RatingCount));
        Logger.i("当前评分="+RatingCount);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mRequestModel.setIsanonymity("0");
        }else {
            mRequestModel.setIsanonymity("1");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendComment:
                submit();
                break;
            case R.id.ll_images:
                Logger.i("选择图片");
                mHolder.ll_images.removeAllViews();
                imageConfig = new ImageConfig.Builder(
                        new GlideLoader())
                        .steepToolBarColor(getResources().getColor(R.color.titleBlue))
                        .titleBgColor(getResources().getColor(R.color.titleBlue))
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）
                        .mutiSelect()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(4)
                        // 设置图片显示容器，参数：（1、显示容器，2、每行显示数量（建议不要超过8个），是否可删除）
                        .setContainer(mHolder.ll_images, 4, true)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/temp")
                        // 开启拍照功能 （默认关闭）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(getActivity(), imageConfig);
                break;
        }
    }

    @Override
    public void onInintData(Bundle bundle) {
        Logger.i("初始化数据");
        mRequestModel=bundle.getParcelable(COMMENT_DATA);
        mRequestModel.setIsanonymity("0");
        mRequestModel.setCommentscontent("默认好评");
        mRequestModel.setDescpoint("5");
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
       activity.mTvActionBarTitle.setText("评价商品");
    }

    public  class ViewHolder {
        public LinearLayout ll_images;
        public ImageView iv_goodsPic;
        public TextView tv_goodsPrice;
        public TextView tv_goodsName;
        public TextView tv_goodsCount;
        public TextView tv_goodsGroup;
        public EditText edit_comment;
        public RatingBar rb_describe;
        public CheckBox cb_isCryptonym;
        public Button btn_sendComment;
    }

    @Override
    public GoodsCommentPresenter initPresenter() {
        return new GoodsCommentPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void isNightMode(boolean isNight) {

    }
}
