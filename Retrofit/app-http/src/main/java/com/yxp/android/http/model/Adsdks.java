package com.yxp.android.http.model;

import com.andcup.android.database.activeandroid.Model;
import com.andcup.android.database.activeandroid.annotation.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxp.android.http.model.db.DbColumn;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/24.
 */
public class  Adsdks extends Model implements Serializable {

    //广告ID
    @Column(name = DbColumn.ADSDK_ID)
    int ads_id;
    //广告名字
    @Column(name = DbColumn.ADSDK_NAME)
     String ads_name;
    //图片URL地址
     @Column(name = DbColumn.ADSDK_IMG_URL)
     String img_url;
    //广告链接地址
    @Column(name = DbColumn.ADSDK_LPG_URL)
     String lpg_url;
    //广告开始日期
    @Column(name = DbColumn.ADSDK_BEGIN_DATE)
     String begin_date;
    //广告结束日期
    @Column(name = DbColumn.ADSDK_END_DATE)
     String end_date;
    //广告开始时间
    @Column(name = DbColumn.ADSDK_PEIOD_FROM)
     String period_from;
    //广告结束时间
    @Column(name = DbColumn.ADSDK_PERIOD_TO)
     String period_to;
    //广告宽度
    @Column(name = DbColumn.ADSDK_WIDTH)
     String width;
    //广告高度
    @Column(name = DbColumn.ADSDK_HEIGHT)
    String height;

    public int getAds_id() {
        return ads_id;
    }

    public void setAds_id(int ads_id) {
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
