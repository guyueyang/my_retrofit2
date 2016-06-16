package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/26.
 */
public class JobEditPassword extends IntegralWallJob<BaseEntity> {

    String mPassword;
    String mNewPassword;

    public JobEditPassword(String password,String newPassword){
        mPassword=password;
        mNewPassword=newPassword;
    }

    @Override
    public BaseEntity start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long time=getTimestamp();
        String password=md5(mPassword);
        String newPassword=md5(mNewPassword);
        String sign=md5(uid+password+newPassword+time+getKey());
        return apis().editPassword(uid,password,newPassword,time,sign).execute().body();
    }
}
