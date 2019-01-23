package com.am.app.bouqeh.ui.fragments.my_orders;

import com.am.app.bouqeh.data.models.ModelLastOrdersListItem;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface MyOrdersMvpView extends MvpView {
    void addMoreToOrdresListAdapter(List<ModelLastOrdersListItem> list);


    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
