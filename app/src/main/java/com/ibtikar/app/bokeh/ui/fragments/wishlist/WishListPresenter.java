package com.ibtikar.app.bokeh.ui.fragments.wishlist;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class WishListPresenter <V extends WishListMvpView> extends BasePresenter<V> implements WishListMvpPresenter<V>{
    public WishListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadFirstPage() {

    }

    @Override
    public void loadNextPage(int currentPage) {

    }
}
