package com.foot.tourpal.base.framework;

/**
 * Created by ZhangPu on 2017/7/7.
 */

public class AppCache {

    private static AppCache appCache = null;
    private boolean isLogin = false;

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
}
