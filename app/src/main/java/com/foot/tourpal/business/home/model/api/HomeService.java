package com.foot.tourpal.business.home.model.api;

import com.foot.tourpal.business.home.model.entity.RefreshTokenResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ZhangPu on 2017/7/27.
 */

public interface HomeService {

    @GET("SysUserController/updateToken")
    Observable<RefreshTokenResponse> refreshToken(@Query("token") String token,
                                                  @Query("mobile") String mobile,
                                                  @Query("userId") String userId,
                                                  @Query("timestamp") long now,
                                                  @Query("sign") String sign);
}
