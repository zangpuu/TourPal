package com.foot.tourpal.base.framework;

/**
 * Created by ZhangPu on 2017/7/7.
 */

public class AppCache {

    private static AppCache appCache = null;
    private boolean isLogined = false;
    private String userId = null;
    private String token = null;

    public static AppCache instance(){
        if(appCache == null){
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
