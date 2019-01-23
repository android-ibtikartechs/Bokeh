package com.am.app.bouqeh.ui.activities.product_details;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.data.models.responses.ResponseLikeButton;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.RxBus;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsPresenter <V extends ProductDetailsMvpView> extends BasePresenter<V> implements ProductDetailsMvpPresenter<V> {
    Disposable disposable;
    ModelProductItem productDetails;

    public ProductDetailsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadData() {
        disposable = RxBus.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof ModelProductItem) {
                    productDetails= (ModelProductItem) o;
                    //do sth with the data .. you can populate a RecycleView for example
                }
            }
        });

        disposable.dispose(); //unsubscribe
        getMvpView().populateData(productDetails);
    }

    @Override
    public void changeLikeStaus(Integer productId) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseLikeButton> call = null;

        call = service.addToWish(getDataManager().getTokenKey(), getDataManager().getUserId(), productId);

        call.enqueue(new Callback<ResponseLikeButton>() {
            @Override
            public void onResponse(Call<ResponseLikeButton> call, Response<ResponseLikeButton> response) {
                if (response.body().getStatus() )
                {
                    getMvpView().changeBtnLikeStatus();
                }

                else
                    getMvpView().showYouAreNotLoggedInAlert();
            }

            @Override
            public void onFailure(Call<ResponseLikeButton> call, Throwable t) {

            }
        });
    }

    @Override
    public void addToCart() {
        if (!getDataManager().getLoginStausus())
        {
            getMvpView().showYouAreNotLoggedInAlert();
        }
        else
            getMvpView().showDialogBuyOptions();

    }

    @Override
    public void isProductLiked(int productId) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseLikeButton> call = null;

        call = service.isProductLiked(getDataManager().getUserId(), productId);

        call.enqueue(new Callback<ResponseLikeButton>() {
            @Override
            public void onResponse(Call<ResponseLikeButton> call, Response<ResponseLikeButton> response) {
                System.out.println(response.body().toString());
                if (response.body().getStatus() )
                {
                    getMvpView().showLikeStatus(response.body().getIsLiked());
                }
            }

            @Override
            public void onFailure(Call<ResponseLikeButton> call, Throwable t) {

            }
        });


    }


}
