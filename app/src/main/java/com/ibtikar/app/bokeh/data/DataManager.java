package com.ibtikar.app.bokeh.data;

import com.ibtikar.app.bokeh.data.db_helper.SQLiteHandler;

public class DataManager {
    private SharedPreferenceHelper sharedPrefsHelper;
    private SQLiteHandler mSQLiteHandler;

    public DataManager(SQLiteHandler mSQLiteHandler, SharedPreferenceHelper sharedPrefsHelper) {
        this.sharedPrefsHelper = sharedPrefsHelper;
        this.mSQLiteHandler = mSQLiteHandler;

    }

    public void clearSharedPreference()
    {
        sharedPrefsHelper.clear();
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

    public void setAreaId(int areaId)
    {
        sharedPrefsHelper.setAreaId(areaId);
    }

    public Integer getAreaId()
    {
        return sharedPrefsHelper.getAreaId();
    }

    public void setCityId(int cityId)
    {
        sharedPrefsHelper.setCityId(cityId);
    }

    public Integer getCityId()
    {
        return sharedPrefsHelper.getCityId();
    }

    public void setUserName(String userName) {
        sharedPrefsHelper.setUserName(userName);
    }

    public void setUserEmail(String userEmail) {
        sharedPrefsHelper.setUserEmail(userEmail);
    }

    public void setUserMobNum(String userMobNum) {
        sharedPrefsHelper.setUserMobNum(userMobNum);
    }

    public void setLoginStatus(boolean status)
    {
        sharedPrefsHelper.setLoginStatus(status);
    }

    public String getUserName (){
        return sharedPrefsHelper.getUserName();
    }

    public String getUserEmail(){
        return sharedPrefsHelper.getUserEmail();
    }



    public String getUserMobNum(){
        return sharedPrefsHelper.getUserMobNum();
    }

    public boolean getLoginStausus(){
        return sharedPrefsHelper.getLoginStatus();
    }

    public String getFirstName(){
        return sharedPrefsHelper.getFirstName();
    }
    public String getLastName(){
        return sharedPrefsHelper.getLastName();
    }
    public String getBirthDate(){
        return sharedPrefsHelper.getBirthDate();
    }
    public int getGender(){
        return sharedPrefsHelper.getGender();
    }


    public void setFirstName(String firstName) {
        sharedPrefsHelper.setFirstName(firstName);
    }

    public void setTokenKey(String tokenKey)
    {
        sharedPrefsHelper.setToken(tokenKey);
    }

    public void setLastName(String lastName) {
        sharedPrefsHelper.setLastName(lastName);
    }

    public void setBirthDate(String birthDate) {
        sharedPrefsHelper.setBirthDate(birthDate);
    }

    public void setGender(int gender) {
        // if gender flag == 1 --> gender is man
        // if gender flag == 2 --> gender is women
        sharedPrefsHelper.setGender(gender);
    }

    public Integer getUserId(){return sharedPrefsHelper.getUserId();}

    public String getTokenKey(){
        return sharedPrefsHelper.getTokenKey();
    }

    public void setUserId(Integer userId) {
        sharedPrefsHelper.setUserId(userId);
    }


   /* private void passNewCartCount(Integer newCartCount)
    {
        rxBus.send(newCartCount);
    }*/
}
