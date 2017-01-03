package com.chinayiz.chinayzy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chinayiz.chinayzy.autoUpdate.APKUpdadeUtils;
import com.chinayiz.chinayzy.base.BaseActivity;
import com.chinayiz.chinayzy.presenter.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener {
    private Button mDsfgdg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mDsfgdg = (Button) findViewById(R.id.dsfgdg);
        mDsfgdg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dsfgdg:
//                //测试数据
//                Version vs =new Version();
//                vs.setDownloadUri("www.baidu.com");
//                vs.setIsNewVersion(1);
//                vs.setnewVersionNumber(2);
//                vs.setUpdateTitle("版本更新");
//                vs.setUpdateMessge("16/09/24 完善AppUtils\n" +
//                        "16/09/23 整理工具类，新增ActivityUtils、BarUtils、IntentUtils\n" +
//                        "16/09/22 完善LogUtils中\n" +
//                        "16/09/21 新增LogUtils\n" +
//                        "16/09/20 对昨天的进行单元测试\n" +
//                        "16/09/19 新增CameraUtils，新增获取中文首字母\n" +
//                        "16/09/18 修复少许代码，发布1.2.1");
//                String str= new Gson().toJson(vs);
//                Logger.i(str);
                APKUpdadeUtils.inspectVersion(this);

                break;
        }
    }
}
