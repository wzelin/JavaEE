package com.xxx.javaee.servlet.test;

import com.xxx.javaee.bean.Info;

import java.util.List;

public class MyResponse {

    private boolean success = false;
    private List<Info> data;
    private String msg;

     public MyResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public MyResponse(boolean success, List<Info> data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(List<Info> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "success=" + success +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
