package com.am.app.bouqeh.ui.activities.about_application;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.data.models.responses.ResponseAboutApplication;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutApplicationPresenter <V extends AboutApplicationMvpView> extends BasePresenter<V> implements AboutApplicationMvpPresenter<V> {


    public AboutApplicationPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadContent(int contentTypeFlag) {
        getMvpView().showLoadingView();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseAboutApplication> call = null;

        switch (contentTypeFlag)
        {
            case StaticValues.FLAG_ABOUT_US_CONTENT:
                call = service.fetchAboutUsContent();
                break;
            case StaticValues.FLAG_PRIVACY_POLICY_CONTENT:
                call = service.fetchPrivacyContent();
                break;
            case StaticValues.FLAG_TERMS_INFO_CONTENT:
                call = service.fetchTermsInfoContent();
                break;
            case StaticValues.FLAG_CONTACT_CONTENT:
                call = service.fetchContantContent();
                break;
        }

        call.enqueue(new Callback<ResponseAboutApplication>() {
            @Override
            public void onResponse(Call<ResponseAboutApplication> call, Response<ResponseAboutApplication> response) {
                getMvpView().populateContent(response.body().getContent());
                getMvpView().showContent();
            }

            @Override
            public void onFailure(Call<ResponseAboutApplication> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });
    }
}
