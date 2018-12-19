package com.ibtikar.app.bokeh.ui.fragments.account_fragment_container;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class AccountContainerPresenter <V extends AccountContainerMvpView> extends BasePresenter<V> implements AccountContainerMvpPresenter<V> {


    public AccountContainerPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void checkLoginStatus() {
        if (getDataManager().getLoginStausus())
            getMvpView().showLoginOrAccountFragment(StaticValues.FLAG_ACCOUNT_SCREEN);

        else
            getMvpView().showLoginOrAccountFragment(StaticValues.FLAG_LOGIN_SCREEN);
    }
}
