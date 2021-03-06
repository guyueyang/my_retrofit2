package com.yxp.android.http.intercepts;

import android.os.Build;
import android.text.TextUtils;

import com.yxp.android.http.Fields;
import com.yxp.android.http.IntegralWallDataLayer;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.config.ChannelConfigure;
import com.yxp.android.http.model.AdsdkEntity;
import com.yxp.android.http.tools.DeviceUtils;
import com.yxp.android.http.tools.MD5;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/19.
 */
public class HeadersIntercept implements Interceptor{

    public static String getUuid() {
        String oldUid = DeviceUtils.getUniqueId();
        //imei
        String imei = DeviceUtils.getIMEI(IntegralWallDataLayer.getInstance().getContext());
        if(TextUtils.isEmpty(imei)){
            imei = "imei is null";
        }
        return MD5.toMd5(oldUid + imei);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        // android type.
        request.addHeader(Fields.AD_DEVICETYPE, "1");
        // old uid.
        String oldUid = DeviceUtils.getUniqueId();
        request.addHeader(Fields.AD_UUID, oldUid);

        // mac
        String mac = DeviceUtils.getMacAddress(IntegralWallDataLayer.getInstance().getContext());
        try {
            request.addHeader(Fields.AD_MAC, mac);
        } catch (Exception e) {
            mac = "mac is null";
            request.addHeader(Fields.AD_MAC, mac);
        }
        //imei
        String imei = DeviceUtils.getIMEI(IntegralWallDataLayer.getInstance().getContext());

        try{
            request.addHeader(Fields.AD_IMEI, imei);
        }catch (Exception e){
            imei = "imei is null";
            request.addHeader(Fields.AD_IMEI, imei);
        }
        //imsi
        String imsi = DeviceUtils.getIMSI(IntegralWallDataLayer.getInstance().getContext());

        try{
            request.addHeader(Fields.AD_IMSI, imsi);
        }catch (Exception e){
            imsi = "imsi is null";
            request.addHeader(Fields.AD_IMSI, imsi);
        }
        // uuid2
        String newUid = MD5.toMd5(oldUid + imei + imei + imsi);
        request.addHeader(Fields.AD_UUID, newUid);
        // uuid
        String newUid2 = MD5.toMd5(oldUid + imei);
        request.addHeader(Fields.AD_UUID, newUid2);

        request.addHeader("channel", ChannelConfigure.getInstance().getChannel());
        request.addHeader(Fields.AD_SDKVERSION, DeviceUtils.getVerName(IntegralWallDataLayer.getInstance().getContext()));
        request.addHeader("build_version", String.valueOf(DeviceUtils.getVerCode(IntegralWallDataLayer.getInstance().getContext())));
        request.addHeader(Fields.AD_OSV, Build.VERSION.RELEASE);
        request.addHeader(Fields.AD_DEVICEMODEL, Build.MODEL);
        if (!TextUtils.isEmpty(UserProvider.getInstance().getAccessToken())) {
            request.addHeader("access_token", UserProvider.getInstance().getAccessToken());
        }
        return chain.proceed(request.build());
    }
}
