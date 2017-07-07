package com.foot.tourpal.mine.di.module;

import com.foot.tourpal.mine.business.login.LoginContract;
import com.foot.tourpal.mine.business.login.LoginModel;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZhangPu on 2017/7/6.
 */
@Module
public class LoginModule {

    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    LoginContract.View provideLoginView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    LoginContract.Model provideLoginModel(LoginModel model){
        return model;
    }
}
