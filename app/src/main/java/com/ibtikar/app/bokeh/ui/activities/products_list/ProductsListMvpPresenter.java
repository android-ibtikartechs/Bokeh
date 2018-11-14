package com.ibtikar.app.bokeh.ui.activities.products_list;

import com.ibtikar.app.bokeh.data.models.LocationLatLong;
import com.ibtikar.app.bokeh.data.models.SortByBottomSheetPassingData;
import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface ProductsListMvpPresenter <V extends ProductsListMvpView> extends MvpPresenter<V> {
    void loadFirstPage(LocationLatLong locationLatLong, Integer categoryId, boolean isSort, SortByBottomSheetPassingData sortByBottomSheetPassingData, int listType);
    void loadNextPage(int currentPage);
}
