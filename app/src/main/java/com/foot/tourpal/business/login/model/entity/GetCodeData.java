package com.foot.tourpal.business.login.model.entity;

import com.foot.tourpal.base.bean.BusinessBean;

/**
 * Created by ZhangPu on 2017/7/11.
 */

public class GetCodeData implements BusinessBean{

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
