package com.yxp.android.http.job;

import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.LoginEntity;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/10.
 */
public class JobUserRegister extends AbsJobUserLogin {

    private String mPhoneNumber;
    private String mPassword;
    private String mInviteCode;
    private String mVerifyCode;

    public JobUserRegister(String phoneNumber, String password,String inviteCode,String verifyCode){
        mPhoneNumber=phoneNumber;
        mPassword=password;
        mInviteCode=inviteCode;
        mVerifyCode=verifyCode;
    }

    @Override
    public BaseEntity<LoginEntity> login() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        long time = getTimestamp();
        String pwMd5=md5(mPassword);
        String sign=md5(mPhoneNumber+mVerifyCode+pwMd5+time+getKey());
        return apis().register(uid,mPhoneNumber,pwMd5, mVerifyCode ,mInviteCode,time,sign)
                .execute()
                .body();
    }
}
