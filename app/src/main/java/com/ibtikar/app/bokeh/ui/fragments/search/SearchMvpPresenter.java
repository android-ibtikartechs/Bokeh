package com.ibtikar.app.bokeh.ui.fragments.search;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface SearchMvpPresenter <V extends SearchMvpView> extends MvpPresenter<V> {
    void loadSearchResult(String keyword);
}
