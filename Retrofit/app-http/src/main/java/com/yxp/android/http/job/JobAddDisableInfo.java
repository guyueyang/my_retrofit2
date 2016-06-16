package com.yxp.android.http.job;

import android.os.Build;

import com.yxp.android.http.IntegralWallDataLayer;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.tools.DeviceUtils;

import java.io.IOException;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/1.
 */
public class JobAddDisableInfo extends IntegralWallJob<BaseEntity> {

    int    mSimState;
    String mValue;

    public JobAddDisableInfo(int simState, String value){
        mSimState = simState;
        mValue    = value;
    }

    @Override
    public BaseEntity start() throws IOException {
        long time = getTimestamp();
        String sign = md5(time + getKey());
        String value = mValue + getOtherInfo(mSimState);
        BaseEntity  entity =  apis().addDisableDeviceInfo(value, time, sign).execute().body();
        log(JobAddDisableInfo.class.getName(), value + " \n result = " + entity.getMessage());
        return entity;
    }

    private static String getOtherInfo(int simState){
        String mac  = " MAX : " + DeviceUtils.getMacAddress(IntegralWallDataLayer.getInstance().getContext());
        String imei = " imei : " + DeviceUtils.getIMEI(IntegralWallDataLayer.getInstance().getContext());
        String imsi = " imsi : " + DeviceUtils.getIMSI(IntegralWallDataLayer.getInstance().getContext());
        String serial = " serial : " + Build.SERIAL;
        String sim = " sim : " + simState;
        String model = " model:" + Build.MODEL;
        return mac + imei + imsi + serial + sim + model;
    }
}
