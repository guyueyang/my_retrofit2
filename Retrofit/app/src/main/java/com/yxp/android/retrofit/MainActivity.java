package com.yxp.android.retrofit;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.yxp.android.http.SimpleCallback;
import com.yxp.android.http.job.JobGetSplash;
import com.yxp.android.http.job.JobUserLogin;
import com.yxp.android.http.model.AdsdkEntity;
import com.yxp.android.http.model.LoginEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.model.base.BasesEntity;
import com.yxp.android.http.model.base.BasesListEntry;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    //实例Button
    @Bind(R.id.btn_ces)
    Button mBtnId;
    @Override
    protected void afterActivityCreate(Bundle savedInstanceState) {
        super.afterActivityCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 按钮点击
     */
    @OnClick(R.id.btn_ces)
    public  void onCes(View view){
        String username="18006931194";
        String password="";
        call(new JobGetSplash(),new SimpleCallback<BasesEntity<List<AdsdkEntity>>>() {
            @Override
            public void onSuccess(BasesEntity<List<AdsdkEntity>> loginEntityBaseEntity) {
                Log.i("------",loginEntityBaseEntity.getMessage());
                Log.i("------",loginEntityBaseEntity.body().get(0).getAds_id());

            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
