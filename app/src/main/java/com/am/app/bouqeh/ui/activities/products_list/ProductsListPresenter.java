package com.am.app.bouqeh.ui.activities.products_list;

import android.util.Log;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.FilterByPassingData;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.data.models.LocationLatLong;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.data.models.SortByBottomSheetPassingData;
import com.am.app.bouqeh.data.models.responses.ResponseProductList;
import com.am.app.bouqeh.ui.activities.base.BasePresenter;
import com.am.app.bouqeh.utils.AscendingComparator;
import com.am.app.bouqeh.utils.DeascendingComparator;
import com.am.app.bouqeh.utils.retrofit.GetDataService;
import com.am.app.bouqeh.utils.retrofit.RetrofitClientInstance;

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
    public void loadFirstPage(LocationLatLong locationLatLong, Integer categoryId, final boolean isSort, final SortByBottomSheetPassingData sortByBottomSheetPassingData, int listType) {


        getMvpView().showLoadingView();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseProductList> call = null;
        if (listType == StaticValues.CATEGORY_TYPE) {
            if (!isSort)
                call = service.getProductListForCategoryPagged(categoryId, getDataManager().getAreaId(), getDataManager().getUserId(), 2, 1, 1);
            else
                call = service.getProductListForCategoryPagged(categoryId, getDataManager().getAreaId(), getDataManager().getUserId(), sortByBottomSheetPassingData.getSortByType(), sortByBottomSheetPassingData.getAscendingDeascending(), 1);
        }

        else if (listType == StaticValues.SHOPS_TYPE) {
            if(!isSort)
                call = service.getProductListForShopPagged(categoryId, getDataManager().getUserId(),2, 1, 1);
            else
                call = service.getProductListForShopPagged(categoryId, getDataManager().getUserId(),sortByBottomSheetPassingData.getSortByType(), sortByBottomSheetPassingData.getAscendingDeascending(), 1);
        }

        call.enqueue(new Callback<ResponseProductList>() {
            @Override
            public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {
                if (response.body().getStatus()) {
                    AscendingComparator ascendingComparator;
                    DeascendingComparator deascendingComparator;
                    List<ModelProductItem> list = response.body().getProducts();
                    if (list.size() == 0)
                        getMvpView().showEmptyView(listType);
                    else
                    {
                       /*
                        if (isSort) {
                        if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_ASCEND) {
                            if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE) {
                                ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_TITLE);
                                Collections.sort(list, ascendingComparator);
                            } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE) {
                                ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_PRICE);
                                Collections.sort(list, ascendingComparator);
                            } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST) {
                                ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                Collections.sort(list, ascendingComparator);
                            }

                        }

                        if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_DEASCEND) {
                            if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE) {
                                deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_TITLE);
                                Collections.sort(list, deascendingComparator);
                            } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE) {
                                deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_PRICE);
                                Collections.sort(list, deascendingComparator);
                            } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST) {
                                deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                Collections.sort(list, deascendingComparator);
                            }
                        }
                    }
                    */

                    getMvpView().addMoreToAdapter(list);
                        getMvpView().addLoadingFooter();
                        getMvpView().showContent();
                }
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
    public void loadNextPage(int currentPage, Integer categoryId, boolean isSort, SortByBottomSheetPassingData sortByBottomSheetPassingData, int listType) {
        Log.d("", "loadNextPage: " + "current page: " + currentPage);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseProductList> call = null;
        if (listType == StaticValues.CATEGORY_TYPE)
        {
            if (!isSort)
                call = service.getProductListForCategoryPagged(categoryId, getDataManager().getAreaId(), getDataManager().getUserId(), 2, 1, currentPage);
            else
                call = service.getProductListForCategoryPagged(categoryId, getDataManager().getAreaId(), getDataManager().getUserId(), sortByBottomSheetPassingData.getSortByType(), sortByBottomSheetPassingData.getAscendingDeascending(), currentPage);
        }
        else if (listType == StaticValues.SHOPS_TYPE) {
            if(!isSort)
                call = service.getProductListForShopPagged(categoryId, getDataManager().getUserId(),2, 1, currentPage);
            else
                call = service.getProductListForShopPagged(categoryId, getDataManager().getUserId(),sortByBottomSheetPassingData.getSortByType(), sortByBottomSheetPassingData.getAscendingDeascending(), currentPage);
        }
        call.enqueue(new Callback<ResponseProductList>() {
            @Override
            public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {
                if (response.body().getStatus()) {

                    getMvpView().removeLoadingFooter();
                    getMvpView().setIsLoadingFalse();



                    AscendingComparator ascendingComparator;
                    DeascendingComparator deascendingComparator;
                    List<ModelProductItem> list = response.body().getProducts();
                    if (list.size() == 0)
                        getMvpView().setLastPageTrue();
                    else
                    {
                        /*
                        if (isSort) {
                            if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_ASCEND) {
                                if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE) {
                                    ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_TITLE);
                                    Collections.sort(list, ascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE) {
                                    ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_PRICE);
                                    Collections.sort(list, ascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST) {
                                    ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                    Collections.sort(list, ascendingComparator);
                                }

                            }

                            if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_DEASCEND) {
                                if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE) {
                                    deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_TITLE);
                                    Collections.sort(list, deascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE) {
                                    deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_PRICE);
                                    Collections.sort(list, deascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST) {
                                    deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                    Collections.sort(list, deascendingComparator);
                                }
                            }
                        }
                        */

                        getMvpView().addMoreToAdapter(list);
                        getMvpView().addLoadingFooter();
                        //getMvpView().showContent();
                    }
                }
                else
                    getMvpView().showRetryAdapter();
                    //getMvpView().showErrorConnectionView();
            }

            @Override
            public void onFailure(Call<ResponseProductList> call, Throwable t) {
                getMvpView().showRetryAdapter();
            }
        });

    }

    @Override
    public void loadFilteredData(List<ModelProductItem> list , int priceFrom, int priceTo) {
        List<ModelProductItem> filteredList  = new ArrayList<>();
        for (int i = 0 ; i<list.size() ; i++)
        {
            if (list.get(i).getPrice()>= priceFrom && list.get(i).getPrice()<= priceTo)
            {
                filteredList.add(list.get(i));
            }
        }

        getMvpView().addMoreToAdapter(filteredList);

    }

    @Override
    public void loadFilteredDataFirstPage(Integer categoryId, int listType, FilterByPassingData filterByPassingData) {

        getMvpView().showLoadingView();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseProductList> call = null;
        if (listType == StaticValues.CATEGORY_TYPE) {
            call = service.getFilteredOccasionsProductList(categoryId, getDataManager().getAreaId(), getDataManager().getUserId(), filterByPassingData.getPriceFrom(), filterByPassingData.getPriceTo(), 1);
        }

        else if (listType == StaticValues.SHOPS_TYPE) {
            call = service.getFilteredSellerProductList(categoryId, getDataManager().getUserId(),filterByPassingData.getPriceFrom(), filterByPassingData.getPriceTo(), 1);        }

        call.enqueue(new Callback<ResponseProductList>() {
            @Override
            public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {
                if (response.body().getStatus()) {
                    List<ModelProductItem> list = response.body().getProducts();
                    if (list.size() == 0)
                        getMvpView().showEmptyView(listType);
                    else
                    {

                        getMvpView().addMoreToAdapter(list);
                        getMvpView().addLoadingFooter();
                        getMvpView().showContent();
                    }
                }
                else
                    getMvpView().showErrorConnectionView();
            }

            @Override
            public void onFailure(Call<ResponseProductList> call, Throwable t) {
                getMvpView().showErrorConnectionView();
            }
        });


    }

    @Override
    public void loadFilteredDataNextPage(int currentPage, Integer categoryId,  int listType, FilterByPassingData filterByPassingData)
    {

        Log.d("", "loadNextPage: " + "current page: " + currentPage);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseProductList> call = null;
        if (listType == StaticValues.CATEGORY_TYPE)
        {
            call = service.getFilteredOccasionsProductList(categoryId, getDataManager().getAreaId(), getDataManager().getUserId(), filterByPassingData.getPriceFrom(), filterByPassingData.getPriceTo(), currentPage);
        }
        else if (listType == StaticValues.SHOPS_TYPE) {
            call = service.getFilteredSellerProductList(categoryId, getDataManager().getUserId(),filterByPassingData.getPriceFrom(), filterByPassingData.getPriceTo(), currentPage);
        }
        call.enqueue(new Callback<ResponseProductList>() {
            @Override
            public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {
                if (response.body().getStatus()) {

                    getMvpView().removeLoadingFooter();
                    getMvpView().setIsLoadingFalse();

                    List<ModelProductItem> list = response.body().getProducts();
                    if (list.size() == 0)
                        getMvpView().setLastPageTrue();
                    else
                    {
                        /*
                        if (isSort) {
                            if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_ASCEND) {
                                if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE) {
                                    ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_TITLE);
                                    Collections.sort(list, ascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE) {
                                    ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_PRICE);
                                    Collections.sort(list, ascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST) {
                                    ascendingComparator = new AscendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                    Collections.sort(list, ascendingComparator);
                                }

                            }

                            if (sortByBottomSheetPassingData.getAscendingDeascending() == StaticValues.SORT_DEASCEND) {
                                if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_TITLE) {
                                    deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_TITLE);
                                    Collections.sort(list, deascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_PRICE) {
                                    deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_PRICE);
                                    Collections.sort(list, deascendingComparator);
                                } else if (sortByBottomSheetPassingData.getSortByType() == StaticValues.SORT_TYPE_NEWEST) {
                                    deascendingComparator = new DeascendingComparator(StaticValues.SORT_TYPE_NEWEST);
                                    Collections.sort(list, deascendingComparator);
                                }
                            }
                        }
                        */

                        getMvpView().addMoreToAdapter(list);
                        getMvpView().addLoadingFooter();
                        //getMvpView().showContent();
                    }
                }
                else
                    getMvpView().showRetryAdapter();
                //getMvpView().showErrorConnectionView();
            }

            @Override
            public void onFailure(Call<ResponseProductList> call, Throwable t) {
                getMvpView().showRetryAdapter();
            }
        });

    }
}
