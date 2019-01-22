package com.ibtikar.app.bokeh.ui.fragments.home;

import android.util.Log;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.OccasionItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.data.models.responses.ResponseHomeModel;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter <V extends HomeMvpView> extends BasePresenter<V> implements HomeMvpPresenter<V> {
    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void loadHomeData() {

        getMvpView().showLoadingView();
        Log.d("", "loadItem: ");

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseHomeModel> call = service.getHomeData(getDataManager().getAreaId(), getDataManager().getUserId());
        call.enqueue(new Callback<ResponseHomeModel>() {
            @Override
            public void onResponse(Call<ResponseHomeModel> call, Response<ResponseHomeModel> response) {
                //getMvpView().addMoreToFeaturedItemAdapter(response.body().getItems());
                if (response.body().getStatus()) {
                    getMvpView().showContent();
                    getDataManager().setTokenKey(response.body().getToken());
                    loadCategories(response.body().getOccasions());
                    loadFeaturedItems(response.body().getItems());
                    loadShopsItems(response.body().getShops());
                    loadSlider(response.body().getSpecialoffers());
                }
                else
                    getMvpView().showErrorConnectionView();
            }

            @Override
            public void onFailure(Call<ResponseHomeModel> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });

    }


    public void loadCategories(List<OccasionItemModel>list) {
        getMvpView().addMoreToCategoryAdapter(list);
    }


    public void loadFeaturedItems(List<ModelProductItem> list) {
        getMvpView().addMoreToFeaturedItemAdapter(list);
    }


    public void loadShopsItems(List<ModelShopItem> list) {
        getMvpView().addMoreToShopsAdapter(list);
    }


    public void loadSlider(List<ModelProductItem>list) {
        getMvpView().addListToSlider(list);
    }


}
