package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/24.
 */
public class JobEditNickName extends IntegralWallJob<BaseEntity> {
    String mNickName;

    public JobEditNickName(String nickName){
        mNickName=nickName;
    }
    @Override
    public BaseEntity start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long time=getTimestamp();
        String sign=md5(uid+time+getKey());
        return apis().editNickName(uid,mNickName,time,sign).execute().body();
    }
}
