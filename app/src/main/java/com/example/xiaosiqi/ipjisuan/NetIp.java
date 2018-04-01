package com.example.xiaosiqi.ipjisuan;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaosiqi on 2018/1/6.
 * 得到公网IP
 */

public class NetIp {
        public String GetNetIp(){
            URL infoUrl = null;
            InputStream inStream = null;
            String ipLine = "";
            HttpURLConnection httpConnection = null;
            try {
//            infoUrl = new URL("http://ip168.com/");
                infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
                URLConnection connection = infoUrl.openConnection();
                httpConnection = (HttpURLConnection) connection;
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inStream = httpConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inStream, "utf-8"));
                    StringBuilder strber = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null){
                        strber.append(line + "\n");
                    }
                    Pattern pattern = Pattern
                            .compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
                    Matcher matcher = pattern.matcher(strber.toString());
                    if (matcher.find()) {
                        ipLine = matcher.group();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inStream.close();
                    httpConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            Log.e("getNetIp", ipLine);
            return ipLine;
        }

    }

