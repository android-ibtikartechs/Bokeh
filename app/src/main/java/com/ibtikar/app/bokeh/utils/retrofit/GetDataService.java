package com.ibtikar.app.bokeh.utils.retrofit;

import com.ibtikar.app.bokeh.data.models.responses.ResponseCategoriesModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseCountriesList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseHomeModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseProductList;
import com.ibtikar.app.bokeh.data.models.responses.ResponseSearchResultList;

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
    Call<ResponseSearchResultList> getSearchResultFor(@Query("keyword") String keyWord);

    @GET("/rcountries")
    Call<ResponseCountriesList> getCountriesList();

}
