package com.foot.tourpal.business.login.model.entity;

import com.foot.tourpal.base.bean.BusinessBean;

/**
 * Created by ZhangPu on 2017/7/10.
 */

public class LoginData implements BusinessBean {
    private String id;
    private String userEmail;
    private String userId; //用户ID
    private String userName; //用户名
    private String userPassword; //用户密码
    private String salt;  //?
    private String userQq;
    private String userWeixin;
    private String userWeibo;
    private String userTel;
    private String userType; //1：短期用户（非活跃用户）2：长期用户（活跃用户），0 新注册用户(未设置登陆密码，若需要通过密码登陆，则客户端需要先请求设置密码)
    private String userAvatar; //头像URL
    private String userBirthDay;
    private String userSex; //1男，2女，3保密
    private String createTime; //注册时间
    private String lastLoginTime; //上次登录时间
    private String loginTime; //本次登录时间
    private String travelTimes; //行程次数
    private String viewTimes; //浏览次数
    private String collectionNum; //收藏路线个数
    private String locked; //1锁定，0未锁定，2未激活
    private UserToken sysUserToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getUserWeixin() {
        return userWeixin;
    }

    public void setUserWeixin(String userWeixin) {
        this.userWeixin = userWeixin;
    }

    public String getUserWeibo() {
        return userWeibo;
    }

    public void setUserWeibo(String userWeibo) {
        this.userWeibo = userWeibo;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserBirthDay() {
        return userBirthDay;
    }

    public void setUserBirthDay(String userBirthDay) {
        this.userBirthDay = userBirthDay;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getTravelTimes() {
        return travelTimes;
    }

    public void setTravelTimes(String travelTimes) {
        this.travelTimes = travelTimes;
    }

    public String getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(String viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(String collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public UserToken getSysUserToken() {
        return sysUserToken;
    }

    public void setSysUserToken(UserToken sysUserToken) {
        this.sysUserToken = sysUserToken;
    }
}
