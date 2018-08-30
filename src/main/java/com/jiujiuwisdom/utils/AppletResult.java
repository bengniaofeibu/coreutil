package com.jiujiuwisdom.utils;



import java.io.Serializable;

public class AppletResult<T> implements Serializable{

    private static final long serialVersionUID = 8360101072462967795L;

     /** 返回码 **/
     Integer code;

     /** msg **/
     String msg;

     /** 业务返回具体信息 **/
     T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

}
