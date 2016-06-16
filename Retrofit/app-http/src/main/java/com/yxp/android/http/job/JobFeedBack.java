package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/25.
 */
public class JobFeedBack extends IntegralWallJob<BaseEntity> {

    String mQuestion;
    public JobFeedBack(String question){
        mQuestion=question;
    }
    @Override
    public BaseEntity start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long time=getTimestamp();
        String sign=md5(uid+time+getKey());
        return apis().feedBack(uid,mQuestion,time,sign).execute().body();
    }
}
