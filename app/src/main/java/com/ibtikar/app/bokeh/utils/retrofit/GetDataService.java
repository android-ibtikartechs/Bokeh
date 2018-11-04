package com.ibtikar.app.bokeh.utils.retrofit;

import com.ibtikar.app.bokeh.data.models.responses.ResponseCategoriesModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseHomeModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseProductList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseSearchResultList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/front/{latitude}/{longitude}")
    Call<ResponseHomeModel> getHomeData(@Path("latitude") double latitude, @Path("longitude") double longitude);

    @GET("/occasions")
    Call<ResponseCategoriesModel> getCategoriesList();

    @GET("/occasion_products/{id}/{latitude}/{longitude}")
    Call<ResponseProductList> getProductListForCategory(@Path("id")Integer id, @Path("latitude") double latitude, @Path("longitude") double longitude);

    @POST("/productslike")
    Call<ResponseSearchResultList> getSearchResultFor(@Query("keyword") String keyWord);

}
