package com.ibtikar.app.bokeh.ui.activities.products_list;

import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.ArrayList;

public interface ProductsListMvpView extends MvpView {
    void setLastPageTrue();
    void addMoreToAdapter(ArrayList<ModelProductItem> list);
    void addLoadingFooter();
    void removeLoadingFooter();
    void showRetryAdapter();
    void setIsLoadingFalse();

}
