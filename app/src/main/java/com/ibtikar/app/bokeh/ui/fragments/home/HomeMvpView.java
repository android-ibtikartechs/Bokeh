package com.ibtikar.app.bokeh.ui.fragments.home;

import com.ibtikar.app.bokeh.data.models.CategoryItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.ArrayList;

public interface HomeMvpView extends MvpView {
    void addMoreToCategoryAdapter(ArrayList<CategoryItemModel> list);
    void addMoreToFeaturedItemAdapter(ArrayList<ModelProductItem>list);

}
