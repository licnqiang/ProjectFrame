package com.project.Kang_Lee.laundry.netService.http;

/**
 * @author lq
 * @fileName 配置ip
 * @data on  2019/2/14 10:37
 * @describe 切换网络ip
 */

public class UrlConfig {

    /**
     * 测试服务器
     */
    private final static String U2 = "http://192.168.0.103:8089/dl_laundry/";

    /**
     * 正式
     */
    private final static String U1 = "http://192.168.0.103:8089/dl_laundry/";

    /**
     * 测试版本下 正式测试服务器地址切换用
     */
    private static boolean flag = true;

    /**
     * 切换服务器
     */
    public static final String URL_PREFIX = flag ? U1 : U2;

}