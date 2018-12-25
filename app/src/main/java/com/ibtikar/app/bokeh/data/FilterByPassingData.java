package com.ibtikar.app.bokeh.data;

public class FilterByPassingData {
    private Integer priceFrom;
    private Integer priceTo;

    public FilterByPassingData() {
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }
}
