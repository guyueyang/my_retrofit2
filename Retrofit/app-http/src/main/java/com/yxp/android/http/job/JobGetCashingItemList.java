package com.yxp.android.http.job;

import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.model.CashingItemEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/22.
 */
public class JobGetCashingItemList extends IntegralWallJob<BaseListEntry<CashingItemEntity>> {

    int mCashType;

    public JobGetCashingItemList(int cashType){
        mCashType = cashType;
    }

    @Override
    public BaseListEntry<CashingItemEntity> start() throws IOException {
        long time = getTimestamp();
        String sign = md5(String.valueOf(mCashType) + time + getKey());
        return apis().getCashingItemList(mCashType, time, sign).execute().body();
    }

    @Override
    public void finish(BaseListEntry<CashingItemEntity> entity) throws JobException {
        super.finish(entity);
        if( null == entity){
            return;
        }
        List<CashingItemEntity> list = entity.data().getList();
        for(int i = 0; null != list && i < list.size(); i++){
            list.get(i).setType(mCashType);
        }
        SqlInsert<CashingItemEntity> operator = new SqlInsert<CashingItemEntity>(CashingItemEntity.class, Where.cashingItem(mCashType));
        operator.insert(list, 0);
    }
}
