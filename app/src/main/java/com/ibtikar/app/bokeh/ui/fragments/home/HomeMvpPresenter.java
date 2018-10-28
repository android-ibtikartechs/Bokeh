package com.ibtikar.app.bokeh.ui.fragments.home;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {
    void loadHomeData(double latitude, double longitude);


}
