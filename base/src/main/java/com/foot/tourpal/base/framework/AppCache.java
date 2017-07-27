package com.foot.tourpal.base.framework;

/**
 * Created by ZhangPu on 2017/7/7.
 */

public class AppCache {

    private static AppCache appCache = null;
    private boolean isLogined = false;
    public final String KEY_USER_ID = "userId";
    public final String KEY_TOKEN = "token";
    public final String KEY_MOBILE = "mobile";
    public final String KEY_IS_RECORDING = "isRecording";

    public static AppCache instance() {
        if (appCache == null) {
            appCache = new AppCache();
        }
        return appCache;
    }

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

}
