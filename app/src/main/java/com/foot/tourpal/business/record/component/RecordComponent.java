package com.foot.tourpal.business.record.component;

import com.foot.tourpal.business.record.RecordFragment;
import com.foot.tourpal.business.record.module.RecordModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by ZhangPu on 2017/7/13.
 */
@FragmentScope
@Component(modules = RecordModule.class, dependencies = AppComponent.class)
public interface RecordComponent {
    void inject(RecordFragment fragment);
}
