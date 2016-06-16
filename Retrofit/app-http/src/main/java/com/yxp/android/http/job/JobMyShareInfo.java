package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.model.ShareInfoEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.where.Where;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/25.
 */
public class JobMyShareInfo extends IntegralWallJob<BaseEntity<ShareInfoEntity>> {

    @Override
    public BaseEntity<ShareInfoEntity> start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long   time=getTimestamp();
        String sign=md5(uid+time+getKey());
        return apis().getMyShareInfo(uid,time,sign).execute().body();
    }

    @Override
    public void finish(BaseEntity<ShareInfoEntity> shareInfoEntity) throws JobException{
        SqlInsert<ShareInfoEntity> dataOperator = new SqlInsert<>(ShareInfoEntity.class, Where.user());
        shareInfoEntity.body().setUid(UserProvider.getInstance().getUid());
        dataOperator.insert(shareInfoEntity.body());
    }
}
