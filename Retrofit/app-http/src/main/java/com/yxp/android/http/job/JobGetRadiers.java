package com.yxp.android.http.job;

import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.RaidersEntity;
import com.yxp.android.http.model.base.BaseListEntry;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/24.
 */
public class JobGetRadiers extends IntegralWallJob<BaseListEntry<RaidersEntity>> {
    @Override
    public BaseListEntry<RaidersEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long time  = getTimestamp();
        String sign= md5(uid + time + getKey());
        return apis().getRaiders(uid, time, sign).execute().body();
    }

    @Override
    public void finish(BaseListEntry<RaidersEntity> raidersEntityBaseListEntry) throws JobException {
        super.finish(raidersEntityBaseListEntry);
        if( null == raidersEntityBaseListEntry){
            return;
        }
        List<RaidersEntity> list = raidersEntityBaseListEntry.getBaseListData().getList();
        SqlInsert<RaidersEntity> sql = new SqlInsert<>(RaidersEntity.class);
        sql.insert(list, 0);
    }
}
