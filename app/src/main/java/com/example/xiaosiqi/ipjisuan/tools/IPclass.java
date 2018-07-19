package com.example.xiaosiqi.ipjisuan.tools;

/**
 * Created by xiaos on 2018/3/17.
 */

public class IPclass {
    private int w网络编号;
    private String w网络地址;
    private String d第一个主机;
    private String z最后一个主机;
    private String g广播地址;

    public IPclass(int w网络编号, String w网络地址, String d第一个主机, String z最后一个主机, String g广播地址) {
        this.w网络编号 = w网络编号;
        this.w网络地址 = w网络地址;
        this.d第一个主机 = d第一个主机;
        this.z最后一个主机 = z最后一个主机;
        this.g广播地址 = g广播地址;
    }

    public int getW网络编号() {
        return w网络编号;
    }

    public void setW网络编号(int w网络编号) {
        this.w网络编号 = w网络编号;
    }

    public String getW网络地址() {
        return w网络地址;
    }

    public void setW网络地址(String w网络地址) {
        this.w网络地址 = w网络地址;
    }

    public String getD第一个主机() {
        return d第一个主机;
    }

    public void setD第一个主机(String d第一个主机) {
        this.d第一个主机 = d第一个主机;
    }

    public String getZ最后一个主机() {
        return z最后一个主机;
    }

    public void setZ最后一个主机(String z最后一个主机) {
        this.z最后一个主机 = z最后一个主机;
    }

    public String getG广播地址() {
        return g广播地址;
    }

    public void setG广播地址(String g广播地址) {
        this.g广播地址 = g广播地址;
    }
}
