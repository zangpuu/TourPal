package com.foot.tourpal.business.home.model;

import com.foot.tourpal.base.tool.HttpRequestSign;
import com.foot.tourpal.business.home.contract.HomeContarct;
import com.foot.tourpal.business.home.model.api.HomeService;
import com.foot.tourpal.business.home.model.entity.RefreshTokenResponse;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ZhangPu on 2017/7/11.
 */

@ActivityScope
public class HomeModel extends BaseModel implements HomeContarct.Model {
    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<RefreshTokenResponse> refreshToken(String token, String mobile, String userId) {
        long time = System.currentTimeMillis();
        Map<String, String> requests = new LinkedHashMap<>();
        requests.put("mobile", mobile);
        requests.put("userId", userId);
        requests.put("token", token);
        requests.put("timestamp", time + "");
        String sign = HttpRequestSign.getRequestSign(requests);

        Observable<RefreshTokenResponse> refreshTokenResponseObservable = mRepositoryManager.
                obtainRetrofitService(HomeService.class).
                refreshToken(token, mobile, userId, time, sign);
        return refreshTokenResponseObservable;
    }
}
