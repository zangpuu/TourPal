package com.foot.tourpal.business.mine.component;

import com.foot.tourpal.business.mine.module.MineModule;
import com.foot.tourpal.business.mine.ui.MineFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by ZhangPu on 2017/7/13.
 */
@FragmentScope
@Component(modules = MineModule.class, dependencies = AppComponent.class)
public interface MineComponent {
    void inject(MineFragment fragment);
}
