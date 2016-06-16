package com.yxp.android.http.job;


import com.andcup.android.database.activeandroid.query.Update;
import com.andcup.android.frame.datalayer.exception.JobException;
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
public class JobGetMsgDetail extends IntegralWallJob<BaseEntity<MsgEntity>> {

    String mMsgId;

    public JobGetMsgDetail(String msgId){
        mMsgId = msgId;
    }

    @Override
    public BaseEntity<MsgEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long time = getTimestamp();
        String sign = md5(uid + mMsgId + time + getKey());
        return apis().getMsgDetail(uid, mMsgId, time, sign).execute().body();
    }

    @Override
    public void finish(BaseEntity<MsgEntity> msgEntityBaseListEntry) throws JobException {
        super.finish(msgEntityBaseListEntry);
        new Update(MsgEntity.class)
                .set(DbColumn.READED + "=" + MsgEntity.STATE_READ_YES)
                .where(DbColumn.MSG_ID + "=" +  mMsgId)
                .execute();
    }
}
