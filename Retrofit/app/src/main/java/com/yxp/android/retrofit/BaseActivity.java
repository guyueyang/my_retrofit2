package com.yxp.android.retrofit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.andcup.android.frame.view.RxAppCompatActivity;
import com.andcup.android.frame.view.navigator.ActivityNavigator;
import com.andcup.android.frame.view.navigator.FragmentNavigator;
import com.readystatesoftware.systembartint.SystemBarTintManager;
/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/9.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements ColorBar{

    protected SystemBarTintManager mSystemBarTintManager;
    private boolean mAnotherLogin = false;

    protected void go(Class<? extends Activity> target){
        ActivityNavigator navigator = new ActivityNavigator(this);
        navigator.to(target).go().finish();
    }

    protected void go(Class<? extends Fragment> target, int containerId){
        FragmentNavigator navigator = new FragmentNavigator(getSupportFragmentManager());
        navigator.at(containerId).to(target).go();
    }

    protected void finish(Class<? extends Fragment> target){
        FragmentNavigator navigator = new FragmentNavigator(getSupportFragmentManager());
        navigator.to(target).finish();
    }

    protected void go2(Class<? extends Fragment> target, Bundle value){
        IntegralWallActivity.go(this, target, value);
    }

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
        mSystemBarTintManager.setTintColor(Color.TRANSPARENT);
    }

    @Override
    public void setBarColor(int color) {
        if( null != mSystemBarTintManager){
            mSystemBarTintManager.setTintColor(getResources().getColor(color));
        }
    }

    protected Point getWindowSize(){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        return new Point(dm.widthPixels, dm.heightPixels);
    }
}
