package com.am.app.bouqeh.ui.activities.country;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseCheckStatusUpdate;
import com.am.app.bouqeh.data.models.responses.ResponseCitiesList;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountrySelectionPresenter <V extends CountrySelectionMvpView> extends BasePresenter<V> implements CountrySelectionMvpPresenter<V>{

    public CountrySelectionPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadCitiesList() {
        getMvpView().showLoadingProgress();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseCitiesList> call = null;
        call = service.getCitiesList();
        call.enqueue(new Callback<ResponseCitiesList>() {
            @Override
            public void onResponse(Call<ResponseCitiesList> call, Response<ResponseCitiesList> response) {
                //Log.d("", "onResponse: " + response.body().getList().get(0).getImage());
                if (response.body().getStatus())
                    getMvpView().populateCitiesListSpinner(response.body().getList());
                else
                {
                  /*  List <ModelCity> list= new ArrayList<>();
                    list.add(new ModelCountry(64,"http://bouquet.ibtikarprojects.com/uploads/countries/egyflag.png","Egypt"));
                    getMvpView().populateCitiesListSpinner(list);*/
                }
                getMvpView().hideLoadingProgress(true);
            }

            @Override
            public void onFailure(Call<ResponseCitiesList> call, Throwable t) {
               /* List <ModelCountry> list= new ArrayList<>();
                list.add(new ModelCountry(64,"http://bouquet.ibtikarprojects.com/uploads/countries/egyflag.png","Egypt"));
                getMvpView().populateCitiesListSpinner(list); */
                getMvpView().ToastOffline();
                getMvpView().hideLoadingProgress(true);
            }
        });
    }

    @Override
    public void setSelectedArea(int selectedCountryId, String selectedAreaName) {
        getDataManager().setAreaId(selectedCountryId);
        getDataManager().setAreaName(selectedAreaName);
    }

    @Override
    public void setSelectedCity(int selectedCityId, String selectedCityName) {
        getDataManager().setCityId(selectedCityId);
        getDataManager().setCityName(selectedCityName);
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
