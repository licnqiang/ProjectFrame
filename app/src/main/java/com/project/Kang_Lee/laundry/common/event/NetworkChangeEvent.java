package com.project.Kang_Lee.laundry.common.event;

/**
 * 网络连接类
 * @author wangyj
 * @time 2018/8/14 14:01
 */

public class NetworkChangeEvent {
    public int networkType;
    public NetworkChangeEvent(int networkType) {
        this.networkType=networkType;
    }
}
