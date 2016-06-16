package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/26.
 */
public class JobInviteCode extends IntegralWallJob<BaseEntity> {

    String mInviteCode;

    public JobInviteCode(String inviteCode){
        mInviteCode=inviteCode;
    }

    @Override
    public BaseEntity start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long time=getTimestamp();
        String sign=md5(uid+mInviteCode+time+getKey());
        return apis().inviteCode(uid,mInviteCode,time,sign).execute().body();
    }
}
