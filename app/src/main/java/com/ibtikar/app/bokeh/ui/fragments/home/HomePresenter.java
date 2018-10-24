package com.ibtikar.app.bokeh.ui.fragments.home;

import android.graphics.BitmapFactory;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.models.CategoryItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

import java.util.ArrayList;

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
}
