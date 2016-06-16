package com.yxp.android.http.job;


import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/5/31.
 */
public class JobViewPush extends IntegralWallJob<BaseEntity> {

    String mPushId;

    public JobViewPush(String pushId){
        mPushId = pushId;
    }

    @Override
    public BaseEntity start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        long   time=getTimestamp();
        String sign=md5(uid + mPushId + time + getKey());
        return apis().viewPush(uid, mPushId, time, sign).execute().body();
    }
}
