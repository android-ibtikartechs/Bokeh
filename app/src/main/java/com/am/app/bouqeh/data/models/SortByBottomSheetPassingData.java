package com.am.app.bouqeh.data.models;

public class SortByBottomSheetPassingData {
    private int ascendingDeascending;
    private int SortByType;

    public SortByBottomSheetPassingData(int ascendingDeascending, int sortByType) {
        this.ascendingDeascending = ascendingDeascending;
        SortByType = sortByType;
    }

    public void setAscendingDeascending(int ascendingDeascending) {
        this.ascendingDeascending = ascendingDeascending;
    }

    public void setSortByType(int sortByType) {
        SortByType = sortByType;
    }

    public int getAscendingDeascending() {
        return ascendingDeascending;
    }

    public int getSortByType() {
        return SortByType;
    }
}
