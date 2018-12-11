package com.ibtikar.app.bokeh.ui.activities.main;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCartDetails;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

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
        Call<ResponseCartDetails> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.getCartDetails(27);
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
