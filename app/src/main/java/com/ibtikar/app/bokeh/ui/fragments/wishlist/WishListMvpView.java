package com.ibtikar.app.bokeh.ui.fragments.wishlist;

import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface WishListMvpView extends MvpView {

    void setLastPageTrue();
    void addMoreToAdapter(List<ModelProductItem> list);
    void addLoadingFooter();
    void removeLoadingFooter();
    void showRetryAdapter();
    void setIsLoadingFalse();

    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
    void showEmptyView();

}
