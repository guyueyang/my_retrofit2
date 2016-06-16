package com.yxp.android.retrofit;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/9.
 */
public abstract class BaseColorActivity extends BaseActivity{

    protected void afterActivityCreate(Bundle savedInstanceState) {
        super.afterActivityCreate(savedInstanceState);
        setTranslucentStatus();
        IntegralWallApplication.INST.addActivity(this);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setTranslucentStatus() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mSystemBarTintManager = new SystemBarTintManager(this);
        mSystemBarTintManager.setStatusBarTintEnabled(true);
    }

}
