package com.yxp.android.retrofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Process;

import com.andcup.android.database.activeandroid.ActiveAndroid;
import com.andcup.android.frame.AndcupApplication;
import com.yxp.android.http.IntegralWallDataLayer;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.config.ChannelConfigure;
import com.yxp.android.http.config.ConfigureProvider;
import com.yxp.android.http.config.Logs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/3.
 */

public class IntegralWallApplication extends AndcupApplication {

    public static IntegralWallApplication INST;

    private List<SoftReference<Activity>> mActivityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        INST = this;
        //init data layer.
        IntegralWallDataLayer.newInstance(INST);
        //
        //init user provider.
        UserProvider.init(INST);
        // configure module.
        ConfigureProvider.init(this);
        //monitor logout and so on.
        //clear logs.
        Logs.LOG1.clear();
        Logs.LOG2.clear();
        //初始化SDK.
    }

    public void addActivity(Activity activity){
        mActivityList.add(new SoftReference<Activity>(activity));
    }

    private void finish(){
        // finish all activity
        for(int i = 0; i< mActivityList.size(); i++){
            if(mActivityList.get(i).get() != null){
                mActivityList.get(i).get().finish();
            }
        }
        mActivityList.clear();
        // start login activity.
    }
}
