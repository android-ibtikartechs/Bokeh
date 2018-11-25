package com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options;

import android.util.Log;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.ModelArea;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.responses.ResponseAddToCart;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.RxBus;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogBuyOptionsPresenter <V extends DialogBuyOptionsMvpView> extends BasePresenter<V> implements DialogBuyOptionsMvpPresenter<V>{
    Disposable disposable;
    ModelProductItem modelProductItem;
    public DialogBuyOptionsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void submitAndAddItem(Integer deliveryOrPickup, Integer areaId, Integer cityId, String textAddress, String deliveryDate, Integer deliveryTime) {
        getMvpView().showLoadingProgress();

        Call<ResponseAddToCart> call;

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.addToCart(27,modelProductItem.getId(),deliveryDate,deliveryTime,deliveryOrPickup,areaId,cityId, textAddress);

        call.enqueue(new Callback<ResponseAddToCart>() {
            @Override
            public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                Log.d("", "onAddToCartResponse: "+ response.body().getExisted());
                if (response.body().getStatus())
                {
                    if (!response.body().getExisted())
                        getDataManager().plusOneCartItems();
                }
                getMvpView().finishSubmitting();

            }

            @Override
            public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
                getMvpView().hideLoadingProgress();
            }
        });


    }

    @Override
    public void loadAreasSpinner() {
        disposable = RxBus.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof ModelProductItem) {
                    modelProductItem= (ModelProductItem) o;
                    //do sth with the data .. you can populate a RecycleView for example
                }
            }
        });


        getMvpView().setaupAreasSpinner(modelProductItem.getCities());
    }

    @Override
    public void disposeRxSubscriber() {
        disposable.dispose(); //unsubscribe
    }


}
