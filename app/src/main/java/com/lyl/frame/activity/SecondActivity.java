package com.lyl.frame.activity;

import android.view.View;

import com.lyl.frame.R;
import com.lyl.frame.base.activity.BaseActivity;
import com.lyl.frame.event.Event;
import com.lyl.frame.event.EventCode;
import com.lyl.frame.utils.EventBusUtils;

import butterknife.OnClick;

/**
 * @author lyl
 * @date 2018/1/17.
 */

public class SecondActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.toolbar_title, R.id.tv_title, R.id.tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_title:
                break;
            case R.id.tv_title:
                break;
            case R.id.tv:
                EventBusUtils.sendEvent(new Event(EventCode.codeA));
                break;
            default:
                break;
        }
    }
}
