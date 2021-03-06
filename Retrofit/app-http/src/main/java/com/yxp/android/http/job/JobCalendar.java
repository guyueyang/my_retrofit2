package com.yxp.android.http.job;


import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.CalendarEntity;
import com.yxp.android.http.model.base.BaseEntity;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/21.
 */
public class JobCalendar extends IntegralWallJob<BaseEntity<CalendarEntity>> {
    @Override
    public BaseEntity<CalendarEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long   time = getTimestamp();
        String sign = md5(uid + time + getKey());
        return apis().signIn(uid, time, sign).execute().body();
    }
}
