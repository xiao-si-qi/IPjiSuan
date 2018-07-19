package com.example.xiaosiqi.ipjisuan.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xiaosiqi on 2017/12/28.
 */
public class Toosl {
    private static final String TAG = "Toosl";
   public String HttpUrl( String type) throws Exception {
    String res="";
    String pash="http://ip.taobao.com/service/getIpInfo.php?ip="+type;
    Log.d(TAG, "地址是HttpUrl: "+pash);
    URL url=new URL(pash);

    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
       conn.setRequestProperty("Connection","Keep-Alive");// 维持长连接
       conn.setConnectTimeout(5000);
       conn.setDoInput(true);
       conn.setRequestMethod("GET");
    if (conn.getResponseCode()==200)
    {
        InputStreamReader reader=new InputStreamReader(conn.getInputStream());
        BufferedReader buff=new BufferedReader(reader);
        String line="";
        while ((line=buff.readLine())!=null)
        {
            res+=line;
        }
    }
    else {
        throw new RuntimeException("返回值不是200是："+conn.getResponseCode());
    }
       Log.d(TAG, "返回的信息 "+res);
    return res;
}
}
