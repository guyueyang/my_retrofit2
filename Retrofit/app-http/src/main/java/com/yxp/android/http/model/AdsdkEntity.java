package com.yxp.android.http.model;

import com.andcup.android.database.activeandroid.annotation.Column;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/24.
 */
public class AdsdkEntity  implements Serializable{

    //广告ID
    @JsonProperty("ads_id")
     String ads_id;
    //广告名字
    @JsonProperty("ads_name")
     String ads_name;
    //图片URL地址
    @JsonProperty("img_url")
     String img_url;
    //广告链接地址
    @JsonProperty("lpg_url")
     String lpg_url;
    //广告开始日期
    @JsonProperty("begin_date")
     String begin_date;
    //广告结束日期
    @JsonProperty("end_date")
     String end_date;
    //广告开始时间
    @JsonProperty("period_from")
     String period_from;
    //广告结束时间
    @JsonProperty("period_to")
     String period_to;
    //广告宽度
    @JsonProperty("width")
     String width;
    //广告高度
    @JsonProperty("height")
    public String height;

    public String getAds_id() {
        return ads_id;
    }

    public void setAds_id(String ads_id) {
        this.ads_id = ads_id;
    }

    public String getAds_name() {
        return ads_name;
    }

    public void setAds_name(String ads_name) {
        this.ads_name = ads_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLpg_url() {
        return lpg_url;
    }

    public void setLpg_url(String lpg_url) {
        this.lpg_url = lpg_url;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getPeriod_from() {
        return period_from;
    }

    public void setPeriod_from(String period_from) {
        this.period_from = period_from;
    }

    public String getPeriod_to() {
        return period_to;
    }

    public void setPeriod_to(String period_to) {
        this.period_to = period_to;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
