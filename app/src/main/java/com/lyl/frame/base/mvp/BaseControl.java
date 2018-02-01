package com.lyl.frame.base.mvp;

import android.content.Context;

/**
 * @author lyl
 * @date 2018/1/31.
 */

public interface BaseControl {
    interface BaseView {
        /**
         * toast
         *
         * @param message
         */
        void showToast(String message);

        /**
         * 显示dialog
         *
         * @param message
         */
        void showProgress(String message);

        /**
         * 隐藏dialog
         */
        void hideProgerss();

        /**
         * 返回context
         *
         * @return
         */
        Context getContext();

    }

    interface IBaseModel<T> {

        /**
         * @param requestListener
         * @param
         */
        void requestServer(RequestListener requestListener);

        /**
         *
         */
        void cancelRequest();


        /**
         * @param <T>
         */
        interface RequestListener<T> {
            void success(T t);

            void error();
        }
    }

    interface IBasePresenter {


        void requestServer();

        Context getContext();
    }
}
