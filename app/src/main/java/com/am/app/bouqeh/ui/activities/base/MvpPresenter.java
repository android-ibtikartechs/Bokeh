package com.am.app.bouqeh.ui.activities.base;

public interface MvpPresenter <V extends MvpView>{
    void onAttach(V mvpView);
}
