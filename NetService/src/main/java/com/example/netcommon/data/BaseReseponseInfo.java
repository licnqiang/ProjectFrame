package com.example.netcommon.data;

import java.io.Serializable;

/**
 * @author lq
 * @fileName http返回信息
 * @data on  2019/2/14 10:27
 * @describe 响应数据的基类
 */
public class BaseReseponseInfo implements Serializable {
    /**
     * 连接成功
     */
    public final static int SUCCESS = 0;

    /**
     * 内容
     */
    private String mg;

    /**
     * 标签
     */
    private int flag;


    public String getMsg() {
        return mg;
    }

    public void setMsg(String msg) {
        this.mg = msg;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setFlag(String flag) {
        this.flag = Integer.parseInt(flag);
    }
}
