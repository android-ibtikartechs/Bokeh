package com.am.app.bouqeh.ui.activities.about_application;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface AboutApplicationMvpPresenter <V extends AboutApplicationMvpView> extends MvpPresenter<V> {
    void loadContent(int contentTypeFlag);
}
