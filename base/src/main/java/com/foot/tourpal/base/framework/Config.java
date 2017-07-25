package com.foot.tourpal.base.framework;

/**
 * Created by ZhangPu on 2017/7/13.
 */

/**
 * APP全局Config
 */
public class Config {
    private static int ENV = Environment.PRODUCT;

    /**
     * 获得环境
     *
     * @return
     */
    public static int getENV() {
        return ENV;
    }

    /**
     * 切换环境
     *
     * @param env
     */
    public static void changeEnv(int env, boolean notifyThird) {
        ENV = env;
        if (notifyThird) {
        }
    }

}
