package com.ibtikar.app.bokeh.ui.fragments.search;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.responses.ResponseSearchResultList;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter <V extends SearchMvpView> extends BasePresenter<V> implements SearchMvpPresenter<V> {
    Call<ResponseSearchResultList> call;

    public SearchPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadSearchResult(String keyword) {
        if (call != null)
            call.cancel();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.getSearchResultFor(getDataManager().getTokenKey(),keyword,getDataManager().getCountryId(), getDataManager().getUserId());
        call.enqueue(new Callback<ResponseSearchResultList>() {
            @Override
            public void onResponse(Call<ResponseSearchResultList> call, Response<ResponseSearchResultList> response) {
                System.out.println(getDataManager().getCountryId().toString());
                getMvpView().addMoreToSearchResultAdapter(response.body().getList());

            }

            @Override
            public void onFailure(Call<ResponseSearchResultList> call, Throwable t) {

            }
        });
    }
}
