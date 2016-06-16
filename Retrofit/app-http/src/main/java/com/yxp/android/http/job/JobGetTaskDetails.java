package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.model.TaskDetailEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.where.Where;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/17.
 */
public class JobGetTaskDetails extends IntegralWallJob<BaseEntity<TaskDetailEntity>>{

    String mTaskId;

    public JobGetTaskDetails(String taskId){
        mTaskId = taskId;
    }

    @Override
    public BaseEntity<TaskDetailEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long   time= getTimestamp();
        String sign= md5(uid + mTaskId + time + getKey());
        BaseEntity<TaskDetailEntity> detailBaseEntry = apis().getTaskDetail(uid, mTaskId, time, sign).execute().body();
        return detailBaseEntry;
    }

    @Override
    public void finish(BaseEntity<TaskDetailEntity> taskDetailEntityBaseEntity) throws JobException {
        taskDetailEntityBaseEntity.body().setUserId(UserProvider.getInstance().getUid());
        SqlInsert<TaskDetailEntity> dataOperator = new SqlInsert<>(TaskDetailEntity.class, Where.taskDetail(mTaskId));
        dataOperator.insert(taskDetailEntityBaseEntity.body());
    }
}
