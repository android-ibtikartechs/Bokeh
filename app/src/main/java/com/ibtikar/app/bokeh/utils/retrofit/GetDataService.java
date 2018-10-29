package com.ibtikar.app.bokeh.utils.retrofit;

import com.ibtikar.app.bokeh.data.models.responses.ResponseCategoriesModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseHomeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("/front/{latitude}/{longitude}")
    Call<ResponseHomeModel> getHomeData(@Path("latitude") double latitude, @Path("longitude") double longitude);

    @GET("/occasions")
    Call<ResponseCategoriesModel> getCategoriesList();

}
