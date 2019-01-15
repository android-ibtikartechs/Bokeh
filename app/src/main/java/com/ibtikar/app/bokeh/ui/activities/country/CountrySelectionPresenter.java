package com.ibtikar.app.bokeh.ui.activities.country;

import android.util.Log;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.ModelCountry;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCheckStatusUpdate;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCountriesList;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountrySelectionPresenter <V extends CountrySelectionMvpView> extends BasePresenter<V> implements CountrySelectionMvpPresenter<V>{

    public CountrySelectionPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadCountriesList() {
        getMvpView().showLoadingProgress();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseCountriesList> call = null;
        call = service.getCountriesList();
        call.enqueue(new Callback<ResponseCountriesList>() {
            @Override
            public void onResponse(Call<ResponseCountriesList> call, Response<ResponseCountriesList> response) {
                Log.d("", "onResponse: " + response.body().getList().get(0).getImage());
                if (response.body().getStatus())
                    getMvpView().populateCountriesListSpinner(response.body().getList());
                else
                {
                    List <ModelCountry> list= new ArrayList<>();
                    list.add(new ModelCountry(64,"http://bouquet.ibtikarprojects.com/uploads/countries/egyflag.png","Egypt"));
                    getMvpView().populateCountriesListSpinner(list);
                }
                getMvpView().hideLoadingProgress(true);
            }

            @Override
            public void onFailure(Call<ResponseCountriesList> call, Throwable t) {
                List <ModelCountry> list= new ArrayList<>();
                list.add(new ModelCountry(64,"http://bouquet.ibtikarprojects.com/uploads/countries/egyflag.png","Egypt"));
                getMvpView().populateCountriesListSpinner(list);
                getMvpView().ToastOffline();
                getMvpView().hideLoadingProgress(true);
            }
        });
    }

    @Override
    public void setSelectedCountry(int selectedCountryId) {
        getDataManager().setCountryId(selectedCountryId);
    }

    @Override
    public void checkUpdateStatus(int currentVersionCode) {
        getMvpView().showLoadingProgress();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseCheckStatusUpdate> call = null;
        call = service.checkStatusUpdsate(currentVersionCode);
        call.enqueue(new Callback<ResponseCheckStatusUpdate>() {
            @Override
            public void onResponse(Call<ResponseCheckStatusUpdate> call, Response<ResponseCheckStatusUpdate> response) {
                if (response.body().getStatus())
                {
                    getDataManager().setTokenKey(response.body().getToken());
                    getMvpView().hideLoadingProgress(false);
                    getMvpView().showUpdateStaus(response.body().getUpdated(), response.body().getForceupdate());
                }
                else
                {
                    getMvpView().hideLoadingProgress(false);
                    getMvpView().ToastOffline();
                }

            }

            @Override
            public void onFailure(Call<ResponseCheckStatusUpdate> call, Throwable t) {
                getMvpView().hideLoadingProgress(false);
                getMvpView().ToastOffline();
            }
        });
    }
}
