package com.chinayiz.chinayzy.ui.fragment.mine;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.UserNamePresenter;
import com.chinayiz.chinayzy.utils.JavaUntil;
import com.chinayiz.chinayzy.views.PickView.TimePickerView;
import com.chinayiz.chinayzy.widget.ArrayAlertDialog;
import com.chinayiz.chinayzy.widget.PickView;
import com.orhanobut.logger.Logger;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**    用户
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class UserFragment extends BaseFragment<UserNamePresenter> implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    public String param;
    public EditText et_username;
    public  int index;
    public TextView tv_sex_man;
    public ImageView iv_sex_man;
    public RelativeLayout rl_sex_man;
    public TextView tv_sex_woman;
    public ImageView iv_sex_woman;
    public RelativeLayout rl_sex_woman;
    public EditText et_birthday,et_usualplace,et_marriage,et_height,et_weight,et_education,et_policatical;
    public String title;
    private Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    private DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    public String id;
    public static final String HIGJT="0";
    public static final String JUNIOR="1";
    public static final String UNDERGRADUATE="2";
    public static final String GRADUATE="3";
    public static final String NO_MARRIAGE="0";
    public static final String MARRIAGE="1";
    public static final String DIVORCED="2";
    public static final String MEMBER="0";
    public static final String PARTY_MEMBER="1";
    public static final String MASSES="2";

    /**
     *
     * @param index 0用户名 1性别 2出生日期 3常住地 4 婚姻状况 5 身高 6 体重 7学历 8 政治面貌
     * @param param
     */
    public UserFragment(int index,String param,String title) {
        this.param = param;
        this.index=index;
        this.title=title;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText(title);
        activity.mCbActionBarEdit.setVisibility(View.VISIBLE);
        activity.mCbActionBarEdit.setText("完成");
        activity.mCbActionBarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mPresenter.submit();
            }
        });
        Logger.i("UserFragment-----onInitActionBar  ");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        switch (index){
            case 0:
              view=inflater.inflate(R.layout.fragment_user_name2,null);
                et_username = (EditText)view.findViewById(R.id.et_username);
                et_username.setText(param);
                break;
            case 1:
                view =inflater.inflate(R.layout.fragment_sex, null);
                tv_sex_man = (TextView) view.findViewById(R.id.tv_sex_man);
                iv_sex_man = (ImageView) view.findViewById(R.id.iv_sex_man);
                rl_sex_man = (RelativeLayout) view.findViewById(R.id.rl_sex_man);
                tv_sex_woman = (TextView) view.findViewById(R.id.tv_sex_woman);
                iv_sex_woman = (ImageView) view.findViewById(R.id.iv_sex_woman);
                rl_sex_woman = (RelativeLayout)view.findViewById(R.id.rl_sex_woman);
                rl_sex_woman.setOnClickListener(this);
                rl_sex_man.setOnClickListener(this);
                if (TextUtils.isEmpty(param)){
                    iv_sex_man.setVisibility(View.GONE);
                    iv_sex_woman.setVisibility(View.GONE);
                }else {
                    if (param.equals("0")){ //男
                        iv_sex_man.setVisibility(View.VISIBLE);
                        iv_sex_woman.setVisibility(View.GONE);
                    }else {
                        iv_sex_man.setVisibility(View.GONE);
                        iv_sex_woman.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case 2:
                view =inflater.inflate(R.layout.fragment_email, null);
                et_birthday = (EditText)view.findViewById(R.id.et_email);
                et_birthday.setText(param);
             et_birthday.setFocusable(false);
                et_birthday.setOnClickListener(this);
                break;
            case 3:
                view =inflater.inflate(R.layout.fragment_email, null);
                et_usualplace= (EditText) view.findViewById(R.id.et_email);
                et_usualplace.setText(param);
                et_usualplace.setFocusable(false);
                et_usualplace.setOnClickListener(this);
                break;
            case 4:
                view =inflater.inflate(R.layout.fragment_email, null);
                et_marriage= (EditText) view.findViewById(R.id.et_email);
                    if (!TextUtils.isEmpty(param)){
                        switch (param){
                            case NO_MARRIAGE:
                                et_marriage.setText("未婚");
                                break;
                            case MARRIAGE:
                                et_marriage.setText("已婚");
                                break;
                            case DIVORCED:
                                et_marriage.setText("离异");
                                break;
                    }


                }
                et_marriage.setFocusable(false);
                et_marriage.setOnClickListener(this);
                break;
            case 5:
                view=inflater.inflate(R.layout.fragment_height,null);
                et_height = (EditText) view.findViewById(R.id.et_height);
                et_height.setText(param);
                break;
            case 6:
                view=inflater.inflate(R.layout.fragment_weight,null);
                et_weight = (EditText) view.findViewById(R.id.et_weight);
                et_weight.setText(param);
                break;
            case 7:
                view =inflater.inflate(R.layout.fragment_email, null);
                et_education= (EditText) view.findViewById(R.id.et_email);
                if (!TextUtils.isEmpty(param)){
                    switch (param){
                        case HIGJT:
                            et_education.setText("中专");
                            break;
                        case JUNIOR:
                            et_education.setText("大专");
                            break;
                        case UNDERGRADUATE:
                            et_education.setText("本科");
                            break;
                        case GRADUATE:
                            et_education.setText("研究生");
                            break;
                    }
                }

                et_education.setFocusable(false);
                et_education.setOnClickListener(this);
                break;
            case 8:
                view =inflater.inflate(R.layout.fragment_email, null);
                et_policatical= (EditText) view.findViewById(R.id.et_email);
                if (!TextUtils.isEmpty(param)){
                    switch (param){
                        case MEMBER:
                            et_policatical.setText("团员");
                            break;
                        case PARTY_MEMBER:
                            et_policatical.setText("党员");
                            break;
                        case MASSES:
                            et_policatical.setText("群众");
                            break;
                    }
                }

                et_policatical.setFocusable(false);
                et_policatical.setOnClickListener(this);
                break;
        }

        Logger.i("UserFragment---------initView");
        return view;
    }

    @Override
    public UserNamePresenter initPresenter() {
        Logger.i("UserFragment---------initPresenter");
        return new UserNamePresenter();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_sex_man:
                iv_sex_man.setVisibility(View.VISIBLE);
                iv_sex_woman.setVisibility(View.GONE);
                break;
            case R.id.rl_sex_woman:
                iv_sex_man.setVisibility(View.GONE);
                iv_sex_woman.setVisibility(View.VISIBLE);
                break;
            case R.id.et_email:
                switch (index){  //出生日期
                    case 2:
                        TimePickerView pvTime=new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
                     Calendar calendar=   Calendar.getInstance();
                        pvTime.setRange(1940,calendar.get(Calendar.YEAR));
                        pvTime.setTime(new Date());
                        pvTime.setCyclic(false);
                        pvTime.setCancelable(true);
                        // 时间选择后回调
                        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener()
                        {

                            @Override
                            public void onTimeSelect(Date date)
                            {
                                et_birthday.setText(JavaUntil.getTime(date));
                            }
                        });
                     pvTime.show();
                        break;
                    case 3:
                        PickView pickView=new PickView(getActivity());
                        pickView.show();
                        break;
                    case 4:
                        final ArrayAlertDialog marriagedialog=new ArrayAlertDialog(getActivity(), Gravity.BOTTOM,new String[]{"未婚","已婚","离异"});
                        marriagedialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                switch (i){
                                    case 0:
                                        et_marriage.setText("未婚");
                                        id="0";
                                        break;
                                    case 1:
                                        et_marriage.setText("已婚");
                                        id="1";
                                        break;
                                    case 2:
                                        et_marriage.setText("离异");
                                        id="2";
                                        break;
                                }
                                marriagedialog.dismiss();
                            }
                        });
                        marriagedialog.show();
                        break;
                    case 7:
                        final ArrayAlertDialog educationdialog=new ArrayAlertDialog(getActivity(), Gravity.BOTTOM,new String[]{"中专","大专","本科","研究生"});
                        educationdialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                switch (i){
                                    case 0:
                                        et_education.setText("中专");
                                        id="0";
                                        break;
                                    case 1:
                                        et_education.setText("大专");
                                        id="1";
                                        break;
                                    case 2:
                                        et_education.setText("本科");
                                        id="2";
                                        break;
                                    case 3:
                                        et_education.setText("研究生");
                                        id="3";
                                        break;
                                }
                                educationdialog.dismiss();
                            }
                        });
                        educationdialog.show();
                        break;
                    case 8:
                        final ArrayAlertDialog policaticaldialog=new ArrayAlertDialog(getActivity(), Gravity.BOTTOM,new String[]{"团员","党员","群众"});
                        policaticaldialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                switch (i){
                                    case 0:
                                        et_policatical.setText("团员");
                                        id="0";
                                        break;
                                    case 1:
                                        et_policatical.setText("党员");
                                        id="1";
                                        break;
                                    case 2:
                                        et_policatical.setText("群众");
                                        id="2";
                                        break;
                                }
                                policaticaldialog.dismiss();
                            }
                        });
                        policaticaldialog.show();
                        break;
                }
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //修改日历控件的年，月，日
        //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, month);
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //将页面TextView的显示更新为最新时间
        upDateDate();
    }

    private void upDateDate() {
        et_birthday.setText(fmtDate.format(dateAndTime.getTime()));
    }
}
