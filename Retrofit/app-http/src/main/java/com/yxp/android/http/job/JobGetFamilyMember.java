package com.yxp.android.http.job;


import com.andcup.android.frame.datalayer.exception.JobException;
import com.andcup.android.frame.datalayer.sql.action.SqlInsert;
import com.yxp.android.http.IntegralWallJob;
import com.yxp.android.http.UserProvider;
import com.yxp.android.http.model.FamilyMemberEntity;
import com.yxp.android.http.model.MemberEntity;
import com.yxp.android.http.model.base.BaseEntity;
import com.yxp.android.http.where.Where;

import java.io.IOException;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/23.
 */
public class JobGetFamilyMember extends IntegralWallJob<BaseEntity<FamilyMemberEntity>> {

    int mPageIndex;
    int mPageSize;
    int mLevel;
    String mKeyword;

    public JobGetFamilyMember(int pageIndex, int pageSize, int level, String keyword){
        mPageIndex = pageIndex;
        mPageSize  = pageSize;
        mLevel     = level;
        mKeyword   = keyword;
    }

    @Override
    public BaseEntity<FamilyMemberEntity> start() throws IOException {
        String uid = UserProvider.getInstance().getUid();
        long   time= getTimestamp();
        String sign= md5(uid + time + getKey());
        return apis().getFamilyMembers(uid, mKeyword, mPageIndex + 1, mPageSize, mLevel, time, sign).execute().body();
    }

    @Override
    public void finish(BaseEntity<FamilyMemberEntity> familyEntityBaseEntity) throws JobException {
        super.finish(familyEntityBaseEntity);
        if( null == familyEntityBaseEntity){
            return;
        }
        String uid = UserProvider.getInstance().getUid();
        List<MemberEntity> familyMembers = familyEntityBaseEntity.body().getMembers();
        for(int  i = 0; null != familyMembers && i<familyMembers.size(); i++){
            familyMembers.get(i).setFamilyId(uid);
            familyMembers.get(i).setKeyword(mKeyword);
            familyMembers.get(i).setFamilyLevel(mLevel);
        }
        SqlInsert<MemberEntity> operator = new SqlInsert<>(MemberEntity.class, Where.memberLevel(uid, mLevel, mKeyword));
        operator.insert(familyMembers, mPageIndex * mPageSize);
    }
}
