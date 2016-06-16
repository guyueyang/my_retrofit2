package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.model.RankEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/23.
 */
public class JobGetRankList extends IntegralWallJob<BaseListEntry<RankEntity>>{

    String mType;


    public JobGetRankList(String type){
        mType = type;
    }

    @Override
    public BaseListEntry<RankEntity> start() throws IOException {
        long time   = getTimestamp();
        String sign = md5(mType+ time + getKey());
        return apis().getRank(mType, 99, time, sign).execute().body();
    }

    @Override
    public void finish(BaseListEntry<RankEntity> rankEntityBaseListEntry) throws JobException {
        super.finish(rankEntityBaseListEntry);
        if(null == rankEntityBaseListEntry ){
            return;
        }
        List<RankEntity> rankEntities = rankEntityBaseListEntry.data().getList();
        for(int i = 0; null != rankEntities && i< rankEntities.size(); i++){
            rankEntities.get(i).setRankType(mType);
        }
        SqlInsert<RankEntity> dataOperator = new SqlInsert<>(RankEntity.class, Where.rank(mType));
        dataOperator.insert(rankEntities, 0);
    }
}
