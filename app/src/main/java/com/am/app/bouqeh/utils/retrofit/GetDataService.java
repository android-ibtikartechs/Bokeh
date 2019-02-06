package com.am.app.bouqeh.utils.retrofit;

import com.am.app.bouqeh.data.models.responses.ResponseAboutApplication;
import com.am.app.bouqeh.data.models.responses.ResponseAddToCart;
import com.am.app.bouqeh.data.models.responses.ResponseCartDetails;
import com.am.app.bouqeh.data.models.responses.ResponseCategoriesModel;
import com.am.app.bouqeh.data.models.responses.ResponseCheckStatusUpdate;
import com.am.app.bouqeh.data.models.responses.ResponseChecout;
import com.am.app.bouqeh.data.models.responses.ResponseCitiesList;
import com.am.app.bouqeh.data.models.responses.ResponseCountriesList;
import com.am.app.bouqeh.data.models.responses.ResponseDecreaseCartItemQuantity;
import com.am.app.bouqeh.data.models.responses.ResponseDeleteCartItem;
import com.am.app.bouqeh.data.models.responses.ResponseFilter;
import com.am.app.bouqeh.data.models.responses.ResponseFilteredProductList;
import com.am.app.bouqeh.data.models.responses.ResponseForgetPassword;
import com.am.app.bouqeh.data.models.responses.ResponseHomeModel;
import com.am.app.bouqeh.data.models.responses.ResponseIncreaseCartItemQuantity;
import com.am.app.bouqeh.data.models.responses.ResponseLikeButton;
import com.am.app.bouqeh.data.models.responses.ResponseLogin;
import com.am.app.bouqeh.data.models.responses.ResponseOrdersHistory;
import com.am.app.bouqeh.data.models.responses.ResponseProductList;
import com.am.app.bouqeh.data.models.responses.ResponseReceiteList;
import com.am.app.bouqeh.data.models.responses.ResponseSearchResultList;
import com.am.app.bouqeh.data.models.responses.ResponseSendMessageToAdmin;
import com.am.app.bouqeh.data.models.responses.ResponseShopsList;
import com.am.app.bouqeh.data.models.responses.ResponseUpdateProfile;
import com.am.app.bouqeh.data.models.responses.ResponseWishList;

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

    @GET("/occasion_products/{occasion_id}/{area_id}/{type_order}/{order}/{user}")
    Call<ResponseProductList> getProductListForCategoryPagged(@Path("occasion_id")Integer id, @Path("area_id") Integer areaId, @Path("user") Integer userId, @Path("type_order") Integer typeOrder, @Path("order") Integer order,  @Query("p") int page);

    @GET("/occasion_products_filterg/{occasion_id}/{area_id}/{price_from}/{price_to}/{user}")
    Call<ResponseProductList> getFilteredOccasionsProductList(@Path("occasion_id")Integer id, @Path("area_id") Integer areaId, @Path("user") Integer userId, @Path("price_from") int priceFrom, @Path("price_to") int priceTo , @Query("p") int page);


    @POST("/search")
    Call<ResponseSearchResultList> getSearchResultFor(@Query("_token") String token, @Query("keyword") String keyWord, @Query("area") Integer areaId, @Query("user") int userId);

    @GET("/rcountries")
    Call<ResponseCountriesList> getCountriesList();

    @GET("/rcities")
    Call<ResponseCitiesList> getCitiesList();

    @GET("/sellers/{country_id}")
    Call<ResponseShopsList> getShopsList(@Path("country_id") Integer countryId);

    @GET("/seller_products/{seller_id}/{user_id}")
    Call<ResponseProductList> getProductListForShop(@Path("seller_id") Integer sellerId, @Path("user_id") int userId);

    @GET("/seller_products/{seller_id}/{type_order}/{order}/{user_id}")
    Call<ResponseProductList> getProductListForShopPagged(@Path("seller_id") Integer sellerId, @Path("user_id") int userId, @Path("type_order") Integer typeOrder, @Path("order") Integer order, @Query("p") int page);

    @GET("/seller_products_filterg/{seller_id}/{price_from}/{price_to}/{user}")
    Call<ResponseProductList> getFilteredSellerProductList(@Path("seller_id")Integer id, @Path("user") Integer userId, @Path("price_from") int priceFrom, @Path("price_to") int priceTo , @Query("p") int page);






    @POST("/addtocart")
    Call<ResponseAddToCart> addToCart(@Query("_token") String token, @Query("user") Integer userId, @Query("product") Integer productId, @Query("pdate") String deliveryDate, @Query("ptime") Integer deliveryTime, @Query("delivary") Integer deliveryOrPickup, @Query("pcity") Integer cityId, @Query("parea") Integer parea, @Query("paddress") String adderss, @Query("psignature") String signature);


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

    @POST("/forgetpassword")
    Call<ResponseForgetPassword> resendPassword(@Query("email") String emailAddress);

    @POST("/sendhelp")
    Call<ResponseSendMessageToAdmin> sendMessageToAdmin(@Query("name") String userName, @Query("email") String email, @Query("msg") String message);


    @GET("/aboutinfo")
    Call<ResponseAboutApplication> fetchAboutUsContent();

    @GET("/privacyinfo")
    Call<ResponseAboutApplication> fetchPrivacyContent();

    @GET("/termsinfo")
    Call<ResponseAboutApplication> fetchTermsInfoContent();

    @GET("/contactinfo")
    Call<ResponseAboutApplication> fetchContantContent();



}
