package com.yxp.android.http;

import android.content.Context;
import com.andcup.android.frame.datalayer.Repository;
import com.andcup.android.frame.datalayer.RepositoryFactory;
import com.yxp.android.http.config.PlatformConfigure;
import com.yxp.android.http.intercepts.HeaderIntercept;
import com.yxp.android.http.intercepts.HeadersIntercept;
import com.yxp.android.http.intercepts.LoggerIntercept;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/9.
 */
public class IntegralWallDataLayer{

    private static IntegralWallDataLayer INST;
    /**
     * use platform.
     * */
    private static PlatformConfigure PLATFORM = PlatformConfigure.TEST;

    private Repository<IntegralWallApis> mRepository;
    private Context mContext;

    public static IntegralWallDataLayer newInstance(Context context){
        if( null == INST){
            INST = new IntegralWallDataLayer(context);
        }
        return INST;
    }

    public static IntegralWallDataLayer getInstance(){
        return INST;
    }

    protected IntegralWallDataLayer(Context context){
        mContext = context;
        // init repository
        RepositoryFactory.RETROFIT.withUrl(PLATFORM.getPlatformUrl());
        // init intercept
        RepositoryFactory.RETROFIT.addInterceptor(new HeadersIntercept());
        // log handler
        LoggerIntercept loggerIntercept = new LoggerIntercept();
        loggerIntercept.setLevel(LoggerIntercept.Level.BODY);
        RepositoryFactory.RETROFIT.addInterceptor(loggerIntercept);

        mRepository = RepositoryFactory.RETROFIT.create(IntegralWallApis.class);
    }

    public Repository<IntegralWallApis> getRepository(){
        return mRepository;
    }

    public Context getContext(){
        return mContext;
    }

    public PlatformConfigure getPlatformConfigure(){
        return PLATFORM;
    }
}
