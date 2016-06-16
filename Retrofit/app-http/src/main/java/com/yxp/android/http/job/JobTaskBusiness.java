package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.CompleteInfoEntity;
import com.yxp.android.http.model.ScoreDetailEntity;
import com.yxp.android.http.model.TaskBusinessEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/18.
 */
public class JobTaskBusiness extends IntegralWallJob<BaseEntity<CompleteInfoEntity>> {

    TaskBusinessEntity mBusinessEntity;

    public JobTaskBusiness(TaskBusinessEntity taskBusinessEntity){
        mBusinessEntity = taskBusinessEntity;
    }

    @Override
    public BaseEntity<CompleteInfoEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long   time= getTimestamp();
        String sign= md5(uid+ mBusinessEntity.getTaskId() + time + getKey());
        return apis().completeTask(uid,
                mBusinessEntity.getTaskId(),
                mBusinessEntity.getTaskOptionId(),
                null,
                mBusinessEntity.getTaskOptionId(),
                String.valueOf(mBusinessEntity.getTimeStart()/1000),
                String.valueOf(mBusinessEntity.getTimeEnd()/1000),
                time,
                sign,
                null).execute().body();
    }
}
