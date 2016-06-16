package com.yxp.android.http.model;

import com.andcup.android.database.activeandroid.annotation.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxp.android.http.model.db.DbColumn;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class DailyTaskEntity extends IntegralWallEntity implements Serializable {

    public static final int DAILY_SIGN_IN=1;
    public static final int DAILY_SHARE=2;
    public static final int DAILY_QUICK_TASK=3;
    public static final int DAILY_UNION_TASK=4;

    @Column(name = DbColumn.USER_UID)
    String mUid;
    @JsonProperty("id")
    @Column
    String mId;
    @JsonProperty("title")
    @Column
    String mTitle;
    @JsonProperty("is_complete")
    @Column
    String mIsComplete;

    public String getTitle() {
        return mTitle;
    }

    public String getIsComplete() {
        return mIsComplete;
    }

    public String getItemId() {
        return mId;
    }
}
