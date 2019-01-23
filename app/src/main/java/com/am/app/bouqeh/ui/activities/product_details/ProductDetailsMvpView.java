package com.am.app.bouqeh.ui.activities.product_details;

import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface ProductDetailsMvpView extends MvpView {
    void populateData(ModelProductItem modelProductItem);
    void changeBtnLikeStatus();
    void showYouAreNotLoggedInAlert();
    void showDialogBuyOptions();
    void showLikeStatus(boolean likeStatus);
}
