package com.ibtikar.app.bokeh.ui.fragments.my_orders;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.GalleryProductImage;
import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelLastOrdersListItem;
import com.ibtikar.app.bokeh.data.models.responses.ResponseOrdersHistory;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrdersPresenter <V extends MyOrdersMvpView> extends BasePresenter<V> implements MyOrdersMvpPresenter<V> {
    public MyOrdersPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadOrdersList() {

        getMvpView().showLoadingView();

        Call<ResponseOrdersHistory> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.getOrdersHistory(getDataManager().getTokenKey(), getDataManager().getUserId());

        call.enqueue(new Callback<ResponseOrdersHistory>() {
            @Override
            public void onResponse(Call<ResponseOrdersHistory> call, Response<ResponseOrdersHistory> response) {
                if (response.body().getStatus()) {
                    getMvpView().addMoreToOrdresListAdapter(response.body().getList());
                    getMvpView().showContent();
                }
                else
                    getMvpView().showErrorConnectionView();
            }

            @Override
            public void onFailure(Call<ResponseOrdersHistory> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });

      /*  List<ModelLastOrdersListItem> ordersListItems = new ArrayList<>();

        List<ModelCartItem> cartItems = new ArrayList<>();
        List<GalleryProductImage> galleryProductImages = new ArrayList<>();

        galleryProductImages.add(new GalleryProductImage("http://bouquet.ibtikarprojects.com/uploads/pimgs/i4.jpg"));
        galleryProductImages.add(new GalleryProductImage("http://bouquet.ibtikarprojects.com/uploads/pimgs/i4.jpg"));


        cartItems.add(new ModelCartItem(1,
                "P1",
                "http://bouquet.ibtikarprojects.com/uploads/images/i1.jpg",
                28,
                "Bouquet",
                34,
                "Wedding",
                "V2VkZGluZyBmbG93ZXJzIGZvciBuZXcgd2VkZGluZw==",
                true,
                30,
                0,
                "","","","","",
                false,
                false,
                galleryProductImages,
                "25/9/2018",
                "10:00 am - 2:00 pm",
                1
        ));

        cartItems.add(new ModelCartItem(1,
                "P2",
                "http://bouquet.ibtikarprojects.com/uploads/images/1705_1712_b1.jpg",
                28,
                "Bouquet",
                34,
                "Wedding",
                "V2VkZGluZyBmbG93ZXJzIGZvciBuZXcgd2VkZGluZw==",
                true,
                45,
                0,
                "","","","","",
                false,
                false,
                galleryProductImages,
                "25/9/2018",
                "10:00 am - 2:00 pm",
                1
        ));

        cartItems.add(new ModelCartItem(1,
                "P3",
                "http://bouquet.ibtikarprojects.com/uploads/pimgs/i4.jpg",
                28,
                "Bouquet",
                34,
                "Wedding",
                "V2VkZGluZyBmbG93ZXJzIGZvciBuZXcgd2VkZGluZw==",
                true,
                80,
                0,
                "","","","","",
                false,
                false,
                galleryProductImages,
                "25/9/2018",
                "10:00 am - 2:00 pm",
                1
        ));

        ordersListItems.add(new ModelLastOrdersListItem(1,"2018-10-30 13:00:00", 250,7,cartItems));
        ordersListItems.add(new ModelLastOrdersListItem(2,"2018-9-1 13:00:00", 300,10,cartItems));
        ordersListItems.add(new ModelLastOrdersListItem(3,"2018-10-30 13:00:00", 82,2,cartItems));

        getMvpView().addMoreToOrdresListAdapter(ordersListItems);*/
    }


}
