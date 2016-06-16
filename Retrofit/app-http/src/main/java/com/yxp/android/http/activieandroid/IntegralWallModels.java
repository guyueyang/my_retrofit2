package com.yxp.android.http.activieandroid;

import com.andcup.android.database.activeandroid.Model;
import com.andcup.android.frame.datalayer.provider.ActiveAndroidProvider;
import com.andcup.lib.download.data.model.DownloadResource;
import com.andcup.lib.download.data.model.DownloadTask;
import com.andcup.lib.download.data.model.ResourceRepository;
import com.yxp.android.http.model.AdsdkEntity;
import com.yxp.android.http.model.CashingItemEntity;
import com.yxp.android.http.model.DailyTaskEntity;
import com.yxp.android.http.model.ExperienceEntity;
import com.yxp.android.http.model.FamilyInfoEntity;
import com.yxp.android.http.model.FamilyMemberEntity;
import com.yxp.android.http.model.FirstLandingTaskEntity;
import com.yxp.android.http.model.HotTaskEntity;
import com.yxp.android.http.model.LoginEntity;
import com.yxp.android.http.model.MemberEntity;
import com.yxp.android.http.model.MsgEntity;
import com.yxp.android.http.model.MyTaskQuickEntity;
import com.yxp.android.http.model.RaidersEntity;
import com.yxp.android.http.model.RankEntity;
import com.yxp.android.http.model.RewardEntity;
import com.yxp.android.http.model.ScoreDetailEntity;
import com.yxp.android.http.model.ShareInfoEntity;
import com.yxp.android.http.model.SignedDateEntity;
import com.yxp.android.http.model.TaskBusinessEntity;
import com.yxp.android.http.model.TaskDetailEntity;
import com.yxp.android.http.model.TaskEntity;
import com.yxp.android.http.model.TaskFeatureEntity;
import com.yxp.android.http.model.TaskLimitEntity;
import com.yxp.android.http.model.TaskQuickEntity;
import com.yxp.android.http.model.TaskUnionPlatformEntity;
import com.yxp.android.http.model.UserInfoEntity;

import java.util.Arrays;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/11.
 */
public class IntegralWallModels implements ActiveAndroidProvider.ModelApis {
    @Override
    public List<Class<? extends Model>> list() {
        return Arrays.asList(MODELS);
    }

    public static Class<? extends Model>[] MODELS = new Class[]{
            DownloadResource.class,
            DownloadTask.class,
            ResourceRepository.class,

            CashingItemEntity.class,
            DailyTaskEntity.class,
            ExperienceEntity.class,
            FamilyInfoEntity.class,
            FamilyMemberEntity.class,
            FirstLandingTaskEntity.class,
            HotTaskEntity.class,
            LoginEntity.class,
            MemberEntity.class,
            MsgEntity.class,
            MyTaskQuickEntity.class,
            TaskEntity.class,
            RaidersEntity.class,
            RankEntity.class,
            RewardEntity.class,
            ScoreDetailEntity.class,
            ShareInfoEntity.class,
            SignedDateEntity.class,
            TaskBusinessEntity.class,
            TaskDetailEntity.class,
            TaskEntity.class,
            TaskFeatureEntity.class,
            TaskQuickEntity.class,
            TaskUnionPlatformEntity.class,
            UserInfoEntity.class,
            TaskDetailEntity.TaskOption.class,
            TaskLimitEntity.class,
            AdsdkEntity.class
    };
}
