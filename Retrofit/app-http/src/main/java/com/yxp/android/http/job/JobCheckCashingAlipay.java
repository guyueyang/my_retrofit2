package com.yxp.android.http.job;



import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/22.
 */
public class JobCheckCashingAlipay extends IntegralWallJob<BaseEntity> {

    String mPhoneNumber;
    String mCashingItemId;
    String mName;
    String mAlipayAccount;

    public JobCheckCashingAlipay(String account, String phoneNumber, String cashItemId, String name){
        mPhoneNumber = phoneNumber;
        mCashingItemId = cashItemId;
        mName = name;
        mAlipayAccount = account;
    }


    @Override
    public BaseEntity start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long time  = getTimestamp();
        String sign = md5(uid + mCashingItemId + mAlipayAccount + mPhoneNumber + time + getKey());
        BaseEntity entity = apis().checkCashingAlipay(uid, mCashingItemId, mAlipayAccount, mPhoneNumber, mName, time, sign).execute().body();
        return entity;
    }
}
