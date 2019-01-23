package com.am.app.bouqeh.ui.fragments.wishlist;

import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.ui.activities.base.MvpView;

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
