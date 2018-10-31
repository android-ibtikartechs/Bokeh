package com.ibtikar.app.bokeh.ui.activities.product_details;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.ui.activities.products_list.ProductsListMvpPresenter;
import com.ibtikar.app.bokeh.utils.RxBus;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ProductDetailsPresenter <V extends ProductDetailsMvpView> extends BasePresenter<V> implements ProductDetailsMvpPresenter<V> {
    Disposable disposable;
    ModelProductItem productDetails;

    public ProductDetailsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadData() {
        disposable = RxBus.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof ModelProductItem) {
                    productDetails= (ModelProductItem) o;
                    //do sth with the data .. you can populate a RecycleView for example
                }
            }
        });

        disposable.dispose(); //unsubscribe
        getMvpView().populateData(productDetails);
    }




}
