package com.ibtikar.app.bokeh.ui.fragments.search;

import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelSearchResultItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface SearchMvpView extends MvpView {
    void addMoreToSearchResultAdapter(List<ModelProductItem> list);

}
