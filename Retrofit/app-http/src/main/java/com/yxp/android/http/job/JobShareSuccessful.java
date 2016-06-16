package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.ScoreDetailEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/25.
 */
public class JobShareSuccessful extends IntegralWallJob<BaseEntity> {
    @Override
    public BaseEntity start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long   time=getTimestamp();
        String sign=md5(uid+time+getKey());
        return apis().getShareSuccessful(uid,time,sign).execute().body();
    }
}
