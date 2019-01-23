package com.am.app.bouqeh.ui.fragments.categories;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.OccasionItemModel;
import com.am.app.bouqeh.data.models.responses.ResponseCategoriesModel;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesPresenter <V extends CategoriesMvpView> extends BasePresenter<V> implements CategoriesMvpPresenter<V> {
    public CategoriesPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadCategories() {
        getMvpView().showLoadingView();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseCategoriesModel> call = service.getCategoriesList();
        call.enqueue(new Callback<ResponseCategoriesModel>() {
            @Override
            public void onResponse(Call<ResponseCategoriesModel> call, Response<ResponseCategoriesModel> response) {
                //getMvpView().addMoreToFeaturedItemAdapter(response.body().getItems());
                getMvpView().showContent();
                populateDataToCategoriesList(response.body().getOccasions());
            }

            @Override
            public void onFailure(Call<ResponseCategoriesModel> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });

    }

    void populateDataToCategoriesList(List<OccasionItemModel> list)
    {
        getMvpView().addMoreToCategoryAdapter(list);
    }


}
