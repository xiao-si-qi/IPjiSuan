package com.example.xiaosiqi.ipjisuan.tools;

/**
 * Created by xiaos on 2018/3/28.
 *
 */
//    {
//        "code": 0,
//                "data": {
//                     "ip": " 59.51.78.210",
//                    "country": "中国",
//                    "area": "",
//                    "region": "湖南",
//                    "city": "衡阳",
//                    "county": "XX",
//                    "isp": "电信",
//                    "country_id": "CN",
//                    "area_id": "",
//                    "region_id": "430000",
//                    "city_id": "430400",
//                    "county_id": "xx",
//                    "isp_id": "100017"
//    }
//    }

public class City_class {
    String IP;
    String 国家;
    String 省;
    String 城市;
    String 运营商;
    String 城市ID;

    public City_class(String IP, String 国家, String 省, String 城市, String 运营商, String 城市ID) {
        this.IP = IP;
        this.国家 = 国家;
        this.省 = 省;
        this.城市 = 城市;
        this.运营商 = 运营商;
        this.城市ID = 城市ID;
    }

    public City_class() {
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String get国家() {
        return 国家;
    }

    public void set国家(String 国家) {
        this.国家 = 国家;
    }

    public String get省() {
        return 省;
    }

    public void set省(String 省) {
        this.省 = 省;
    }

    public String get城市() {
        return 城市;
    }

    public void set城市(String 城市) {
        this.城市 = 城市;
    }

    public String get运营商() {
        return 运营商;
    }

    public void set运营商(String 运营商) {
        this.运营商 = 运营商;
    }

    public String get城市ID() {
        return 城市ID;
    }

    public void set城市ID(String 城市ID) {
        this.城市ID = 城市ID;
    }
}
