package com.yxp.android.http.job;


import com.andcup.android.database.activeandroid.query.Update;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.MsgEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.model.db.DbColumn;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/25.
 */
public class JobReadMessage extends IntegralWallJob<BaseEntity> {

    MsgEntity mMsgEntity;

    public JobReadMessage(MsgEntity msgEntity){
        mMsgEntity = msgEntity;
    }

    @Override
    public BaseEntity start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long time = getTimestamp();
        String sign = md5(uid + mMsgEntity.getMsgId() + time + getKey());
        updateMsgEntity();
        return apis().readMsg(uid, mMsgEntity.getMsgId(), time, sign).execute().body();
    }

    private void updateMsgEntity(){
        new Update(MsgEntity.class)
                .set(DbColumn.READED + "=" + MsgEntity.STATE_READ_YES)
                .where(DbColumn.MSG_ID + "=" +  mMsgEntity.getMsgId())
                .execute();
    }
}
