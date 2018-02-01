package com.lyl.frame.event;

/**
 * @author lyl
 * @date 2018/1/17.
 */

public class Event<T> {

    private T t;

    private int code;

    public Event(int code) {
        this.code = code;
    }

    public Event(int code, T t) {
        this.t = t;
        this.code = code;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
