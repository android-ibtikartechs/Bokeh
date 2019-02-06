package com.am.app.bouqeh.ui.activities.about_application;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface AboutApplicationMvpView extends MvpView {

    void populateContent(String Content);

    void showErrorConnectionView();
    void showLoadingView();
    void showContent();

}
