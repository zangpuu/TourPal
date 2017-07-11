package com.foot.tourpal.base.framework;

/**
 * Created by ZhangPu on 2017/7/7.
 */

public class AppCache {

    private static AppCache appCache = null;
    private boolean isLogin = false;
    private String userId = null;
    private String token = null;

    public static AppCache instance(){
        if(appCache == null){
            appCache = new AppCache();
        }
        return appCache;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
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
