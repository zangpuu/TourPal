package com.foot.tourpal.business.login.model.api;

import com.foot.tourpal.business.login.model.entity.GetCodeResponse;
import com.foot.tourpal.business.login.model.entity.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ZhangPu on 2017/7/4.
 */

public interface LoginService {
    @GET("LoginController/loginBySMS")
    Observable<GetCodeResponse> getCheckCode(@Query("mobile") String mobile,
                                             @Query("timestamp") long now,
                                             @Query("sign") String sign);

    @GET("LoginController/loginSmsConfirm")
    Observable<LoginResponse> login(@Query("userId") String userId,
                                    @Query("mobile") String mobile,
                                    @Query("code") String code,
                                    @Query("timestamp") long now,
                                    @Query("sign") String sign);
}
