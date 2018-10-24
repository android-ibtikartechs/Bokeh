package com.ibtikar.app.bokeh.ui.fragments.home;

import android.graphics.BitmapFactory;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.CategoryItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter <V extends HomeMvpView> extends BasePresenter<V> implements HomeMvpPresenter<V> {
    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadCategories() {

        ArrayList<CategoryItemModel> list = new ArrayList<>();
        CategoryItemModel categoryModel = new CategoryItemModel("1", "https://i.imgur.com/NgAAeSN.png","Suits all occasions");
        list.add(categoryModel);

        categoryModel = new CategoryItemModel("1", "https://i.imgur.com/u1EcZMc.png","Wedding");
        list.add(categoryModel);

        categoryModel = new CategoryItemModel("1", "https://i.imgur.com/CgOtADr.png","Hajj");
        list.add(categoryModel);

        categoryModel = new CategoryItemModel("1", "https://i.imgur.com/d7Tjint.png","Birthday");
        list.add(categoryModel);

        categoryModel = new CategoryItemModel("1", "https://i.imgur.com/1SnKPb2.png","Love");
        list.add(categoryModel);

        getMvpView().addMoreToCategoryAdapter(list);

    }

    @Override
    public void loadFeaturedItems() {
        ArrayList<ModelProductItem> list = new ArrayList<>();
        ModelProductItem modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1512056495345-913a0c261dc8?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=56076c6afbca70b55a3b1d053c58f40a&auto=format&fit=crop&w=750&q=80",
                null,
                false,
                null,
                false,
                null
                );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1521859547034-8955b6e3d994?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b2f4a3a160766a476fc9423e8431873e&auto=format&fit=crop&w=750&q=80",
                null,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1491994336086-44f5d76dd8f2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=24ffc71db8c8e944bebc5f1ecb5b987a&auto=format&fit=crop&w=750&q=80",
                null,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);


        modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1518621327167-ea94ffba2a70?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=bd4650e0a5a4ddb19bbe635b9405bc81&auto=format&fit=crop&w=750&q=80",
                null,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        getMvpView().addMoreToFeaturedItemAdapter(list);


    }

    @Override
    public void loadShopsItems() {

        ArrayList<ModelShopItem> list = new ArrayList<>();
        ModelShopItem modelShopItem = new ModelShopItem("1", "Green City","https://images.unsplash.com/photo-1505295146617-869b5ad0fff8?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=aef047aff8b1dbdd785c871506392871&auto=format&fit=crop&w=750&q=80");
        list.add(modelShopItem);

        modelShopItem = new ModelShopItem("1", "Beautiful Blossoms","https://images.unsplash.com/photo-1487070183336-b863922373d4?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=6d34026de28fc574bcd9fb3d230be17f&auto=format&fit=crop&w=750&q=80");
        list.add(modelShopItem);

        modelShopItem = new ModelShopItem("1", "Poppy Petals Florist","https://images.unsplash.com/photo-1488181665079-6c7f3b399bf4?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=0fa7d38a9c591cb4bddcfa0379138e91&auto=format&fit=crop&w=750&q=80");
        list.add(modelShopItem);

        modelShopItem = new ModelShopItem("1", "May Flowers","https://images.unsplash.com/photo-1506094898237-719a84070f2c?ixlib=rb-0.3.5&s=08f6cc60ad8363d43c9bab76189682de&auto=format&fit=crop&w=750&q=80");
        list.add(modelShopItem);



        getMvpView().addMoreToShopsAdapter(list);

    }

    @Override
    public void loadSlider() {
        List<ModelProductItem> list = new ArrayList<>();
        ModelProductItem modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1455497946288-e1d8808c92d2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=a6fd3353efd31d0e22ca7e767f0b7403&auto=format&fit=crop&w=750&q=80",
                null,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1477507551522-19b9960f0df0?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=ab4fd8247989d27763be47c1c87bf85f&auto=format&fit=crop&w=755&q=80",
                null,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1512180958517-a7e591660832?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=1accc9e6c5e1fcb55d38f51fc41f2ddf&auto=format&fit=crop&w=667&q=80",
                null,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);


        modelProductItem = new ModelProductItem("1",
                null,
                "https://images.unsplash.com/photo-1534321900124-219b1c20704e?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=6ed15b44f1623014dbc059de38f4421a&auto=format&fit=crop&w=750&q=80",
                null,
                false,
                null,
                false,
                null
        );
        list.add(modelProductItem);

        getMvpView().addListToSlider(list);
    }


}
