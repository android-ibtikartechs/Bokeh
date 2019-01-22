package com.ibtikar.app.bokeh.ui.activities.country;

import com.ibtikar.app.bokeh.data.models.ModelCity;
import com.ibtikar.app.bokeh.data.models.ModelCountry;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface CountrySelectionMvpView extends MvpView {
    void populateCitiesListSpinner(List<ModelCity> citiesList);
    void ToastOffline();
    void showUpdateStaus(boolean isAppUpdated, boolean isForceUpdateRequired);
    void showLoadingProgress();
    void hideLoadingProgress(boolean isAllDone);

}
