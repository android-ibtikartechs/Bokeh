package com.ibtikar.app.bokeh.utils;

import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;

import java.text.ParseException;
import java.util.Comparator;

public class DeascendingComparator implements Comparator<ModelProductItem> {
    private int sortType;

    public DeascendingComparator(int sortType) {
        this.sortType = sortType;
    }


    @Override
    public int compare(ModelProductItem o1, ModelProductItem o2) {
        switch (sortType)
        {
            case  com.ibtikar.app.bokeh.data.StaticValues.SORT_TYPE_TITLE :
                return o2.getName().compareTo(o1.getName());

            case StaticValues.SORT_TYPE_PRICE:
                return o2.getPrice().compareTo(o1.getPrice());

            case StaticValues.SORT_TYPE_NEWEST:
                try {
                    return o2.getTimeOfProductAdditionInMilliSecond().compareTo(o1.getTimeOfProductAdditionInMilliSecond());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                //case StaticValues.SORT_TYPE_NEWEST:

        }
        return 0;
    }
}
