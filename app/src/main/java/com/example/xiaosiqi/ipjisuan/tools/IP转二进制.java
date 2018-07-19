package com.example.xiaosiqi.ipjisuan.tools;

import android.util.Log;

/**
 * Created by xiaosiqi on 2018/1/28.
 * //IP转二进制
 */

public class IP转二进制 {
    public static String ip转二进制(String strIP)

    {
        int[] intIp = {0, 0, 0, 0};
        String[] ip = strIP.split("[.]");
        for (int i = 0; i < ip.length; i++) {
            intIp[i] = Integer.valueOf(ip[i]);
        }
        String strip = "";
        String str合成ip = "";
        for (int i = 0; i < ip.length; i++) {
            strip = Integer.toBinaryString(intIp[i]);
            if (strip.length() < 8) {
                for (; strip.length() < 8; ) {
                    strip = 0 + strip;
                }
            }
            if (i < 3) {
                str合成ip += strip + ".";
            } else {
                str合成ip += strip;
            }
        }
        return str合成ip;
    }

    public static String 网络长度转二进制(int mask) {
        String str合成ip = "";
        for (int i = 0; i < 32; i++) {
            if (mask > 0) {
                str合成ip += "1";
                mask--;
            } else {
                str合成ip += "0";
            }
            if (i == 7 || i == 15 || i == 23) {
                str合成ip += ".";
            }


        }
        return str合成ip;
    }

    public static String 二进制转IP(String IP) {
        String str合成ip = "";
        String 分段二进制IP[] = IP.split("[.]");
        str合成ip = Integer.parseInt(分段二进制IP[0], 2) + "."
                + Integer.parseInt(分段二进制IP[1], 2) + "."
                + Integer.parseInt(分段二进制IP[2], 2) + "." +
                Integer.parseInt(分段二进制IP[3], 2);
        return str合成ip;
    }

    public static int 二进制掩码转网络长度(String IP) {
        int ip长度 = 0;
        for (int i = 0; i < IP.length(); i++) {
            char c = IP.charAt(i);
            if (c == '1') {
                ip长度++;
            }
            if (c == '0') {
                break;
            }
        }
        return ip长度;
    }
}

