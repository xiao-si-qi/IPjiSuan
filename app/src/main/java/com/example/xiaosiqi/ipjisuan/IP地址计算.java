package com.example.xiaosiqi.ipjisuan;

import android.util.Log;

/**
 * Created by xiaosiqi on 2018/3/15.
 */

public class IP地址计算 {
    private static final String TAG = "IP地址计算";

    public static String j计算网络地址(String IP, String IPmask) {
        StringBuilder strIP = new StringBuilder(IP);
        String 网络地址 = "";
        for (int i = 0; i < IP.length(); i++) {
            if (IPmask.charAt(i) == '0') {
                strIP = strIP.replace(i, i + 1, "0");
            }
        }
        网络地址 = IP转二进制.二进制转IP(strIP.toString());
        return 网络地址;
    }

    public static String j计算第一个ip(String IP, String IPmask) {
        StringBuilder strIP = new StringBuilder(IP);
        String 第一个IP地址 = "";
        for (int i = 0; i < IP.length(); i++) {
            if (IPmask.charAt(i) == '0') {
                strIP = strIP.replace(i, i + 1, "0");
            }
        }
        strIP = strIP.replace(IP.length() - 1, IP.length(), "1");
        第一个IP地址 = IP转二进制.二进制转IP(strIP.toString());
        return 第一个IP地址;
    }

    public static String j计算广播地址(String IP, String IPmask) {
        StringBuilder strIP = new StringBuilder(IP);
        String 广播地址 = "";
        for (int i = 0; i < IP.length(); i++) {
            if (IPmask.charAt(i) == '0') {
                strIP = strIP.replace(i, i + 1, "1");
            }
        }
        广播地址 = IP转二进制.二进制转IP(strIP.toString());
        return 广播地址;
    }

    public static String j计算最后的IP地址(String IP, String IPmask) {
        StringBuilder strIP = new StringBuilder(IP);
        String 最后的IP地址 = "";
        for (int i = 0; i < IP.length(); i++) {
            if (IPmask.charAt(i) == '0') {
                strIP = strIP.replace(i, i + 1, "1");
            }
        }
        strIP = strIP.replace(IP.length() - 1, IP.length(), "0");
        最后的IP地址 = IP转二进制.二进制转IP(strIP.toString());
        return 最后的IP地址;
    }

    public static int j计算可用ip的个数(String IPmask) {
        int z主机位长度 = 0;
        for (int i = 0; i < IPmask.length(); i++) {
            if (IPmask.charAt(i) == '0') {
                z主机位长度++;
            }
        }
        int 可用IP个数 = (int) (Math.pow(2, z主机位长度)) - 2;
        return 可用IP个数;
    }


}
