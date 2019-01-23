package com.am.app.bouqeh.ui.fragments.account_fragment_container;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;

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
