package com.lyl.frame.utils;

import com.lyl.frame.event.Event;

import org.greenrobot.eventbus.EventBus;

/**
 * @author lyl
 * @date 2018/1/17.
 */

public class EventBusUtils {

    public static void register(Object object) {
        EventBus.getDefault().register(object);
    }

    public static void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }
}
