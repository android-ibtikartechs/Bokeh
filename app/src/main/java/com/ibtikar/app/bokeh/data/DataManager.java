package com.ibtikar.app.bokeh.data;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.data.db_helper.SQLiteHandler;
import com.ibtikar.app.bokeh.utils.RxBus;

public class DataManager {
    private SharedPreferenceHelper sharedPrefsHelper;
    private SQLiteHandler mSQLiteHandler;

    public DataManager(SQLiteHandler mSQLiteHandler, SharedPreferenceHelper sharedPrefsHelper) {
        this.sharedPrefsHelper = sharedPrefsHelper;
        this.mSQLiteHandler = mSQLiteHandler;

    }

    public void setCartItemsCount(int cartItemsCount) {
        sharedPrefsHelper.setCartItemsCount(cartItemsCount);
        //passNewCartCount(getCartItemsCount());
    }

    public int getCartItemsCount (){
        return sharedPrefsHelper.getCartItemsCount();
    }

    public void plusOneCartItems()
    {
        sharedPrefsHelper.plusOneCartItems();
        //passNewCartCount(getCartItemsCount());
    }

    public void removeOneCartItem()
    {
        sharedPrefsHelper.removeOneCartItem();
        //passNewCartCount(getCartItemsCount());
    }

    public void setCountryId(int countryId)
    {
        sharedPrefsHelper.setCountryId(countryId);
    }

    public int getCountryId()
    {
        return sharedPrefsHelper.getCountryId();
    }

   /* private void passNewCartCount(Integer newCartCount)
    {
        rxBus.send(newCartCount);
    }*/
}
