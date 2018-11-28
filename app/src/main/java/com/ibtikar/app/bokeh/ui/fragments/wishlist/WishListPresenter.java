package com.ibtikar.app.bokeh.ui.fragments.wishlist;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.responses.ResponseProductList;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListPresenter <V extends WishListMvpView> extends BasePresenter<V> implements WishListMvpPresenter<V>{
    public WishListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadFirstPage() {
        getMvpView().showLoadingView();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseProductList> call = null;

        call = service.getProductListForCategory(34,getDataManager().getCountryId());

        call.enqueue(new Callback<ResponseProductList>() {
            @Override
            public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {
                if (response.body().getStatus()) {
                    List<ModelProductItem> list = response.body().getProducts();
                    if (list.size() == 0)
                        getMvpView().showEmptyView();

                    else
                    {
                        getMvpView().addMoreToAdapter(list);
                        getMvpView().showContent();
                    }
                }
                else
                    getMvpView().showErrorConnectionView();
            }

            @Override
            public void onFailure(Call<ResponseProductList> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadNextPage(int currentPage) {

    }
}
