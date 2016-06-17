package com.yxp.android.http.task;

import com.andcup.android.frame.datalayer.CallBack;
import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.job.Job;
import com.andcup.android.frame.datalayer.job.JobEntity;
import com.yxp.android.http.IntegralWallDataLayer;
import com.yxp.android.http.map.ModelMapper;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.model.base.BaseListEntry;
import com.yxp.android.http.tools.MD5;

import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/25.
 */
public abstract class Task<T extends JobEntity> implements Job<T> {

    public static class OnResult<T> extends CallBack<T> {

        OnResult    mDelegate;
        ModelMapper mMapper;

        public OnResult(){

        }

        public OnResult(OnResult delegate, ModelMapper mapper){
            mDelegate = delegate;
            mMapper   = mapper;
        }

        public OnResult getDelegate() {
            return mDelegate;
        }

        @Override
        public void onSuccess(T t) {
            // 对象转换.
            if(t instanceof BaseEntity){
                Object object = ((BaseEntity) t).body();
                if(null != object && object instanceof List){
                    List list = (List)object ;
                    mDelegate.onSuccess(mMapper.map(list, null == list? 0 : list.size()));
                }else{
                    mDelegate.onSuccess(t);
                }
            }
        }

        @Override
        public void onFinish() {
            if( null != mDelegate){
                mDelegate.onFinish();
            }
        }
        @Override
        public void onError(Throwable e) {
            if( null != mDelegate){
                mDelegate.onError(e);
            }
        }
    }
    protected  <Api> Api api(){
        return (Api) IntegralWallDataLayer.getInstance().getRepository().from();
    }


    protected int getTime(){
        return (int) (System.currentTimeMillis() / 1000);
    }

    protected String lastUpdate(){
        return "0";
    }

    @Override
    public void finish(T t) throws JobException {

    }
}
