package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/26.
 */
public class JobEditBirthday extends IntegralWallJob<BaseEntity> {

    String mBirthday;

    public JobEditBirthday(String birthday){
        mBirthday=birthday;
    }

    @Override
    public BaseEntity start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long time=getTimestamp();
        String sign=md5(uid+mBirthday+time+getKey());
        return apis().editBirthday(uid,mBirthday,time,sign).execute().body();
    }
}
