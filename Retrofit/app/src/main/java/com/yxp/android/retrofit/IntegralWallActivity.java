package com.yxp.android.retrofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.andcup.android.frame.view.annotations.Restore;
import com.andcup.android.frame.view.navigator.ActivityNavigator;
import com.andcup.android.frame.view.navigator.FragmentNavigator;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/15.
 */
public class IntegralWallActivity extends BaseColorActivity implements BaseActionBar {
    @Restore(value = BundleKey.TARGET_CLAZZ)
    Class<?> mClazz;
    @Bind(R.id.include_title_bar)
    View     mTitleBar;
    @Bind(R.id.tv_title)
    TextView mTvTitle;

    public static void go(Activity activity, Class<? extends Fragment> clazz, Bundle value){
        ActivityNavigator activityNavigator = new ActivityNavigator(activity);
        if( null == value){
            value = new Bundle();
        }
        value.putSerializable(BundleKey.TARGET_CLAZZ, clazz);
        activityNavigator.to(IntegralWallActivity.class).with(value).go();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void afterActivityCreate(Bundle savedInstanceState) {
        super.afterActivityCreate(savedInstanceState);
        mTitleBar.setVisibility(View.GONE);
        FragmentNavigator fragmentNavigator = new FragmentNavigator(getSupportFragmentManager());
        fragmentNavigator.at(R.id.fr_container).to(mClazz).with(getIntent().getExtras()).go();

    }

    @OnClick(R.id.iv_back)
    public void onBack(){
        finish();
    }

    public void setTitle(String title){
        mTitleBar.setVisibility(TextUtils.isEmpty(title) ? View.GONE: View.VISIBLE);
        mTvTitle.setText(title);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for(int i = 0; null != fragmentList && i< fragmentList.size(); i++){
            fragmentList.get(i).onActivityResult(requestCode, resultCode, data);
        }
    }
}
