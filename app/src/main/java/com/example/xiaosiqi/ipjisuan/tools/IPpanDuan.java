package com.example.xiaosiqi.ipjisuan.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaosiqi on 2018/1/28.
 * 判断IP是否合法
 */

public class IPpanDuan {

    public static boolean panDuan(String ip) {
        String regs = "^([0-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                "([0-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                "([0-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\." +
                "([0-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])$";
        Pattern pattern = Pattern.compile(regs);
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

    }


}
