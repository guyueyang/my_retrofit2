package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/23.
 */
public class JobDailyTaskAddScore extends IntegralWallJob<BaseEntity> {
    @Override
    public BaseEntity start() throws IOException {
        long time=getTimestamp();
        String uid = UserProvider.getInstance().getUid();
        String sign=md5(uid+time+getKey());
        return apis().getDailyTaskAddScore(uid,time,sign).execute().body();
    }
}
