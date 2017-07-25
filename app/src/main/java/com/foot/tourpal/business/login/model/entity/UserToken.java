package com.foot.tourpal.business.login.model.entity;

import com.foot.tourpal.base.bean.BusinessBean;

/**
 * Created by ZhangPu on 2017/7/10.
 */

public class UserToken implements BusinessBean {
    private String id;
    private String userId;
    private String token;
    private String lastUpdateTime;
    private String updateTime;
    private String isAvailable;
    private String avaiableDuration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getAvaiableDuration() {
        return avaiableDuration;
    }

    public void setAvaiableDuration(String avaiableDuration) {
        this.avaiableDuration = avaiableDuration;
    }
}
