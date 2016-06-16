package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.TaskQuickEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;
import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/16.
 */
public class JobGetQuickTasks extends IntegralWallJob<BaseListEntry<TaskQuickEntity>> {

    int QUICK_TYPE = 2;

    int mPageIndex;
    int mPageSize;

    public JobGetQuickTasks(int pageIndex, int pageSize){
        mPageIndex = pageIndex;
        mPageSize  = pageSize;
    }

    @Override
    public BaseListEntry<TaskQuickEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long  time = getTimestamp();
        String sign = md5(uid + 0 + time + getKey());
        return apis().getQuickTasks(uid, 0, QUICK_TYPE, mPageIndex + 1, mPageSize, time, sign ).execute().body();
    }

    @Override
    public void finish(BaseListEntry<TaskQuickEntity> tasks) throws JobException {
        List<TaskQuickEntity> taskEntityList = tasks.getBaseListData().getList();
        for (int i = 0; null != taskEntityList && i < taskEntityList.size(); i++) {
            TaskQuickEntity task = taskEntityList.get(i);
            task.setTaskState(QUICK_TYPE);
            task.setUid(UserProvider.getInstance().getUid());
        }
        SqlInsert<TaskQuickEntity> dataOperator = new SqlInsert<>(TaskQuickEntity.class, Where.user());
        dataOperator.insert(tasks.getBaseListData().getList(), mPageIndex * mPageSize);
    }
}
