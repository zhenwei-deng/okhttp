package com.example.hiot_cloud.test.networktest;

import java.io.Serializable;

/**
 * 父级的类，用作转换
 */
public class ResultBase<T> implements Serializable {
    //data是一个换型字段
    /**
     * 返回对象
     */
    T data;

    /**
     * 返回描述
     */
   String msg;

    /**
     * 请求响应状态
     */
    int Status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
