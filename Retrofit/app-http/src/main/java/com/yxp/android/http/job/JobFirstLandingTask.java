package com.yxp.android.http.job;

import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.FirstLandingTaskEntity;
import com.yxp.android.http.model.base.BaseListEntry;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class JobFirstLandingTask extends IntegralWallJob<BaseListEntry<FirstLandingTaskEntity>> {
    @Override
    public BaseListEntry<FirstLandingTaskEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long   time= getTimestamp();
        String sign=md5(uid+time+getKey());
        return apis().getFirstLandingTask(uid,time,sign)
                .execute()
                .body();
    }
    @Override
    public void finish(BaseListEntry<FirstLandingTaskEntity> firstLandingTaskEntityBaseListEntry) throws JobException{
        SqlInsert<FirstLandingTaskEntity> dataOperator = new SqlInsert<>(FirstLandingTaskEntity.class);
        dataOperator.insert(firstLandingTaskEntityBaseListEntry.getBaseListData().getList(), 0);
    }
}
