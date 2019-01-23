package com.am.app.bouqeh.ui.activities.splash;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
