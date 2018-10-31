package com.ibtikar.app.bokeh.utils;

import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;

import java.util.Comparator;

public class AscendingComparator implements Comparator<ModelProductItem> {
    private int sortType;

    public AscendingComparator(int sortType) {
        this.sortType = sortType;
    }


    @Override
    public int compare(ModelProductItem o1, ModelProductItem o2) {
        switch (sortType)
        {
            case  StaticValues.SORT_TYPE_TITLE :
                return o1.getName().compareTo(o2.getName());

                case StaticValues.SORT_TYPE_PRICE:
                    return o1.getPrice().compareTo(o2.getPrice());

                    //case StaticValues.SORT_TYPE_NEWEST:

        }
        return 0;
    }
}
