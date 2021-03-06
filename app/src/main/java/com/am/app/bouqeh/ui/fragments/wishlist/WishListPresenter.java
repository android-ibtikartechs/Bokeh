package com.am.app.bouqeh.ui.fragments.wishlist;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.data.models.responses.ResponseWishList;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

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
        Call<ResponseWishList> call = null;

        call = service.getWishList(getDataManager().getTokenKey(), getDataManager().getUserId());

        call.enqueue(new Callback<ResponseWishList>() {
            @Override
            public void onResponse(Call<ResponseWishList> call, Response<ResponseWishList> response) {
                if (response.body().getStatus()) {
                    List<ModelProductItem> list = response.body().getList();
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
            public void onFailure(Call<ResponseWishList> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadNextPage(int currentPage) {

    }
}
