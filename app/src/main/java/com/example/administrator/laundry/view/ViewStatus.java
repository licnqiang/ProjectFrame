package com.example.administrator.laundry.view;

/**
 * Created by Lawson on 2017/6/6.
 */

public enum ViewStatus {
    START("数据加载中", 0), SUCCESS("获取数据成功", 1), FAILURE("获取数据失败", 2), EMPTY("数据集合为空", 3), NOTNETWORK("无网络访问", 4);

    private ViewStatus(String statusName, int status) {
    }
}
