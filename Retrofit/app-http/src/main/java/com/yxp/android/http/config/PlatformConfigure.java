package com.yxp.android.http.config;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/3/31.
 */
public interface PlatformConfigure {

    String getPlatformUrl();

    boolean isDebug();

    boolean isCheck();

    PlatformConfigure DEVELOPER = new PlatformConfigure() {
        @Override
        public String getPlatformUrl() {
            return "http://test.yiclock.com";
        }

        @Override
        public boolean isDebug() {
            return true;
        }

        @Override
        public boolean isCheck() {
            return true;
        }
    };

    PlatformConfigure TEST = new PlatformConfigure() {
        @Override
        public String getPlatformUrl() {
            return "http://test.yiclock.com";
        }

        @Override
        public boolean isDebug() {
            return true;
        }

        @Override
        public boolean isCheck() {
            return false;
        }
    };

    PlatformConfigure RELEASE = new PlatformConfigure() {
        @Override
        public String getPlatformUrl() {
            return "http://test.yiclock.com";
        }

        @Override
        public boolean isDebug() {
            return false;
        }

        @Override
        public boolean isCheck() {
            return false;
        }
    };
}
