package com.am.app.bouqeh.utils;

import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.data.models.ModelProductItem;

import java.text.ParseException;
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

                case StaticValues.SORT_TYPE_NEWEST:
                    try {
                        return o1.getTimeOfProductAdditionInMilliSecond().compareTo(o2.getTimeOfProductAdditionInMilliSecond());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //case StaticValues.SORT_TYPE_NEWEST:

        }
        return 0;
    }
}
