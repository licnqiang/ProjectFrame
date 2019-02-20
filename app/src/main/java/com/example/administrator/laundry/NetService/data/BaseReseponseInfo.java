package com.example.administrator.laundry.NetService.data;

import java.io.Serializable;

/**
 * @author lq
 * @fileName http返回信息
 * @data on  2019/2/14 10:27
 * @describe 响应数据的基类
 */
public class BaseReseponseInfo implements Serializable {
    public final static int SUCCESS = 200;//连接成功



    String info;// 内容

    int flag;// 标签

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
