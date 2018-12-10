package com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options;

import com.ibtikar.app.bokeh.data.models.ModelArea;
import com.ibtikar.app.bokeh.data.models.ModelCity;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface DialogBuyOptionsMvpView extends MvpView {
    void showAreaListSpinner(List<ModelArea> list);
    void showCityListSpinner(List<ModelCity> list);
    void setaupAreasSpinner(List<ModelArea> list);

    void showLoadingProgress();
    void hideLoadingProgress();

    void finishSubmitting(String message, String productName);
}
