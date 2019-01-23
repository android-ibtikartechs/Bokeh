package com.am.app.bouqeh.ui.fragments.search;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface SearchMvpPresenter <V extends SearchMvpView> extends MvpPresenter<V> {
    void loadSearchResult(String keyword);
}
