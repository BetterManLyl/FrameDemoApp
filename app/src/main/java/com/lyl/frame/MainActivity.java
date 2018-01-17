package com.lyl.frame;

import android.content.Intent;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lyl.frame.activity.SecondActivity;
import com.lyl.frame.base.activity.BaseActivity;
import com.lyl.frame.event.Event;
import com.lyl.frame.event.EventCode;

import org.greenrobot.eventbus.Subscribe;

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
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    public boolean isEventBusRegister() {
        return true;
    }

    @Subscribe
    public void onEvent(Event event) {
        if (event.getCode() == EventCode.codeA) {
            ToastUtils.showShort("111");
        }
    }
}
