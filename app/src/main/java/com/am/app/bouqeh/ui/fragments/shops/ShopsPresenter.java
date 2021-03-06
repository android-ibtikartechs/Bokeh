package com.am.app.bouqeh.ui.fragments.shops;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseShopsList;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsPresenter <V extends ShopsMvpView> extends BasePresenter<V> implements ShopsMvpPresenter<V> {


    public ShopsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadShops() {

        getMvpView().showLoadingView();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseShopsList> call = null;
        call = service.getShopsList(getDataManager().getAreaId());
        call.enqueue(new Callback<ResponseShopsList>() {
            @Override
            public void onResponse(Call<ResponseShopsList> call, Response<ResponseShopsList> response) {
                if (response.body().getStatus())
                {
                    getMvpView().addMoreToAdapter(response.body().getList());
                    getMvpView().showContent();
                }
                else
                {
                    getMvpView().showErrorConnectionView();
                }
            }

            @Override
            public void onFailure(Call<ResponseShopsList> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });


        /*
        List<ModelShopItem> list = new ArrayList<>();
        list.add(new ModelShopItem(34 ,
                "Bouquet",
                "https://images.unsplash.com/photo-1531058240690-006c446962d8?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=d7c5c05b9ee9d344821207bfcf9b7a2d&auto=format&fit=crop&w=750&q=80",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""));

        list.add(new ModelShopItem(1,
                "Elshikh Zayed Flowers",
                "https://images.unsplash.com/photo-1472200129899-ecfa867b5534?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=c42850e3beeb5d69dd56f619f548795c&auto=format&fit=crop&w=765&q=80",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""));

        list.add(new ModelShopItem(1,
                "Elshrouk Ward",
                "https://images.unsplash.com/photo-1487070183336-b863922373d4?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=6d34026de28fc574bcd9fb3d230be17f&auto=format&fit=crop&w=750&q=80",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""));

        list.add(new ModelShopItem(1,
                "Elzakaziq Ward",
                "https://images.unsplash.com/photo-1510885428963-8362ed9167ce?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=f98733aabed5bae7f35b78c7bcd799a7&auto=format&fit=crop&w=750&q=80",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""));

        getMvpView().addMoreToAdapter(list);
        getMvpView().showContent();
        */
    }
}
