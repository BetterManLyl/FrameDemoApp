package com.lyl.frame.base.application;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import okhttp3.OkHttpClient;

/**
 * @author lyl
 * @date 2018/1/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initUtils();
        initOkGo();
        initCrash();
        LeakCanary.install(this);
    }

    /**
     * 初始化崩溃日志
     */
    private void initCrash() {
        CustomActivityOnCrash.install(this.getApplicationContext());
    }

    /**
     * 初始化OkGo网络请求框架
     */
    private void initOkGo() {
        //okgo网络请求全局配置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        //header不支持中文，不允许有特殊字符
        //headers.put("commonHeaderKey1", "commonHeaderValue1");
        //headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        //param支持中文,直接传,不要自己编码
        //params.put("commonParamsKey1", "commonParamsValue1");
        //params.put("commonParamsKey2", "这里支持中文参数");
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);

    }

    /**
     * 初始化工具类
     */
    private void initUtils() {
        Utils.init(this);
        //设置log日志的开关
        LogUtils.getConfig().setLogSwitch(true);
    }
}
