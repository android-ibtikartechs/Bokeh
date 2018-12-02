package com.ibtikar.app.bokeh.ui.fragments.cart;

import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelCartListItem;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface CartMvpView extends MvpView {
    void addMoreToCartListAdapter(List<ModelCartListItem> list);
    void addMoreToReceiptList(List<ModelReciptList> list);
    void setOrderTotalTxtView(String total);

    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
