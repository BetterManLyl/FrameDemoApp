package com.lyl.frame.bean;

import java.io.Serializable;

/**
 * @author lyl
 * @date 2018/1/17.
 */

public class Login implements Serializable {

    private boolean result;
    private String message;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
