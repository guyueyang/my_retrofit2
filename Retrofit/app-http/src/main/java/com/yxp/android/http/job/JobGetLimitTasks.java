package com.yxp.android.http.job;


import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.TaskFeatureEntity;
import com.yxp.android.http.model.TaskLimitEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/16.
 */
public class JobGetLimitTasks extends IntegralWallJob<BaseListEntry<TaskLimitEntity>> {

    int FEATURE_TYPE = 0;

    int mPageIndex;
    int mPageSize;

    public JobGetLimitTasks(int pageIndex, int pageSize){
        mPageIndex = pageIndex;
        mPageSize  = pageSize;
    }

    @Override
    public BaseListEntry<TaskLimitEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long  time = getTimestamp();
        String sign = md5(uid + FEATURE_TYPE + mPageSize + time + getKey());
        return apis().getLimitTasks(uid, FEATURE_TYPE, 1, mPageIndex + 1, mPageSize, time, sign).execute().body();
    }

    @Override
    public void finish(BaseListEntry<TaskLimitEntity> tasks) throws JobException {
        List<TaskLimitEntity> taskEntityList = tasks.getBaseListData().getList();
        for (int i = 0; null != taskEntityList && i < taskEntityList.size(); i++) {
            TaskFeatureEntity task = taskEntityList.get(i);
            task.setTaskState(FEATURE_TYPE);
            task.setUid(UserProvider.getInstance().getUid());
        }
        SqlInsert<TaskLimitEntity> dataOperator = new SqlInsert<>(TaskLimitEntity.class, Where.user());
        dataOperator.insert(tasks.getBaseListData().getList(), mPageIndex * mPageSize);
    }
}
