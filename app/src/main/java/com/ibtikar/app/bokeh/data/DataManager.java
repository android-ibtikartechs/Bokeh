package com.ibtikar.app.bokeh.data;

import com.ibtikar.app.bokeh.data.db_helper.SQLiteHandler;

public class DataManager {
    private SharedPreferenceHelper sharedPrefsHelper;
    private SQLiteHandler mSQLiteHandler;

    public DataManager(SQLiteHandler mSQLiteHandler, SharedPreferenceHelper sharedPrefsHelper) {
        this.sharedPrefsHelper = sharedPrefsHelper;
        this.mSQLiteHandler = mSQLiteHandler;
    }
}
