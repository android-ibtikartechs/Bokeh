package com.am.app.bouqeh.ui.fragments.cart;

import android.util.Log;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.responses.ResponseCartDetails;
import com.am.app.bouqeh.data.models.responses.ResponseDecreaseCartItemQuantity;
import com.am.app.bouqeh.data.models.responses.ResponseDeleteCartItem;
import com.am.app.bouqeh.data.models.responses.ResponseIncreaseCartItemQuantity;
import com.am.app.bouqeh.data.models.responses.ResponseReceiteList;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

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
        Log.d("", "loadCartList: " + getDataManager().getTokenKey());
        System.out.println("loadCartList: " + getDataManager().getTokenKey());
        System.out.println("userId= " + getDataManager().getUserId());
        call = service.getCartDetails(getDataManager().getTokenKey(), getDataManager().getUserId());

        call.enqueue(new Callback<ResponseCartDetails>() {
            @Override
            public void onResponse(Call<ResponseCartDetails> call, Response<ResponseCartDetails> response) {
                System.out.println( "onResponse: " + response.body().toString());
                if (response.body().getList().size() > 0) {
                    getMvpView().showContent();

                    //System.out.println(response.body().getOrders().get(0).getProducts().get(0).getProductname());
                    getMvpView().addMoreToCartListAdapter(response.body().getList());
                    getMvpView().addMoreToReceiptList(response.body().getOrders());
                    getMvpView().setOrderTotalTxtView(response.body().getGrandTtoal().toString());
                }
                else
                    getMvpView().showEmptyView();
            }

            @Override
            public void onFailure(Call<ResponseCartDetails> call, Throwable t) {
                getMvpView().showErrorConnectionView();
                System.out.println( "error: " + t.toString());
            }
        });

    }

    @Override
    public void loadReceitList() {
        getMvpView().showLoadingViewOrdersInfo();
        Call<ResponseReceiteList> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.getReceitList(getDataManager().getTokenKey(), getDataManager().getUserId());
        call.enqueue(new Callback<ResponseReceiteList>() {
            @Override
            public void onResponse(Call<ResponseReceiteList> call, Response<ResponseReceiteList> response) {
                if (response.body().getStatus())
                {
                    getMvpView().reloadOrdersInformation(response.body().getGrandTtoal(), response.body().getOrders());
                    getMvpView().showContentOrdersInfo();
                }
                else
                    getMvpView().showErrorConnectionViewOrdersInfo();
            }

            @Override
            public void onFailure(Call<ResponseReceiteList> call, Throwable t) {
                getMvpView().showErrorConnectionViewOrdersInfo();
            }
        });
    }

    @Override
    public void increaseCartItemQuantityPresenter(int cartItemId, int position, Integer currentQuantity) {
        getMvpView().showLoadingViewOrdersInfo();
        Call<ResponseIncreaseCartItemQuantity> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.increaseCartItemQuantity(getDataManager().getTokenKey(), cartItemId);
        call.enqueue(new Callback<ResponseIncreaseCartItemQuantity>() {
            @Override
            public void onResponse(Call<ResponseIncreaseCartItemQuantity> call, Response<ResponseIncreaseCartItemQuantity> response) {
                if (response.body().getStatus())
                {
                    if (response.body().getIncreased()) {
                        getMvpView().increaseCartItemQuantity(position, currentQuantity);
                    }
                    else
                        getMvpView().showAlertDialogExcedeMaximumQuantityOfCartItem(position,response.body().getMaximum(), response.body().getProductName());

                    getMvpView().reloadOrdersInformation(response.body().getGrandTtoal(),response.body().getOrders());
                    getMvpView().showContentOrdersInfo();
                }
            }

            @Override
            public void onFailure(Call<ResponseIncreaseCartItemQuantity> call, Throwable t) {
                getMvpView().showErrorConnectionViewOrdersInfo();
            }
        });
    }

    @Override
    public void decreaseCartItemQuantityPresenter(int cartItemId, int position, Integer currentQuantity) {
        getMvpView().showLoadingViewOrdersInfo();
        Call<ResponseDecreaseCartItemQuantity> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.decreaseCartItemQuantity(getDataManager().getTokenKey(), cartItemId);
        call.enqueue(new Callback<ResponseDecreaseCartItemQuantity>() {
            @Override
            public void onResponse(Call<ResponseDecreaseCartItemQuantity> call, Response<ResponseDecreaseCartItemQuantity> response) {
                if (response.body().getStatus())
                {
                    if (response.body().getDecreased())
                        getMvpView().decreaseCartItemQuantity(position,currentQuantity);

                    getMvpView().reloadOrdersInformation(response.body().getGrandTtoal(),response.body().getOrders());
                    getMvpView().showContentOrdersInfo();
                }
            }

            @Override
            public void onFailure(Call<ResponseDecreaseCartItemQuantity> call, Throwable t) {
                getMvpView().showErrorConnectionViewOrdersInfo();
            }
        });
    }

    @Override
    public void deleteItemFromCartList(int cartItemId, int position) {
        getMvpView().showLoadingViewOrdersInfo();
        Call<ResponseDeleteCartItem> call;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.deleteCartItem(getDataManager().getTokenKey(), cartItemId);

        call.enqueue(new Callback<ResponseDeleteCartItem>() {
            @Override
            public void onResponse(Call<ResponseDeleteCartItem> call, Response<ResponseDeleteCartItem> response) {
                if (response.body().getStatus()) {
                    if (response.body().getDeleted()) {
                        getMvpView().deleteItemFromCartList(position);
                        getDataManager().removeOneCartItem();
                        if (response.body().getOrders().size() == 0)
                            getMvpView().showEmptyView();
                    }

                    getMvpView().reloadOrdersInformation(response.body().getGrandTtoal(),response.body().getOrders());
                    getMvpView().showContentOrdersInfo();
                }


            }

            @Override
            public void onFailure(Call<ResponseDeleteCartItem> call, Throwable t) {
                getMvpView().showErrorConnectionViewOrdersInfo();
            }
        });

    }

    @Override
    public void buy(Integer userId) {

    }
}
