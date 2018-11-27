package com.ibtikar.app.bokeh.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.ibtikar.app.bokeh.MvpApp;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceHelper {
    public static final String MY_PREFS = "MY_PREFS";
    SharedPreferences mSharedPreferences;
    private static final String CART_ITEMS_COUNT = "CART_ITEMS_COUNT";
    private static final String KEY_COUNTRY_ID = "KEY_COUNTRY_ID";
    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_USER_EMAIL = "KEY_USER_EMAIL";
    private static final String KEY_USER_MOB_NUM = "KEY_USER_MOB_NUM";
    private Context context;

    public SharedPreferenceHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        this.context = context;
    }

    public void clear()
    {
        mSharedPreferences.edit().clear().apply();
    }


    public void setUserName(String userName) {
        mSharedPreferences.edit().putString(KEY_USER_NAME, userName).apply();
    }

    public void setUserEmail(String userEmail) {
        mSharedPreferences.edit().putString(KEY_USER_EMAIL, userEmail).apply();
    }

    public void setUserMobNum(String userMobNum) {
        mSharedPreferences.edit().putString(KEY_USER_MOB_NUM, userMobNum).apply();
    }



    public void setCartItemsCount(int cartItemsCount) {
        mSharedPreferences.edit().putInt(CART_ITEMS_COUNT, cartItemsCount).apply();
        passNewCartCount(getCartItemsCount());
    }




    public void setCountryId(int countryId)
    {
        mSharedPreferences.edit().putInt(KEY_COUNTRY_ID, countryId).apply();
    }

    public int getCountryId()
    {
        return mSharedPreferences.getInt(KEY_COUNTRY_ID,64);
    }

    public int getCartItemsCount (){
        return mSharedPreferences.getInt(CART_ITEMS_COUNT,0);
    }

    public String getUserName (){
        return mSharedPreferences.getString(KEY_USER_NAME,"");
    }

    public String getUserEmail(){
        return mSharedPreferences.getString(KEY_USER_EMAIL,"");
    }

    public String getUserMobNum(){
        return mSharedPreferences.getString(KEY_USER_MOB_NUM,"");
    }


    public void plusOneCartItems()
    {
        mSharedPreferences.edit().putInt(CART_ITEMS_COUNT, getCartItemsCount()+1).apply();
        passNewCartCount(getCartItemsCount());
    }

    public void removeOneCartItem()
    {
        mSharedPreferences.edit().putInt(CART_ITEMS_COUNT, getCartItemsCount()-1).apply();
        passNewCartCount(getCartItemsCount());
    }

    private void passNewCartCount(Integer newCartCount)
    {
        ((MvpApp) context.getApplicationContext()).bus().send(newCartCount);
    }


}
