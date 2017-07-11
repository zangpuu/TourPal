package com.foot.tourpal.di.component;

import com.foot.tourpal.business.login.ui.LoginFragment;
import com.foot.tourpal.di.module.LoginModule;
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
