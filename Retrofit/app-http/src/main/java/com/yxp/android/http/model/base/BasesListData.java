package com.yxp.android.http.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Amos on 2015/9/3.
 */
public class BasesListData<T> implements Serializable {
    @JsonProperty("data")
    List<T> mList;
    @JsonProperty("errcode")
    int mTotalCount;
    @JsonProperty("errmsg")
    String mContent;

    public List<T> getList() {
        return mList;
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public String getContent() {
        return mContent;
    }
}
