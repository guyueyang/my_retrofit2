package com.yxp.android.http.config;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxp.android.http.tools.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/29.
 */
public abstract class ConfigureProvider {

    interface Provider{
        void build(Context context);
    }

    public static void init(Context context){
        ChannelConfigure.gen().build(context);
    }

    protected ConfigureProvider(){

    }

    protected <T> T build(Context context, String file, Class<T> tClass){
        try {
            InputStream inputStream = context.getAssets().open(file);
            String pc = IOUtils.readToString(inputStream);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(pc, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
