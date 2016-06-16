package com.yxp.android.http.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Amos on 2015/9/25.
 */
public class CompleteInfoEntity implements Serializable {

    @JsonProperty("task_remaining_days")
    int mTaskRemainingDays;
    @JsonProperty("is_can_get_lucky_money")
    int mIsCanGetLuckyMoney;
    @JsonProperty("next_task_option_id")
    int mNextTaskOptionId;

    public int getTaskRemainingDays() {
        return mTaskRemainingDays;
    }

    public int getIsCanGetLuckyMoney(){
        return  mIsCanGetLuckyMoney;
    }
}
