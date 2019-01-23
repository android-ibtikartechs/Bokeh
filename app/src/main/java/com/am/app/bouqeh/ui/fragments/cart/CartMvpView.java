package com.am.app.bouqeh.ui.fragments.cart;

import com.am.app.bouqeh.data.models.ModelCartListItem;
import com.am.app.bouqeh.data.models.ModelReciptList;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface CartMvpView extends MvpView {
    void addMoreToCartListAdapter(List<ModelCartListItem> list);
    void addMoreToReceiptList(List<ModelReciptList> list);
    void setOrderTotalTxtView(String total);
    void increaseCartItemQuantity(int position, Integer currentQuantity);
    void decreaseCartItemQuantity(int position, Integer currentQuantity);
    void showAlertDialogExcedeMaximumQuantityOfCartItem(int itemPosition, Integer maximumQuantity, String productName);
    void reloadOrdersInformation (Integer OrderTotal, List<ModelReciptList> reciptList);
    void deleteItemFromCartList(int position);




    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
    void showEmptyView();


    void showErrorConnectionViewOrdersInfo();
    void showLoadingViewOrdersInfo();
    void showContentOrdersInfo();

}
