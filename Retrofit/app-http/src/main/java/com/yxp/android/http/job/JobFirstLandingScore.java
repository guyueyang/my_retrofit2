package com.yxp.android.http.job;



import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/23.
 */
public class JobFirstLandingScore extends IntegralWallJob<BaseEntity> {
    @Override
    public BaseEntity start() throws IOException {
        long time=getTimestamp();
        String uid = UserProvider.getInstance().getUid();
        String sign=md5(uid+1+time+getKey());
        return apis().getFirstLandingScore(uid,1,time,sign).execute().body();
    }
}
