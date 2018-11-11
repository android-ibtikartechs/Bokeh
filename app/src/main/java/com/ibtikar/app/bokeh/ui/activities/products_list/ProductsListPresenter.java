package com.ibtikar.app.bokeh.ui.activities.products_list;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.data.models.LocationLatLong;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.SortByBottomSheetPassingData;
import com.ibtikar.app.bokeh.data.models.responses.ResponseHomeModel;
import com.ibtikar.app.bokeh.data.models.responses.ResponseProductList;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.AscendingComparator;
import com.ibtikar.app.bokeh.utils.DeascendingComparator;
import com.ibtikar.app.bokeh.utils.retrofit.GetDataService;
import com.ibtikar.app.bokeh.utils.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsListPresenter <V extends ProductsListMvpView> extends BasePresenter<V> implements ProductsListMvpPresenter<V> {
    public ProductsListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadFirstPage(LocationLatLong locationLatLong, Integer categoryId, final boolean isSort, final SortByBottomSheetPassingData sortByBottomSheetPassingData) {


        getMvpView().showLoadingView();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseProductList> call = service.getProductListForCategory(categoryId,locationLatLong.getLat(),locationLatLong.getLongitude());
        call.enqueue(new Callback<ResponseProductList>() {
            @Override
            public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {
                if (response.body().getStatus()) {
                    AscendingComparator ascendingComparator;
                    DeascendingComparator deascendingComparator;
                    List<ModelProductItem> list = response.body().getProducts();
                    if (isSort)
                    {
                        if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_ASCEND)
                        {
                            if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE)
                            {
                                ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_TITLE);
                                Collections.sort(list, ascendingComparator);
                            }

                            else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE)
                            {
                                ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_PRICE);
                                Collections.sort(list, ascendingComparator);
                            }

                            else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST)
                            {
                                ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                Collections.sort(list, ascendingComparator);
                            }

                        }

                        if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_DEASCEND)
                        {
                            if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE)
                            {
                                deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_TITLE);
                                Collections.sort(list, deascendingComparator);
                            }

                            else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE)
                            {
                                deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_PRICE);
                                Collections.sort(list, deascendingComparator);
                            }

                            else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST)
                            {
                                deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                Collections.sort(list, deascendingComparator);
                            }
                        }
                    }

                    getMvpView().addMoreToAdapter(list);
                    getMvpView().showContent();
                }
                else
                    getMvpView().showErrorConnectionView();
            }

            @Override
            public void onFailure(Call<ResponseProductList> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });


       /* ArrayList<ModelProductItem> list = new ArrayList<>();
        ModelProductItem modelProductItem = new ModelProductItem("1",
                "Blushing Bella Blooms",
                "https://i9.fnp.com/images/pr/l/vibrant-lilies-bouquet_1.jpg",
                100,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                "Blushing Blossoms",
                "http://www.southportblooms.com/image/cache/data/european-mix-flowers-bouquet-345284344-600x600.jpg",
                45,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                "Caribbean Breeze",
                "https://centralsquareflorist.imgix.net/images/item/CSFKyleKleinKKP17887-18020592301.jpg?w=950&auto=format",
                80,
                true,
                null,
                false,
                null
        );
        list.add(modelProductItem);


        modelProductItem = new ModelProductItem("1",
                "Chocolate Love",
                "http://withbestcompliments.com/wp-content/uploads/2018/03/FB00011.jpeg",
                50,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                "Crystal Curiosity",
                "http://newflowerpoint.com/wp-content/uploads/2018/07/magic-and-love-bouqueta-800x800.jpg",
                90,
                true,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                "Diamond Devotion",
                "https://www.veldkampsflowers.com/images/item/zoom_LovesPassonLarge-17011270117.jpg",
                60,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                "Ezili",
                "https://cdn.shopify.com/s/files/1/1252/1803/products/pink-blush-2529961345137_large.jpg?v=1539763968",
                47,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                "Floral Embrace",
                "http://maynilaflowergroup.imap.netdna-cdn.com/super/image/cache/data/new%20products/Rose%20Bouquet/Pink/Pink-Roses-166-700x700.jpg",
                98,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                "Garden of Grandeur",
                "https://www.bigbasket.com/media/uploads/p/m/800435313_2-blooms-bouquets-flower-bouquet-12-delightful-red-roses.jpg",
                79,
                true,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        getMvpView().addMoreToAdapter(list);
        */
    }

    @Override
    public void loadNextPage(int currentPage) {

    }
}
