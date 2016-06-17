package com.yxp.android.http.model.db;

import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.model.AdsdkEntity;
import com.yxp.android.http.where.Where;

import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/29.
 */
public class DataManager {

    public static void slider(List<AdsdkEntity> slider, int start){
        new SqlInsert(AdsdkEntity.class, Where.user()).insert(slider, start);
    }

}
