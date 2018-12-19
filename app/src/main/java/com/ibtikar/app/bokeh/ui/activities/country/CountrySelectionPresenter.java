package com.ibtikar.app.bokeh.ui.activities.country;

import android.util.Log;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.ModelCountry;
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
            }

            @Override
            public void onFailure(Call<ResponseCountriesList> call, Throwable t) {
                List <ModelCountry> list= new ArrayList<>();
                list.add(new ModelCountry(64,"http://bouquet.ibtikarprojects.com/uploads/countries/egyflag.png","Egypt"));
                getMvpView().populateCountriesListSpinner(list);
                getMvpView().ToastOffline();
            }
        });
    }

    @Override
    public void setSelectedCountry(int selectedCountryId) {
        getDataManager().setCountryId(selectedCountryId);
    }
}
