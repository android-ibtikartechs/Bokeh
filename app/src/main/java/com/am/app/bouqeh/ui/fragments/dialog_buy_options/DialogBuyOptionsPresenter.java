package com.am.app.bouqeh.ui.fragments.dialog_buy_options;

import android.util.Log;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.data.models.responses.ResponseAddToCart;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.RxBus;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

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
        if (getDataManager().getLoginStausus()) {
            getMvpView().showLoadingProgress();

            Call<ResponseAddToCart> call;

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            call = service.addToCart(getDataManager().getTokenKey(), getDataManager().getUserId(), modelProductItem.getId(), deliveryDate, deliveryTime, deliveryOrPickup, areaId, cityId, textAddress);

            call.enqueue(new Callback<ResponseAddToCart>() {
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {
                    Log.d("", "onAddToCartResponse: " + response.body().getExisted());
                    if (response.body().getStatus()) {

                        if (response.body().getExisted())
                            getMvpView().finishSubmitting("is already existed in your cart", modelProductItem.getName());
                        else {
                            if (response.body().getAdded()) {
                                getMvpView().finishSubmitting("has been added to cart", modelProductItem.getName());
                                getDataManager().plusOneCartItems();
                            } else
                                getMvpView().finishSubmitting("Sorry, this bouquet is not available now", modelProductItem.getName());


                        }
                    }


                }

                @Override
                public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
                    getMvpView().hideLoadingProgress();
                }
            });

        }


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

    @Override
    public int getSelectedAreaId() {
        return getDataManager().getAreaId();
    }

    @Override
    public int getSelectedCityId() {
        return getDataManager().getCityId();
    }

    @Override
    public String getSelectedAreaName() {
        return getDataManager().getAreaName();
    }

    @Override
    public String getSelectedCityName() {
        return getDataManager().getCityName();
    }


}
