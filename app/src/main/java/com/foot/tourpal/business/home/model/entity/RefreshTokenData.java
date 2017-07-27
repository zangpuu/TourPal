package com.foot.tourpal.business.home.model.entity;

import com.foot.tourpal.base.bean.BusinessBean;

/**
 * Created by ZhangPu on 2017/7/27.
 */

public class RefreshTokenData implements BusinessBean {
    private int availableDuration = -1;
    private long id;
    private int isAvailable;
    private String lastUpdateTime;
    private String token;
    private String updateTime;
    private String userId;

    public int getAvailableDuration() {
        return availableDuration;
    }

    public void setAvailableDuration(int availableDuration) {
        this.availableDuration = availableDuration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
