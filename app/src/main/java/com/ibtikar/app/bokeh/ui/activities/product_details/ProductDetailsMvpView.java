package com.ibtikar.app.bokeh.ui.activities.product_details;

import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

public interface ProductDetailsMvpView extends MvpView {
    void populateData(ModelProductItem modelProductItem);
}
