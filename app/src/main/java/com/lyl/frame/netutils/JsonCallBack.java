package com.lyl.frame.netutils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author lyl
 * @date 2018/1/17.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    public JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        request.headers("", "")
                .params("", "");
    }

    @Override
    public void onSuccess(Response<T> response) {

    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);

    }

    /**
     * 解析请求返回的结果
     * @param response
     * @return
     * @throws Throwable
     */
    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody responseBody=response.body();
        if (responseBody==null){
            return null;
        }
        T data=null;
        Gson gson=new Gson();
        JsonReader jsonReader=new JsonReader(responseBody.charStream());
        if (type!=null){
            data=gson.fromJson(jsonReader,type);
        }
        if (clazz!=null){
            data=gson.fromJson(jsonReader,clazz);
        }
        return data;
    }
}
