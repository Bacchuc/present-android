package com.larry.present.network.base;

/**
 * Created by Larry-sea on 2016/11/22.
 *
 * retrofit 中返回结果的抽象
 *
 */

public class BaseCallModeal<T> {
    //状态码
    public int code;
    //状态码对应消息
    public String msg;
    //实际数据
    public T data;
   /* public String user_id;              //用户id
    public String session_id;           //session*/

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

  /*  public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }*/
}
