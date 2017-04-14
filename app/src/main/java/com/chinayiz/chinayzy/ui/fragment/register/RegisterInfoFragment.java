package com.chinayiz.chinayzy.ui.fragment.register;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinayiz.chinayzy.R;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.base.BaseFragment;
import com.chinayiz.chinayzy.presenter.RegisterInfoPresenter;
import com.chinayiz.chinayzy.widget.ArrayAlertDialog;
import com.chinayiz.chinayzy.widget.PickView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/*  注册信息
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class RegisterInfoFragment extends BaseFragment<RegisterInfoPresenter> implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    public String code;
    public String phone;
    public String password;
    public EditText et_nickname;
    public EditText et_username;
    public EditText et_sex;
    public EditText et_birth;
    public EditText et_card;
    public EditText et_address;
    public EditText et_marriage;
    public EditText et_height;
    public EditText et_weight;
    public EditText et_education;
    public EditText et_policatical;
    public ImageView iv_card;
    public ImageView iv_card_back;
    public TextView tv_register_submit;
    public String recommendcard;
    private Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
   private  DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    public RegisterInfoFragment(String code, String phone, String password,String recommendcard) {
        this.code = code;
        this.phone = phone;
        this.password = password;
        this.recommendcard=recommendcard;
    }

    @Override
    public void onInitActionBar(BaseActivity activity) {
        activity.mTvActionBarTitle.setText("用户注册信息");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register_info,null);
        et_nickname = (EditText)view.findViewById(R.id.et_nickname);
        et_nickname.setOnClickListener(this);
        et_username = (EditText) view.findViewById(R.id.et_username);
        et_username.setOnClickListener(this);
        et_sex = (EditText) view.findViewById(R.id.et_sex);
        et_sex.setOnClickListener(this);
        et_birth = (EditText) view.findViewById(R.id.et_birth);
        et_birth.setOnClickListener(this);
        et_card = (EditText) view.findViewById(R.id.et_card);
        et_card.setOnClickListener(this);
        et_address = (EditText) view.findViewById(R.id.et_address);
        et_address.setOnClickListener(this);
        et_marriage = (EditText) view.findViewById(R.id.et_marriage);
        et_marriage.setOnClickListener(this);
        et_height = (EditText) view.findViewById(R.id.et_height);
        et_height.setOnClickListener(this);
        et_weight = (EditText) view.findViewById(R.id.et_weight);
        et_weight.setOnClickListener(this);
        et_education = (EditText) view.findViewById(R.id.et_education);
        et_education.setOnClickListener(this);
        et_policatical = (EditText) view.findViewById(R.id.et_policatical);
        et_policatical.setOnClickListener(this);
        iv_card = (ImageView) view.findViewById(R.id.iv_card);
        iv_card.setOnClickListener(this);
        iv_card_back = (ImageView) view.findViewById(R.id.iv_card_back);
        iv_card_back.setOnClickListener(this);
        tv_register_submit = (TextView) view.findViewById(R.id.tv_register_submit);
        tv_register_submit.setOnClickListener(this);
        return view;

    }

    @Override
    public RegisterInfoPresenter initPresenter() {
        return new RegisterInfoPresenter();
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register_submit:  //提交
                mPresenter.submit();
                break;
            case R.id.et_sex:  //性别
                final ArrayAlertDialog sexdialog=new ArrayAlertDialog(getActivity(), Gravity.BOTTOM,new String[]{"男","女"});
                sexdialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                et_sex.setText("男");
                                break;
                            case 1:
                                et_sex.setText("女");
                                break;
                        }
                        sexdialog.dismiss();
                    }
                });
                sexdialog.show();
                break;
            case R.id.et_marriage:   //婚姻状况
                final ArrayAlertDialog marriagedialog=new ArrayAlertDialog(getActivity(), Gravity.BOTTOM,new String[]{"未婚","已婚","离异"});
                marriagedialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                et_marriage.setText("已婚");
                                break;
                            case 1:
                                et_marriage.setText("已婚");
                                break;
                            case 2:
                                et_marriage.setText("离异");
                                break;
                        }
                        marriagedialog.dismiss();
                    }
                });
                marriagedialog.show();
                break;
            case R.id.et_education:   //学历
                final ArrayAlertDialog educationdialog=new ArrayAlertDialog(getActivity(), Gravity.BOTTOM,new String[]{"中专","大专","本科","研究生"});
                educationdialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                et_education.setText("中专");
                                break;
                            case 1:
                                et_education.setText("大专");
                                break;
                            case 2:
                                et_education.setText("本科");
                                break;
                            case 3:
                                et_education.setText("研究生");
                                break;
                        }
                        educationdialog.dismiss();
                    }
                });
                educationdialog.show();
                break;
            case R.id.et_policatical:   //政治面貌
                final ArrayAlertDialog policaticaldialog=new ArrayAlertDialog(getActivity(), Gravity.BOTTOM,new String[]{"团员","党员","群众"});
                policaticaldialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                et_policatical.setText("团员");
                                break;
                            case 1:
                                et_policatical.setText("党员");
                                break;
                            case 2:
                                et_policatical.setText("群众");
                                break;
                        }
                        policaticaldialog.dismiss();
                    }
                });
                policaticaldialog.show();
                break;
            case R.id.et_address:  //常驻地址
                PickView pickView=new PickView(getActivity());
                 pickView.show();
                break;
            case R.id.et_birth:
                DatePickerDialog dateDlg = new DatePickerDialog(getActivity(),
                        this,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));

                dateDlg.show();

                break;
            case R.id.iv_card:
                mPresenter.toCard();
                break;
            case R.id.iv_card_back:
                mPresenter.toCardBack();
                break;
        }
    }


    private void upDateDate() {
//        txtDate.setText(fmtDate.format(dateAndTime.getTime()));
        et_birth.setText(fmtDate.format(dateAndTime.getTime()));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        //修改日历控件的年，月，日
        //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, monthOfYear);
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //将页面TextView的显示更新为最新时间
        upDateDate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
