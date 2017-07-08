package com.foot.tourpal.mine.business.login;

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
}
