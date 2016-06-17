package com.yxp.android.http.job;

import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.map.AdsdkMapper;
import com.yxp.android.http.model.Adsdks;
import com.yxp.android.http.model.AdsdkEntity;
import com.yxp.android.http.model.DailyTaskEntity;
import com.yxp.android.http.model.base.BasesEntity;
import com.yxp.android.http.tools.MD5;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/15.
 */
public class JobGetSplash extends IntegralWallJob<BasesEntity<AdsdkEntity>> {

    @Override
    public BasesEntity<AdsdkEntity> start() throws IOException {
        String appid="APP-20160523004550";
        String appkey="C5C8D2EB39E5A325915C5A0DB075EF75";
        Long time=getTimestamp();
        String uuid=MD5.toMd5(getUniqueId()+time);
        String tiy=MD5.toMd5(uuid+"100"+"100"+time+appid+appkey);
        BasesEntity<AdsdkEntity> entry = apis().splash(uuid,appid,"100","100",time+"",tiy).execute().body();
        return entry;
    }

    @Override
    public void finish(BasesEntity<AdsdkEntity> data) throws JobException {
        super.finish(data);
//        List<Adsdks> list = new AdsdkMapper().map(data.getList(), 0);
//        new SqlInsert(Adsdks.class).insert(list, 0);
        SqlInsert<AdsdkEntity> dataOperator = new SqlInsert<>(AdsdkEntity.class);
        dataOperator.insert(data.getList(), 0);
    }
}
