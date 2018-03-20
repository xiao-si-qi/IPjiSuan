package com.example.xiaosiqi.ipjisuan;

import android.util.Log;

/**
 * Created by xiaos on 2018/3/17.
 */

public class Z子网划分 {
    private static final String TAG = "Z子网划分";
    public static String j借位(String z子网掩码,int y要借的位数)
    {
        String j借位后的子网掩码="";
        int s剩余的位数=0;
        for (int i = 0; i <z子网掩码.length() ; i++) {
        if (z子网掩码.charAt(i)=='0')
        {
            s剩余的位数++;
        }
    }
    if (s剩余的位数<=y要借的位数)
    {
        return "-1";
    }
        j借位后的子网掩码=IP转二进制.网络长度转二进制(IP转二进制.二进制掩码转网络长度(z子网掩码)+y要借的位数);
        return j借位后的子网掩码;
    }
    public static String j根据要保留的位数借位(String z子网掩码,int y要保留的位数)
    {
        String j借位后的子网掩码="";
        int s剩余的位数=0;
        Log.d(TAG, "j根据要保留的位数借位: "+y要保留的位数);
        for (int i = 0; i <z子网掩码.length() ; i++) {
            if (z子网掩码.charAt(i)=='0')
            {
                s剩余的位数++;
            }
        }
        if (s剩余的位数<=y要保留的位数)
        {
            return "-1";
        }
        j借位后的子网掩码=IP转二进制.网络长度转二进制(IP转二进制.二进制掩码转网络长度(z子网掩码)+(32-(IP转二进制.二进制掩码转网络长度(z子网掩码)+y要保留的位数)));

        return j借位后的子网掩码;
    }
    public static String 计算借位后的网络地址(int w网络编号,String e二进制IP地址,String j借位前的子网掩码,String j借位后的子网掩码)
    {
        int z左边界=0;
        int y右边界=0;
        for (int i = 0; i < e二进制IP地址.length(); i++) {
            if (j借位前的子网掩码.charAt(i)=='0')
            {
                z左边界=i;
                break;
            }

        }
        for (int i = 0; i < e二进制IP地址.length(); i++) {
            if (j借位后的子网掩码.charAt(i)=='0')
            {
                y右边界=i;
                break;
            }
        }
        int e二进制网络编号的长度=y右边界-z左边界;
        String y要插入的二进制网络编号=binary2decimal(w网络编号,e二进制网络编号的长度) ;
        Log.d(TAG, "要插入的二进制网络编号: "+y要插入的二进制网络编号);
        Log.d(TAG, "左边界 "+z左边界);
        Log.d(TAG, "右边界 "+y右边界);
        Log.d(TAG, "借位前的子网掩码: "+j借位前的子网掩码);
        Log.d(TAG, "借位后的子网掩码: "+j借位后的子网掩码);
        Log.d(TAG, "e二进制IP地址: "+e二进制IP地址);
        StringBuilder strIP = new StringBuilder(e二进制IP地址);

        Log.d(TAG, "填充0后的地址"+strIP);
        for (int i = y右边界; i >= z左边界&&e二进制网络编号的长度>0; i--) {
            Log.d(TAG, "e二进制网络编号的长度 "+e二进制网络编号的长度);
            if (strIP.charAt(i-1)=='.')continue; //绕过中间的点
            strIP=strIP.replace(i-1,i, y要插入的二进制网络编号.charAt(--e二进制网络编号的长度)+"");
            Log.d(TAG, "计算借位后的网络地址: "+strIP);

        }
        return strIP.toString();
    }
    public static String binary2decimal(int decNum , int digit) {
        String binStr = "";
        for(int i= digit-1;i>=0;i--) {
            binStr +=(decNum>>i)&1;
        }
        return binStr;
    }
}
