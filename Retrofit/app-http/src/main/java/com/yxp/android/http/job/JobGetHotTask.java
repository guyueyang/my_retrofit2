package com.yxp.android.http.job;

import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.HotTaskEntity;
import com.yxp.android.http.model.base.BaseListEntry;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/15.
 */
public class JobGetHotTask extends IntegralWallJob<BaseListEntry<HotTaskEntity>> {

    @Override
    public BaseListEntry<HotTaskEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        int number = 10;
        long time= getTimestamp();
        String sign = md5(uid + number + time + getKey());
        BaseListEntry<HotTaskEntity> entry = apis().getHotTask(uid,number,time,sign).execute().body();
        return entry;
    }

    @Override
    public void finish(BaseListEntry<HotTaskEntity> data) throws JobException {
        super.finish(data);
        SqlInsert<HotTaskEntity> dataOperator = new SqlInsert<>(HotTaskEntity.class);
        dataOperator.insert(data.data().getList(), 0);
    }
}
