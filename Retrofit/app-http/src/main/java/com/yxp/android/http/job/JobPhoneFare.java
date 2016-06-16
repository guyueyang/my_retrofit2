package com.yxp.android.http.job;


import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseErrorEntity;
import com.yxp.android.http.tools.MD5;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/22.
 */
public class JobPhoneFare extends IntegralWallJob<BaseErrorEntity> {

    String mPhoneNumber;
    String mCashingItemId;

    public JobPhoneFare(String phoneNumber, String cashItemId){
        mPhoneNumber = phoneNumber;
        mCashingItemId = cashItemId;
    }

    @Override
    public BaseErrorEntity start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long time = getTimestamp();
        String sign = MD5.toMd5(uid + mPhoneNumber + mCashingItemId + time + getKey());
        return apis().phoneFare(uid, mPhoneNumber, mCashingItemId, time, sign).execute().body();
    }
}
