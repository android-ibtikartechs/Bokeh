package com.am.app.bouqeh.ui.activities.country;

import com.am.app.bouqeh.data.models.ModelCity;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface CountrySelectionMvpView extends MvpView {
    void populateCitiesListSpinner(List<ModelCity> citiesList);
    void ToastOffline();
    void showUpdateStaus(boolean isAppUpdated, boolean isForceUpdateRequired);
    void showLoadingProgress();
    void hideLoadingProgress(boolean isAllDone);

}
