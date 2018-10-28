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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter <V extends HomeMvpView> extends BasePresenter<V> implements HomeMvpPresenter<V> {
    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void loadHomeData(double latitude, double longitude) {

        Log.d("", "loadItem: ");

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseHomeModel> call = service.getHomeData(30.0659632,31.2021518);
        call.enqueue(new Callback<ResponseHomeModel>() {
            @Override
            public void onResponse(Call<ResponseHomeModel> call, Response<ResponseHomeModel> response) {
                //getMvpView().addMoreToFeaturedItemAdapter(response.body().getItems());
                loadCategories(response.body().getOccasions());
                loadFeaturedItems(response.body().getItems());
                loadShopsItems(response.body().getShops());
                loadSlider(response.body().getSpecialoffers());

            }

            @Override
            public void onFailure(Call<ResponseHomeModel> call, Throwable t) {
                Log.d("", "onFailure: ");
            }
        });

    }


    public void loadCategories(List<OccasionItemModel>list) {
/*
        ArrayList<OccasionItemModel> list = new ArrayList<>();
        OccasionItemModel categoryModel = new OccasionItemModel(1, "https://i.imgur.com/NgAAeSN.png","Suits all occasions");
        list.add(categoryModel);

        categoryModel = new OccasionItemModel(1, "https://i.imgur.com/u1EcZMc.png","Wedding");
        list.add(categoryModel);

        categoryModel = new OccasionItemModel(1, "https://i.imgur.com/CgOtADr.png","Hajj");
        list.add(categoryModel);

        categoryModel = new OccasionItemModel(1, "https://i.imgur.com/d7Tjint.png","Birthday");
        list.add(categoryModel);

        categoryModel = new OccasionItemModel(1, "https://i.imgur.com/1SnKPb2.png","Love");
        list.add(categoryModel);
*/
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
