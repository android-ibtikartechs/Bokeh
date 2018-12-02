package com.ibtikar.app.bokeh.ui.fragments.cart;

import android.util.Log;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.GalleryProductImage;
import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelProductItemReciptList;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCartDetails;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter <V extends CartMvpView> extends BasePresenter<V> implements CartMvpPresenter<V> {
    public CartPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadCartList() {
        getMvpView().showLoadingView();
    /*    List<ModelCartItem> cartItems = new ArrayList<>();
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


        List<ModelProductItemReciptList> reciptListList = new ArrayList<>();
        reciptListList.add(new ModelProductItemReciptList(50,"بوكيه ورد طبيعي أحمر"));
        reciptListList.add(new ModelProductItemReciptList(30,"Loloa Bouquet"));
        reciptListList.add(new ModelProductItemReciptList(25,"بوكيه ورد طبيعي أصفر"));
        reciptListList.add(new ModelProductItemReciptList(80,"Khoshah"));
        reciptListList.add(new ModelProductItemReciptList(200,"Lotus"));

        List<ModelReciptList> reciptList = new ArrayList<>();
        reciptList.add(new ModelReciptList(50, 500, reciptListList));
        reciptList.add(new ModelReciptList(40, 300, reciptListList));
        reciptList.add(new ModelReciptList(53, 852, reciptListList));
        reciptList.add(new ModelReciptList(30, 963, reciptListList));

        getMvpView().addMoreToReceiptList(reciptList);

        getMvpView().addMoreToCartListAdapter(cartItems);
*/

        Call<ResponseCartDetails> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.getCartDetails(27);

        call.enqueue(new Callback<ResponseCartDetails>() {
            @Override
            public void onResponse(Call<ResponseCartDetails> call, Response<ResponseCartDetails> response) {
                getMvpView().showContent();
                Log.d("", "onResponse: "+response.body());
                //System.out.println(response.body().getOrders().get(0).getProducts().get(0).getProductname());
                getMvpView().addMoreToCartListAdapter(response.body().getList());
                getMvpView().addMoreToReceiptList(response.body().getOrders());
                getMvpView().setOrderTotalTxtView(response.body().getGrandTtoal().toString());
            }

            @Override
            public void onFailure(Call<ResponseCartDetails> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });

    }
}
