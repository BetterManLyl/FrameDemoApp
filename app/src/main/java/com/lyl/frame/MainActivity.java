package com.lyl.frame;

import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lyl.frame.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2018/01/16.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tv.setText("你好。。。");
        setNorTitle("你好");
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        ToastUtils.showShort("点击了");
    }
}
