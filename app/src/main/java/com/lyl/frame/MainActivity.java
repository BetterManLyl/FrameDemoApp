package com.lyl.frame;

import android.content.Intent;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lyl.frame.activity.SecondActivity;
import com.lyl.frame.base.activity.BaseActivity;
import com.lyl.frame.bean.Login;
import com.lyl.frame.event.Event;
import com.lyl.frame.event.EventCode;
import com.lyl.frame.netutils.ProgressCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

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
        initToolBar("你好", true);
        LogUtils.eTag(TAG, "你好");
        OkGo.<Login>get("http://36.7.144.130:6020/api/phone/logins")
                .params("loginName", "222222")
                .params("password", "888888")
                .execute(new ProgressCallBack<Login>(this, Login.class) {
                    @Override
                    public void onSuccess(Response<Login> response) {
                        super.onSuccess(response);
                        LogUtils.eTag("lyl", response.body().getMessage());
                    }

                    @Override
                    public void onError(Response<Login> response) {
                        super.onError(response);
                    }
                });
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
