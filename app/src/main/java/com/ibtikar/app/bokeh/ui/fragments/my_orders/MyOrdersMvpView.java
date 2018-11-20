package com.ibtikar.app.bokeh.ui.fragments.my_orders;

import com.ibtikar.app.bokeh.data.models.ModelLastOrdersListItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface MyOrdersMvpView extends MvpView {
    void addMoreToOrdresListAdapter(List<ModelLastOrdersListItem> list);


    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
