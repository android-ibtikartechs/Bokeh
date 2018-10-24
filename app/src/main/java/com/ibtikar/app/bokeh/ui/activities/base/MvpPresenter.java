package com.ibtikar.app.bokeh.ui.activities.base;

public interface MvpPresenter <V extends MvpView>{
    void onAttach(V mvpView);
}
