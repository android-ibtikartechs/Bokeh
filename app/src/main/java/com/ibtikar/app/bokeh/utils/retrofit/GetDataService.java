package com.ibtikar.app.bokeh.utils.retrofit;

import com.ibtikar.app.bokeh.data.models.responses.ResponseAddToCart;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCartDetails;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCategoriesModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCountriesList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseDecreaseCartItemQuantity;
import com.ibtikar.app.bokeh.data.models.responses.ResponseHomeModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseIncreaseCartItemQuantity;
import com.ibtikar.app.bokeh.data.models.responses.ResponseLikeButton;
import com.ibtikar.app.bokeh.data.models.responses.ResponseProductList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseSearchResultList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseShopsList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseWishList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/front/{country_id}")
    Call<ResponseHomeModel> getHomeData(@Path("country_id") int countryId);

    @GET("/occasions")
    Call<ResponseCategoriesModel> getCategoriesList();

    @GET("/occasion_products/{product_id}/{country_id}")
    Call<ResponseProductList> getProductListForCategory(@Path("product_id")Integer id, @Path("country_id") Integer countryId);

    @POST("/productslike")
    Call<ResponseSearchResultList> getSearchResultFor(@Query("keyword") String keyWord, @Query("country") Integer countryId);

    @GET("/rcountries")
    Call<ResponseCountriesList> getCountriesList();

    @GET("/sellers/{country_id}")
    Call<ResponseShopsList> getShopsList(@Path("country_id") Integer countryId);

    @GET("/seller_products/{seller_id}")
    Call<ResponseProductList> getProductListForShop(@Path("seller_id") Integer sellerId);


    @POST("/addtocart")
    Call<ResponseAddToCart> addToCart(@Query("user") Integer userId, @Query("product") Integer productId, @Query("pdate") String deliveryDate, @Query("ptime") Integer deliveryTime, @Query("delivary") Integer deliveryOrPickup, @Query("pcity") Integer cityId, @Query("parea") Integer parea, @Query("paddress") String adderss);


    @POST("/cartitems")
    Call<ResponseCartDetails> getCartDetails(@Query("user") Integer userId);

    @POST("/wishlist")
    Call<ResponseWishList> getWishList(@Query("user") Integer userId);

    @POST("/addtowish")
    Call<ResponseLikeButton> addToWish(@Query("user") Integer userId, @Query("product") Integer productId);

    @POST("/increasequantity")
    Call<ResponseIncreaseCartItemQuantity> increaseCartItemQuantity(@Query("id") Integer cartItemId);

    @POST("/decreasequantity")
    Call<ResponseDecreaseCartItemQuantity> decreaseCartItemQuantity(@Query("id") Integer cartItemId);



}
