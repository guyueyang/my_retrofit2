package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.ScoreDetailEntity;
import com.yxp.android.http.model.TaskUnionPlatformEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;
import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/16.
 */
public class JobUnionPlatform extends IntegralWallJob<BaseListEntry<TaskUnionPlatformEntity>> {

    int mPageIndex;
    int mPageSize;

    public JobUnionPlatform(int pageIndex,int pageSize){
        mPageIndex=pageIndex;
        mPageSize=pageSize;
    }
    @Override
    public BaseListEntry<TaskUnionPlatformEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long time=getTimestamp();
        String sign=md5(uid + time + getKey());
        return apis().getUnionPlatform(uid,mPageIndex+1,mPageSize,time,sign)
                .execute()
                .body();
    }

    @Override
    public void finish(BaseListEntry<TaskUnionPlatformEntity> tasks) throws JobException {
        SqlInsert<TaskUnionPlatformEntity> dataOperator = new SqlInsert<>(TaskUnionPlatformEntity.class);
        dataOperator.insert(tasks.getBaseListData().getList(), mPageIndex * mPageSize);
    }
}
