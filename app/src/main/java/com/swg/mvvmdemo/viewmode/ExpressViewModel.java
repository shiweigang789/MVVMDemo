package com.swg.mvvmdemo.viewmode;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.swg.mvvmdemo.bean.ExpressInfo;
import com.swg.mvvmdemo.databinding.ActivityMainBinding;
import com.swg.mvvmdemo.manager.DataManager;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * ViewModel层
 * Created by swg on 2017/11/10.
 */

public class ExpressViewModel extends BaseViewModel {

    public ExpressInfo expressInfo;
    private DataManager dataManager;

    // 是否显示Loading
    public final ObservableBoolean isShowLoading = new ObservableBoolean();
    // 错误信息
    public final ObservableField<String> errorMessage = new ObservableField<>();

    public ExpressViewModel(LifecycleProvider<ActivityEvent> provider, ActivityMainBinding binding) {
        super(provider);
        expressInfo = new ExpressInfo();
        binding.setExpressViewModel(this);
        dataManager = DataManager.getInstance();
    }

    public void getExpressInfo(String type, String positionId) {
        isShowLoading.set(true);
        dataManager.getExpressInfo(type, positionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<ExpressInfo>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new DefaultObserver<ExpressInfo>() {
                    @Override
                    public void onNext(ExpressInfo expressInfo) {
                        ExpressViewModel.this.expressInfo.setExpressInfo(expressInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMessage.set(e.getMessage());
                        isShowLoading.set(false);
                    }

                    @Override
                    public void onComplete() {
                        isShowLoading.set(false);
                    }
                });
    }

}
