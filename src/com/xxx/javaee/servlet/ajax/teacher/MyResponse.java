package com.xxx.javaee.servlet.ajax.teacher;

public class MyResponse {

    private boolean success = false;
    private Object data;
    private String msg;

    public MyResponse() {
    }

    public MyResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public MyResponse(boolean success, Object data, String msg) {
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

    public void setData(Object data) {
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
