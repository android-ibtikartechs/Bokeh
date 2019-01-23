package com.am.app.bouqeh.ui.activities.main;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseCartDetails;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public Integer getCartItem() {
        return getDataManager().getCartItemsCount();
    }

    @Override
    public void refreshCartItemCount() {
        if (getDataManager().getLoginStausus()) {
            Call<ResponseCartDetails> call;
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            call = service.getCartDetails(getDataManager().getTokenKey(), getDataManager().getUserId());
            call.enqueue(new Callback<ResponseCartDetails>() {
                @Override
                public void onResponse(Call<ResponseCartDetails> call, Response<ResponseCartDetails> response) {
                    if (response.body().getStatus()) {
                        getMvpView().refreshCartCount(response.body().getList().size());
                        getDataManager().setCartItemsCount(response.body().getList().size());
                    }

                }

                @Override
                public void onFailure(Call<ResponseCartDetails> call, Throwable t) {

                }
            });
        }
    }


}
