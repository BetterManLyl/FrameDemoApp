package com.lyl.frame.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lyl.frame.R;
import com.lyl.frame.utils.EventBusUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author lyl
 * @date 2018/1/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initButterKnife();
        initView();
        initEventBus();
    }

    /**
     * 初始化ButterKnife
     */
    private void initButterKnife() {
        unbinder = ButterKnife.bind(this);
    }


    /**
     * 初始化EventBus
     */
    private void initEventBus() {
        if (isEventBusRegister()) {
            EventBusUtils.register(this);
        }
    }


    /**
     * 是否注册事件分发
     *
     * @return true 绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    public boolean isEventBusRegister() {
        return false;
    }

    /**
     * 定义通用的标题栏
     *
     * @param title 标题
     */
    public void setNorTitle(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_title);
        TextView titleText = (TextView) findViewById(R.id.tv_title);
        if (toolbar == null) {
            return;
        } else {
            setSupportActionBar(toolbar);
        }
        //设置toolbar左侧返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        titleText.setText(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化界面
     */
    public abstract void initView();

    /**
     * 获取布局
     *
     * @return 布局id
     */
    public abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (isEventBusRegister()) {
            EventBusUtils.unregister(this);
        }
    }
}
