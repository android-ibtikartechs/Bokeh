package com.am.app.bouqeh.ui.activities.payment;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseChecout;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentPresenter <V extends PaymentMvpView> extends BasePresenter<V> implements PaymentMvpPresenter<V> {

    public PaymentPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void checout(int payType, String referenceNumber) {
        getMvpView().showLoading();
        Call<ResponseChecout> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.checoutOrder(getDataManager().getTokenKey(), getDataManager().getUserId(),payType, referenceNumber);

        call.enqueue(new Callback<ResponseChecout>() {
            @Override
            public void onResponse(Call<ResponseChecout> call, Response<ResponseChecout> response) {
                getMvpView().hideLoading();
                if (response.body().getStatus())
                {
                    getMvpView().transitToSuccessActivity();
                }
            }

            @Override
            public void onFailure(Call<ResponseChecout> call, Throwable t) {
                getMvpView().hideLoading();
            }
        });
    }
}
