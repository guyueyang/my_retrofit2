package com.yxp.android.http.job;


import com.andcup.android.frame.datalayer.exception.JobException;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.AvatarEntity;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/3/28.
 */
public class JobEditAvatar extends IntegralWallJob<BaseEntity<AvatarEntity>> {

    String mAvatarPath;

    public JobEditAvatar(String avatarPath){
        mAvatarPath=avatarPath;
    }

    @Override
    public BaseEntity<AvatarEntity> start() throws IOException {
        String uid= UserProvider.getInstance().getUid();
        Long time=getTimestamp();
        String sign=md5(uid + time + getKey());
        File imageFile = new File(mAvatarPath);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        RequestBody rbSign=
                RequestBody.create(MediaType.parse("multipart/form-data"), sign);
        return apis().editAvatar(Integer.parseInt(uid),requestBody,time,rbSign).execute().body();
    }

    @Override
    public void finish(BaseEntity<AvatarEntity> avatarEntityBaseEntity) throws JobException {
        super.finish(avatarEntityBaseEntity);
        UserProvider.getInstance().getUserInfo().setAvatar(avatarEntityBaseEntity.body().getAvatar());
        UserProvider.User.update(UserProvider.getInstance().getUserInfo());
    }
}
