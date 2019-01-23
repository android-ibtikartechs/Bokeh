package com.am.app.bouqeh.ui.fragments.dialog_buy_options;

import com.am.app.bouqeh.data.models.ModelCity;
import com.am.app.bouqeh.data.models.ModelArea;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface DialogBuyOptionsMvpView extends MvpView {
    void showAreaListSpinner(List<ModelCity> list);
    void showCityListSpinner(List<ModelArea> list);
    void setaupAreasSpinner(List<ModelCity> list);

    void showLoadingProgress();
    void hideLoadingProgress();

    void finishSubmitting(String message, String productName);

}
