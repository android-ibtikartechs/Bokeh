package com.ibtikar.app.bokeh.ui.activities.payment;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.responses.ResponseChecout;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentPresenter <V extends PaymentMvpView> extends BasePresenter<V> implements PaymentMvpPresenter<V> {

    public PaymentPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void checout(int payType) {
        getMvpView().showLoading();
        Call<ResponseChecout> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.checoutOrder(27,payType);

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
