package com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.ModelArea;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.utils.RxBus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DialogBuyOptionsPresenter <V extends DialogBuyOptionsMvpView> extends BasePresenter<V> implements DialogBuyOptionsMvpPresenter<V>{
    Disposable disposable;
    ModelProductItem modelProductItem;
    public DialogBuyOptionsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void submitAndAddItem() {
        getDataManager().plusOneCartItems();
    }

    @Override
    public void loadAreasSpinner() {
        disposable = RxBus.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof ModelProductItem) {
                    modelProductItem= (ModelProductItem) o;
                    //do sth with the data .. you can populate a RecycleView for example
                }
            }
        });

        disposable.dispose(); //unsubscribe
        getMvpView().setaupAreasSpinner(modelProductItem.getCities());
    }
}
