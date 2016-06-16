package com.yxp.android.http.model.base;


import com.yxp.android.http.IntegralWallException;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.exception.AccountDisableException;
import com.yxp.android.http.exception.AnotherLoginException;

import org.greenrobot.eventbus.EventBus;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/5.
 */
public class BaseErrorEntity<T> extends BaseEntity<T> {

    @Override
    public void throwIfException() {
        if(mErrCode == IntegralWallJob.ACCOUNT_DISABLED){
            //不抛出异常.
            EventBus.getDefault().post(new AccountDisableException(mMessage));
        }else if(mErrCode == IntegralWallJob.DO_TASK_BEFORE_CASH_CHECK){
            //不抛出异常.
            return;
        }else if(mErrCode < 0){
            if(mErrCode == IntegralWallJob.LOGIN_AT_ANOTHER){
                EventBus.getDefault().post(new AnotherLoginException());
                throw new IntegralWallException(mMessage);
            }
        }
    }
}
