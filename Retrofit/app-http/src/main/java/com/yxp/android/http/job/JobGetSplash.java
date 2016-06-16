package com.yxp.android.http.job;

import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.exception.AccountDisableException;
import com.yxp.android.http.model.AdsdkEntity;
import com.yxp.android.http.model.HotTaskEntity;
import com.yxp.android.http.model.TaskDetailEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.model.base.BasesEntity;
import com.yxp.android.http.model.base.BasesListEntry;
import com.yxp.android.http.tools.MD5;
import com.yxp.android.http.where.Where;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/15.
 */
public class JobGetSplash extends IntegralWallJob<BasesEntity<List<AdsdkEntity>>> {

    @Override
    public BasesEntity<List<AdsdkEntity>> start() throws IOException {
        String appid="APP-20160523004550";
        String appkey="C5C8D2EB39E5A325915C5A0DB075EF75";
        Long time=getTimestamp();
        String uuid=MD5.toMd5(getUniqueId()+time);
        String tiy=MD5.toMd5(uuid+"100"+"100"+time+appid+appkey);
        BasesEntity<List<AdsdkEntity>> entry = apis().splash(uuid,appid,"100","100",time+"",tiy).execute().body();
        return entry;
    }

    @Override
    public void finish(BasesEntity<List<AdsdkEntity>> data) throws JobException {
        super.finish(data);
//        SqlInsert<BasesEntity> dataOperator = new SqlInsert<>(BasesEntity.class);
//        dataOperator.insert(data.body());
    }
}
