package com.ibtikar.app.bokeh.ui.activities.splash;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
