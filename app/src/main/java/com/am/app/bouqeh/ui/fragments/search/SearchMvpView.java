package com.am.app.bouqeh.ui.fragments.search;

import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface SearchMvpView extends MvpView {
    void addMoreToSearchResultAdapter(List<ModelProductItem> list);

}
