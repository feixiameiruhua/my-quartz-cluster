package com.fwmagic.quartz.entity;

import java.io.Serializable;

/**
 * 统一返回对象
 * @param <T>
 */
public class ResponseResult<T> implements Serializable{

    /**
     * 结果信息编号，对应字典
     */
    private int code;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 返回值，类型为T
     */
    private T data;

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult() {
    }


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}