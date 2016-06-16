package com.yxp.android.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxp.android.http.model.base.AbsAuthEntry;

import java.io.Serializable;

/**
 * Created by Amos on 2015/9/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaoBaoEntry extends AbsAuthEntry implements Serializable {

    int    mType = AUTH_TYPE_TAOBAO;   //1 weibo 2 weinxin 3 qq 4taobao
    @JsonProperty("openid")
    String mUid;
    @JsonProperty("country")
    String mCountry;
    @JsonProperty("city")
    String mCity;
    @JsonProperty("nickname")
    String mNickName;
    @JsonProperty("sex")
    String mSex;
    @JsonProperty("headimgurl")
    String mAvatarUrl;

    public String getUid() {
        return mUid;
    }

    public String getLocation() {
        return mCountry + mCity;
    }

    public String getSex() {
        return mSex;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }

    public void setAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public void setNickName(String mNickName) {
        this.mNickName = mNickName;
    }

    public int getType() {
        return mType;
    }

    public String getNickName() {
        return mNickName;
    }

    @Override
    public String getAvatar() {
        return mAvatarUrl;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }
}
