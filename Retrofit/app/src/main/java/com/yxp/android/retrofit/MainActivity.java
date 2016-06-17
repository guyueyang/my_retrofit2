package com.yxp.android.retrofit;


import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.andcup.android.frame.datalayer.sql.SqlConvert;
import com.andcup.android.frame.datalayer.sql.SqlLoader;
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
        load();
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
        call(new JobGetSplash(),new SimpleCallback<BasesEntity<AdsdkEntity>>() {
            @Override
            public void onSuccess(BasesEntity<AdsdkEntity> loginEntityBaseEntity) {
                Log.i("------",loginEntityBaseEntity.getList().get(0).getAds_id()+"");

            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }

    public void load(){
        loader(AdsdkEntity.class, (SqlLoader.CursorCallBack) new SqlLoader.CursorCallBack() {
            @Override
            public void onUpdate(Cursor cursor) {
                List<AdsdkEntity> hotTasks = SqlConvert.convert(cursor, AdsdkEntity.class, 10);
                if (null != hotTasks) {
                    Log.i("------", hotTasks.get(0).getAds_name());

                }
            }
        });
    }
}
