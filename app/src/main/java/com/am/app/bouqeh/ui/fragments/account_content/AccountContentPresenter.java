package com.am.app.bouqeh.ui.fragments.account_content;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;

public class AccountContentPresenter <V extends AccountContentMvpView> extends BasePresenter<V> implements AccountContentMvpPresenter<V> {


    public AccountContentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getProfileData() {
        String name = getDataManager().getFirstName()+" "+getDataManager().getLastName();
        System.out.println(getDataManager().getUserEmail());
        getMvpView().setProfileData(name,getDataManager().getUserEmail(), getDataManager().getUserMobNum());
    }

    @Override
    public void logout() {
        getDataManager().clearSharedPreference();
        getMvpView().afterLogout();
    }
}
