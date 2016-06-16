package com.yxp.android.http.model.base;

import com.andcup.android.frame.datalayer.job.JobEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxp.android.http.IntegralWallException;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.exception.AccountDisableException;
import com.yxp.android.http.exception.AnotherLoginException;

import org.greenrobot.eventbus.EventBus;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/9.
 */
public class BasesEntity<T>extends JobEntity<T> {


    @JsonProperty("errcode")
    int    mErrCode;
    @JsonProperty("errmsg")
    String mMessage;
    @JsonProperty("data")
    T      mBody;

    public void setErrCode(int mErrCode) {
        this.mErrCode = mErrCode;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    @Override
    public int getErrCode() {
        return mErrCode;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    @Override
    public T body() {
        return mBody;
    }

    @Override
    public void throwIfException() {
        if(mErrCode < 0){
            if(mErrCode == IntegralWallJob.LOGIN_AT_ANOTHER){
                EventBus.getDefault().post(new AnotherLoginException());
            }else if(mErrCode == IntegralWallJob.ACCOUNT_DISABLED){
                EventBus.getDefault().post(new AccountDisableException(mMessage));
            }else if(mErrCode == IntegralWallJob.EQUIPMENT_HAS_BEEN_BINDING_NUMBER){
                throw new IntegralWallException(mErrCode+"&"+mMessage);
            }
            throw new IntegralWallException(mMessage);
        }
    }
}
