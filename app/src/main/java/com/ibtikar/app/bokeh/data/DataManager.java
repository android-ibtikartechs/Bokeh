package com.ibtikar.app.bokeh.data;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.data.db_helper.SQLiteHandler;
import com.ibtikar.app.bokeh.utils.RxBus;

public class DataManager {
    private SharedPreferenceHelper sharedPrefsHelper;
    private SQLiteHandler mSQLiteHandler;
    RxBus rxBus;

    public DataManager(SQLiteHandler mSQLiteHandler, SharedPreferenceHelper sharedPrefsHelper, RxBus rxBus) {
        this.sharedPrefsHelper = sharedPrefsHelper;
        this.mSQLiteHandler = mSQLiteHandler;
        this.rxBus = rxBus;
    }

    public void setCartItemsCount(int cartItemsCount) {
        sharedPrefsHelper.setCartItemsCount(cartItemsCount);
        passNewCartCount(getCartItemsCount());
    }

    public int getCartItemsCount (){
        return sharedPrefsHelper.getCartItemsCount();
    }

    public void plusOneCartItems()
    {
        sharedPrefsHelper.plusOneCartItems();
        passNewCartCount(getCartItemsCount());
    }

    public void removeOneCartItem()
    {
        sharedPrefsHelper.removeOneCartItem();
        passNewCartCount(getCartItemsCount());
    }

    private void passNewCartCount(Integer newCartCount)
    {
        rxBus.send(newCartCount);
    }
}
