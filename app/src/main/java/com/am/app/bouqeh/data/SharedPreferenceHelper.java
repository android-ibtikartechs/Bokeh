package com.am.app.bouqeh.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.am.app.bouqeh.MvpApp;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceHelper {
    public static final String MY_PREFS = "MY_PREFS";
    SharedPreferences mSharedPreferences;
    private static final String CART_ITEMS_COUNT = "CART_ITEMS_COUNT";
    private static final String KEY_AREA_ID = "KEY_AREA_ID";
    private static final String KEY_CITY_ID = "KEY_CITY_ID";
    private static final String KEY_CITY_NAME = "KEY_CITY_NAME";
    private static final String KEY_AREA_NAME = "KEY_AREA_NAME";

    private static final String KEY_TOKEN = "KEY_TOKEN";

    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_USER_EMAIL = "KEY_USER_EMAIL";
    private static final String KEY_USER_MOB_NUM = "KEY_USER_MOB_NUM";
    private static final String KEY_FIRST_NAME="KEY_FIRST_NAME";
    private static final String KEY_LAST_NAME="KEY_LAST_NAME";
    private static final String KEY_BIRTH_DATE= "KEY_BIRTH_DATE";
    private static final String KEY_GENDER = "KEY_GENDER";
    private static final String KEY_USER_ID = "KEY_USER_ID";

    private static final String KEY_IS_USER_LOGED_IN = "KEY_IS_USER_LOGED_IN";

    private static final String KEY_SELECTED_LANGUAGE = "KEY_SELECTED_LANGUAGE";

    private static final String KEY_FIREBASE_TOKEN = "KEY_FIREBASE_TOKEN";

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

    public void setFirebaseToken(String firebaseToken)
    {
        mSharedPreferences.edit().putString(KEY_FIREBASE_TOKEN, firebaseToken).apply();
    }



    public void setToken(String tokenKey){
        mSharedPreferences.edit().putString(KEY_TOKEN, tokenKey).apply();
    }

    public void setUserId(Integer userId) {
        mSharedPreferences.edit().putInt(KEY_USER_ID, userId).apply();
    }

    public void setUserEmail(String userEmail) {
        mSharedPreferences.edit().putString(KEY_USER_EMAIL, userEmail).apply();
    }



    public void setUserMobNum(String userMobNum) {
        mSharedPreferences.edit().putString(KEY_USER_MOB_NUM, userMobNum).apply();
    }

    public void setFirstName(String firstName) {
        mSharedPreferences.edit().putString(KEY_FIRST_NAME, firstName).apply();
    }

    public void setLastName(String lastName) {
        mSharedPreferences.edit().putString(KEY_LAST_NAME, lastName).apply();
    }

    public void setBirthDate(String birthDate) {
        mSharedPreferences.edit().putString(KEY_BIRTH_DATE, birthDate).apply();
    }

    public void setSelectedLanguage(String lang) {
        mSharedPreferences.edit().putString(KEY_SELECTED_LANGUAGE, lang).apply();
    }



    public void setGender(int gender) {
        // if gender flag == 1 --> gender is man
        // if gender flag == 2 --> gender is women
        mSharedPreferences.edit().putInt(KEY_GENDER, gender).apply();
    }



    public void setLoginStatus(boolean status)
    {
        mSharedPreferences.edit().putBoolean(KEY_IS_USER_LOGED_IN, status).apply();
    }





    public void setCartItemsCount(int cartItemsCount) {
        mSharedPreferences.edit().putInt(CART_ITEMS_COUNT, cartItemsCount).apply();
        passNewCartCount(getCartItemsCount());
    }




    public void setAreaId(int areaId)
    {
        mSharedPreferences.edit().putInt(KEY_AREA_ID, areaId).apply();
    }

    public void setCityId(int cityId)
    {
        mSharedPreferences.edit().putInt(KEY_CITY_ID, cityId).apply();
    }

    public void setAreaName(String areaName)
    {
        mSharedPreferences.edit().putString(KEY_AREA_NAME, areaName).apply();
    }

    public void setCityName(String cityName)
    {
        mSharedPreferences.edit().putString(KEY_CITY_NAME, cityName).apply();
    }

    public String getFirebaseToken()
    {
        return mSharedPreferences.getString(KEY_FIREBASE_TOKEN, null);
    }

    public boolean getLoginStatus()
    {
        return mSharedPreferences.getBoolean(KEY_IS_USER_LOGED_IN,false);
    }

    public int getAreaId()
    {
        return mSharedPreferences.getInt(KEY_AREA_ID,64);
    }

    public int getCityId()
    {
        return mSharedPreferences.getInt(KEY_CITY_ID,64);
    }

    public String getAreaName()
    {
        return mSharedPreferences.getString(KEY_AREA_NAME, "");
    }

    public String getCityName()
    {
        return mSharedPreferences.getString(KEY_CITY_NAME,"");
    }


    public int getCartItemsCount (){
        return mSharedPreferences.getInt(CART_ITEMS_COUNT,0);
    }




    public String getUserName (){
        return mSharedPreferences.getString(KEY_USER_NAME,"");
    }

    public String getTokenKey(){
        return mSharedPreferences.getString(KEY_TOKEN,"");
    }

    public Integer getUserId(){return mSharedPreferences.getInt(KEY_USER_ID,0);}

    public String getUserEmail(){
        return mSharedPreferences.getString(KEY_USER_EMAIL,"");
    }

    public String getUserMobNum(){
        return mSharedPreferences.getString(KEY_USER_MOB_NUM,"");
    }

    public String getFirstName(){
        return mSharedPreferences.getString(KEY_FIRST_NAME,"");
    }
    public String getLastName(){
        return mSharedPreferences.getString(KEY_LAST_NAME,"");
    }
    public String getBirthDate(){
        return mSharedPreferences.getString(KEY_BIRTH_DATE,"");
    }
    public int getGender(){
        return mSharedPreferences.getInt(KEY_GENDER,1);
    }

    public String getSelectedLanguage(){
        return mSharedPreferences.getString(KEY_SELECTED_LANGUAGE,"en");
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
