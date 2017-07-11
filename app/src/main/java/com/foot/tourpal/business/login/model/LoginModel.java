package com.foot.tourpal.business.login.model;

import com.foot.tourpal.base.tool.HttpRequestSign;
import com.foot.tourpal.business.login.contract.LoginContract;
import com.foot.tourpal.business.login.model.api.LoginService;
import com.foot.tourpal.business.login.model.entity.GetCodeResponse;
import com.foot.tourpal.business.login.model.entity.LoginResponse;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ZhangPu on 2017/7/6.
 */
@FragmentScope
public class LoginModel extends BaseModel implements LoginContract.Model {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<GetCodeResponse> getCode(String phone) {
        long time = System.currentTimeMillis();
        Map<String, String> requests = new LinkedHashMap<>();
        requests.put("mobile", phone);
        requests.put("timestamp", time+"");
        String sign = HttpRequestSign.getRequestSign(requests);

        Observable<GetCodeResponse> getCodeModelObservable = mRepositoryManager.
                obtainRetrofitService(LoginService.class).
                getCheckCode(phone,time,sign);

        return getCodeModelObservable;
    }

    @Override
    public Observable<LoginResponse> login(String mobile, String userId, String code) {
        long time = System.currentTimeMillis();
        Map<String, String> requests = new LinkedHashMap<>();
        requests.put("mobile", mobile);
        requests.put("userId", userId);
        requests.put("code", code);
        requests.put("timestamp", time + "");
        String sign = HttpRequestSign.getRequestSign(requests);

        Observable<LoginResponse> loginResponseObservable = mRepositoryManager.
                obtainRetrofitService(LoginService.class).
                login(userId, mobile, code, time, sign);

        return loginResponseObservable;
    }
}
