package com.am.app.bouqeh.ui.activities.products_list;

import com.am.app.bouqeh.data.models.LocationLatLong;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.data.models.SortByBottomSheetPassingData;
import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

import java.util.List;

public interface ProductsListMvpPresenter <V extends ProductsListMvpView> extends MvpPresenter<V> {
    void loadFirstPage(LocationLatLong locationLatLong, Integer categoryId, boolean isSort, SortByBottomSheetPassingData sortByBottomSheetPassingData, int listType);
    void loadNextPage(int currentPage, Integer categoryId, boolean isSort, SortByBottomSheetPassingData sortByBottomSheetPassingData, int listType);

    void loadFilteredData(List<ModelProductItem> list, int priceFrom, int priceTo);

    void loadFilteredDataFirstPage( Integer categoryId,  int listType, int priceFrom, int priceTo);
}
