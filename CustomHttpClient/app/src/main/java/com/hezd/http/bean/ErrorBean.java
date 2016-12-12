package com.hezd.http.bean;

/**
 * Created by hezd on 2016/12/9.
 */

public class ErrorBean {
    private String message;
    private int code;

    @Override
    public String toString() {
        return "ErrorBean{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorBean errorBean = (ErrorBean) o;

        if (code != errorBean.code) return false;
        return message != null ? message.equals(errorBean.message) : errorBean.message == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + code;
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
