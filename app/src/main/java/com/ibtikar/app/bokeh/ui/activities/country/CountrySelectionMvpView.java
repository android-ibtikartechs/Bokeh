package com.ibtikar.app.bokeh.ui.activities.country;

import com.ibtikar.app.bokeh.data.models.ModelCountry;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface CountrySelectionMvpView extends MvpView {
    void populateCountriesListSpinner(List<ModelCountry> countriesList);
    void ToastOffline();
}
