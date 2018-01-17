package com.lyl.frame.netutils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * @author lyl
 * @date 2018/1/17.
 * 自定义的CallBack实现
 */

public class ProgressCallBack<T> implements Callback<T> {

    private ProgressDialog progressDialog = null;

    public ProgressCallBack(Context context) {
        super();
        initDialog(context);
    }

    private void initDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("请求网络中...");
    }

    private void dialogDiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onSuccess(Response<T> response) {
        dialogDiss();
    }

    @Override
    public void onCacheSuccess(Response<T> response) {

    }

    @Override
    public void onError(Response<T> response) {
        dialogDiss();
    }

    @Override
    public void onFinish() {
        dialogDiss();
    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }
}
