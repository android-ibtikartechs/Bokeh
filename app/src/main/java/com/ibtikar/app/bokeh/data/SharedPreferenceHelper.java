package com.ibtikar.app.bokeh.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceHelper {
    public static final String MY_PREFS = "MY_PREFS";
    SharedPreferences mSharedPreferences;

    public SharedPreferenceHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear()
    {
        mSharedPreferences.edit().clear().apply();
    }
}
