package com.ibtikar.app.bokeh.ui.activities.country;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class CountrySelectionPresenter <V extends CountrySelectionMvpView> extends BasePresenter<V> implements CountrySelectionMvpPresenter<V>{

    public CountrySelectionPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
