package com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options;

import com.ibtikar.app.bokeh.data.models.ModelCity;
import com.ibtikar.app.bokeh.data.models.ModelArea;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface DialogBuyOptionsMvpView extends MvpView {
    void showAreaListSpinner(List<ModelCity> list);
    void showCityListSpinner(List<ModelArea> list);
    void setaupAreasSpinner(List<ModelCity> list);

    void showLoadingProgress();
    void hideLoadingProgress();

    void finishSubmitting(String message, String productName);

}
