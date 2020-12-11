package com.example.demo.utils;

import java.io.Serializable;

public class LayuiEditResult implements Serializable{
    private int code;
    private String msg;
    private PicData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PicData getData() {
        return data;
    }

    public void setData(PicData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LayuiEditResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
