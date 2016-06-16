package com.yxp.android.http.job;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.model.UserInfoEntity;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/14.
 */
public class JobGetUserInfo extends IntegralWallJob<BaseEntity<UserInfoEntity>>{

    @Override
    public BaseEntity<UserInfoEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long  time = getTimestamp();
        String sign = md5(uid + time + getKey());
        return apis().getUserInfo(uid, time, sign ).execute().body();
    }

    @Override
    public void finish(BaseEntity<UserInfoEntity> userInfo) throws JobException {
        SqlInsert<UserInfoEntity> dataOperator = new SqlInsert<>(UserInfoEntity.class);
        UserProvider.getInstance().setUserInfo(userInfo.body());
        dataOperator.insert(userInfo.body());
    }
}
