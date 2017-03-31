package com.chinayiz.chinayzy.ui.fragment.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.LabelPresenter;
import com.chinayiz.chinayzy.widget.Tag;
import com.chinayiz.chinayzy.widget.TagListView;
import com.chinayiz.chinayzy.widget.TagView;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18. 个性标签
 */
@SuppressLint("ValidFragment")
public class LabelFragment extends BaseFragment<LabelPresenter> implements View.OnClickListener {
    public List <Tag> param;
    public EditText et_label;
    public TextView tv_label_num;
    public TextView tv_add;
    public TagListView tlv_mystyle;
    public TagListView tlv_staple;

    private int editStart;//光标开始位置
    private int editEnd;//光标结束位置
    private CharSequence temp;//监听前的文本

    public LabelFragment(List <Tag> param) {
        this.param = param;
    }

    @Override
    protected void onVisible() {

    }


    @Override
    protected void onInvisible() {

    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("个性标签");
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("保存");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.i("完成");
                mPresenter.submit();
            }
        });

    }


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_label,null);
        et_label = (EditText)view.findViewById(R.id.et_label);
        tv_label_num = (TextView)view.findViewById(R.id.tv_label_num);
        tv_add = (TextView) view.findViewById(R.id.tv_add);
        tlv_mystyle = (TagListView)view.findViewById(R.id.tlv_mystyle);
        tlv_staple = (TagListView) view.findViewById(R.id.tlv_staple);
        tv_add.setOnClickListener(this);
        tlv_mystyle.setTagViewBackgroundRes(R.drawable.label_red);
        tlv_staple.setTagViewBackgroundRes(R.drawable.label_black);
        tlv_mystyle.setTagViewTextColorRes(Color.WHITE);
        tlv_staple.setTagViewTextColorRes(Color.BLACK);


        tlv_staple.setOnTagClickListener(new TagListView.OnTagClickListener() {
            @Override
            public void onTagClick(TagView tagView, Tag tag) {
                mPresenter.stapleTag(tag);
            }
        });

        tlv_mystyle.setOnTagClickListener(new TagListView.OnTagClickListener() {
            @Override
            public void onTagClick(TagView tagView, Tag tag) {
                mPresenter.mystyleTag(tag);
            }
        });

        et_label.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editStart = et_label.getSelectionStart();
                editEnd = et_label.getSelectionEnd();
                if (temp.length() > 6) {
//                    Toast.makeText(getActivity(), "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();
                    editable.delete(editStart - 1, editEnd);
                    int tempSelection = editStart;
                    et_label.setText(editable);
                    et_label.setSelection(tempSelection);
                }
                tv_label_num.setText(temp.length()+"/6");
            }
        });

        mPresenter.tags_mystle=param;
        tlv_mystyle.setTags(mPresenter.tags_mystle);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return view;

    }

    @Override
    public LabelPresenter initPresenter() {
        return new LabelPresenter();
    }

    @Override
    public void isNightMode(boolean isNight) {

    }



    @Override
    public void onClick(View view) {
           switch (view.getId()){
               case R.id.tv_add:
                   mPresenter.AddTag();
                   break;
           }
    }
}
