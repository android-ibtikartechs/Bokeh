package com.am.app.bouqeh.ui.activities.products_list;

import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface ProductsListMvpView extends MvpView {
    void setLastPageTrue();
    void addMoreToAdapter(List<ModelProductItem> list);
    void addLoadingFooter();
    void removeLoadingFooter();
    void showRetryAdapter();
    void setIsLoadingFalse();

    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
    void showEmptyView(int categoryOrShop);

}
