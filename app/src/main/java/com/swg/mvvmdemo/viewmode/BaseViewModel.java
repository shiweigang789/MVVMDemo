package com.swg.mvvmdemo.viewmode;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * ViewModel基类
 * Created by swg on 2017/11/10.
 */

public class BaseViewModel {

    private LifecycleProvider<ActivityEvent> provider;

    public BaseViewModel(LifecycleProvider<ActivityEvent> provider) {
        this.provider = provider;
    }

    public LifecycleProvider<ActivityEvent> getProvider() {
        return provider;
    }

}
