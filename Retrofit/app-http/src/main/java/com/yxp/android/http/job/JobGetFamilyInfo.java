package com.yxp.android.http.job;

import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.FamilyInfoEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.where.Where;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/14.
 */
public class JobGetFamilyInfo extends IntegralWallJob<BaseEntity<FamilyInfoEntity>> {

    @Override
    public BaseEntity<FamilyInfoEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long  time = getTimestamp();
        String sign = md5(uid + time + getKey());
        return apis().getFamilyInfo(uid, time, sign ).execute().body();
    }

    @Override
    public void finish(BaseEntity<FamilyInfoEntity> familyInfo) throws JobException {
        familyInfo.body().setUid(UserProvider.getInstance().getUid());
        SqlInsert<FamilyInfoEntity> dataOperator = new SqlInsert<>(FamilyInfoEntity.class, Where.user());
        dataOperator.insert(familyInfo.body());
    }
}
