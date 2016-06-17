package com.yxp.android.http.map;

import com.yxp.android.http.model.Adsdks;
import com.yxp.android.http.model.AdsdkEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/29.
 */
public class AdsdkMapper implements ModelMapper<List<Adsdks>, AdsdkEntity> {

    @Override
    public List<Adsdks> map(List<AdsdkEntity> list, int number) {
        List<Adsdks> sliders = new ArrayList<>();
        if (null != list) {
            for (AdsdkEntity entity : list) {
                Adsdks slider = new Adsdks();
                slider.setAds_id(entity.getAds_id());
                slider.setAds_name(entity.getAds_name());
                slider.setBegin_date(entity.getBegin_date());
                slider.setEnd_date(entity.getEnd_date());
                slider.setHeight(entity.getHeight());
                slider.setImg_url(entity.getImg_url());
                slider.setLpg_url(entity.getLpg_url());
                slider.setPeriod_from(entity.getPeriod_from());
                slider.setWidth(entity.getWidth());
                slider.setPeriod_to(entity.getPeriod_to());
                sliders.add(slider);
            }
        }
        return sliders;
    }
}
