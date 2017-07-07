package com.foot.tourpal.mine.di.component;

import com.foot.tourpal.mine.business.login.LoginFragment;
import com.foot.tourpal.mine.di.module.LoginModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by ZhangPu on 2017/7/6.
 */
@FragmentScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginFragment fragment);
}
