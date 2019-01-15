package com.ibtikar.app.bokeh.utils.retrofit;

import com.ibtikar.app.bokeh.data.models.responses.ResponseAddToCart;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCartDetails;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCategoriesModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCheckStatusUpdate;
import com.ibtikar.app.bokeh.data.models.responses.ResponseChecout;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCountriesList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseDecreaseCartItemQuantity;
import com.ibtikar.app.bokeh.data.models.responses.ResponseDeleteCartItem;
import com.ibtikar.app.bokeh.data.models.responses.ResponseFilter;
import com.ibtikar.app.bokeh.data.models.responses.ResponseHomeModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseIncreaseCartItemQuantity;
import com.ibtikar.app.bokeh.data.models.responses.ResponseLikeButton;
import com.ibtikar.app.bokeh.data.models.responses.ResponseLogin;
import com.ibtikar.app.bokeh.data.models.responses.ResponseOrdersHistory;
import com.ibtikar.app.bokeh.data.models.responses.ResponseProductList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseReceiteList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseSearchResultList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseShopsList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseUpdateProfile;
import com.ibtikar.app.bokeh.data.models.responses.ResponseWishList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/front/{country_id}/{user}")
    Call<ResponseHomeModel> getHomeData(@Path("country_id") int countryId, @Path("user") int userId);

    @GET("/occasions")
    Call<ResponseCategoriesModel> getCategoriesList();

    @GET("/occasion_products/{product_id}/{country_id}/{user}")
    Call<ResponseProductList> getProductListForCategory(@Path("product_id")Integer id, @Path("country_id") Integer countryId, @Path("user") Integer userId);

    @POST("/productslike")
    Call<ResponseSearchResultList> getSearchResultFor(@Query("_token") String token, @Query("keyword") String keyWord, @Query("country") Integer countryId, @Query("user") int userId);

    @GET("/rcountries")
    Call<ResponseCountriesList> getCountriesList();

    @GET("/sellers/{country_id}")
    Call<ResponseShopsList> getShopsList(@Path("country_id") Integer countryId);

    @GET("/seller_products/{seller_id}/{user_id}")
    Call<ResponseProductList> getProductListForShop(@Path("seller_id") Integer sellerId, @Path("user_id") int userId);


    @POST("/addtocart")
    Call<ResponseAddToCart> addToCart(@Query("_token") String token, @Query("user") Integer userId, @Query("product") Integer productId, @Query("pdate") String deliveryDate, @Query("ptime") Integer deliveryTime, @Query("delivary") Integer deliveryOrPickup, @Query("pcity") Integer cityId, @Query("parea") Integer parea, @Query("paddress") String adderss);


    @POST("/cartitems")
    Call<ResponseCartDetails> getCartDetails(@Query("_token") String token, @Query("user") Integer userId);

    @POST("/wishlist")
    Call<ResponseWishList> getWishList(@Query("_token") String token, @Query("user") Integer userId);

    @POST("/addtowish")
    Call<ResponseLikeButton> addToWish(@Query("_token") String token, @Query("user") Integer userId, @Query("product") Integer productId);

    @POST("/increasequantity")
    Call<ResponseIncreaseCartItemQuantity> increaseCartItemQuantity(@Query("_token") String token, @Query("id") Integer cartItemId);

    @POST("/decreasequantity")
    Call<ResponseDecreaseCartItemQuantity> decreaseCartItemQuantity(@Query("_token") String token, @Query("id") Integer cartItemId);

    @POST("/ordershistory")
    Call<ResponseOrdersHistory> getOrdersHistory(@Query("_token") String token, @Query("user") Integer userId);

    @POST("/removecartitem")
    Call<ResponseDeleteCartItem> deleteCartItem(@Query("_token") String token, @Query("id") Integer cartItemId);

    @POST("/getcartresetitems")
    Call<ResponseReceiteList> getReceitList(@Query("_token") String token, @Query("user") Integer userId);


    @POST("/checkout")
    Call<ResponseChecout> checoutOrder(@Query("_token") String token, @Query("user") Integer userId, @Query("paytype") Integer payType);

    @POST("/ulogin")
    Call<ResponseLogin> loginUser(@Query("_token") String token, @Query("email") String email, @Query("password") String password);

    @POST("/resendactivation")
    Call<ResponseLogin> resendActivationLink(@Query("_token") String token, @Query("email") String email);

    @POST("/uregister")
    Call<ResponseLogin> signupUser(@Query("_token") String token, @Query("fname") String firstName, @Query("lname") String lastName, @Query("phone") String mobNum, @Query("email") String email, @Query("gender") Integer gender, @Query("bdate") String birthDate,  @Query("password") String password);


    @POST("/search")
    Call<ResponseFilter> filter(@Query("_token") String token, @Query("price_from") Integer priceFrom, @Query("price_to") Integer priceTo, @Query("user") int userId);

    @POST("/updateprofile")
    Call<ResponseUpdateProfile> updateProfile(@Query("_token") String token, @Query("email") String emailAddress, @Query("fname") String firstName, @Query("lname") String lastName, @Query("phone") String phone, @Query("bdate") String birthDate, @Query("gender") Integer gender);

    @GET("/iswished/{user_id}/{product_id}")
    Call<ResponseLikeButton> isProductLiked(@Path("user_id") int userId, @Path("product_id") int productId);

    @GET("/checkappversion/{current_version}")
    Call<ResponseCheckStatusUpdate> checkStatusUpdsate(@Path("current_version") int currentVersionCode);
}
