package com.yxp.android.http.model.base;

import com.andcup.android.frame.datalayer.job.JobEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.exception.AccountDisableException;
import com.yxp.android.http.exception.AnotherLoginException;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

/**
 * Created by Amos on 2015/9/3.
 */
public class BasesListEntry<T extends Serializable> extends JobEntity<T> implements Serializable {

    @JsonProperty("errcode")
    private int mStatus;
    @JsonProperty("errmsg")
    private String mMessage;
    @JsonProperty("data")
    private BaseListData<T> mData;

    public BaseListData<T> getBaseListData() {
        return mData;
    }

    @Override
    public int getErrCode() {
        return mStatus;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    @Override
    public void throwIfException() {
        if (mStatus < 0) {
            if (mStatus == IntegralWallJob.LOGIN_AT_ANOTHER) {
                EventBus.getDefault().post(new AnotherLoginException());
            } else if (mStatus == IntegralWallJob.ACCOUNT_DISABLED) {
                EventBus.getDefault().post(new AccountDisableException(mMessage));
            }
            throw new RuntimeException(mMessage);
        }
    }

    public BaseListData<T> data() {
        return mData;
    }

    @Override
    public T body() {
        return null;
    }

}
