package com.yxp.android.http.job;

import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.AvatarEntity;
import com.yxp.android.http.model.ScoreDetailEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.where.Where;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/24.
 */
public class JobUpload extends IntegralWallJob<BaseEntity<AvatarEntity>> {

    public  JobUpload(){}

    @Override
    public BaseEntity<AvatarEntity> start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long   time=getTimestamp();
        String sgin=md5(uid+time+getKey());
        return null;
    }
}
