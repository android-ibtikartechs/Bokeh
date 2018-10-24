package com.ibtikar.app.bokeh;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.SharedPreferenceHelper;
import com.ibtikar.app.bokeh.data.db_helper.SQLiteHandler;

import java.util.Locale;

public class MvpApp extends Application {
    DataManager dataManager;



    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceHelper sharedPrefsHelper = new SharedPreferenceHelper(getApplicationContext());
        SQLiteHandler sqliteHandler = new SQLiteHandler(getApplicationContext());
        dataManager = new DataManager(sqliteHandler, sharedPrefsHelper);
        //setLocale("ar");
        //changeLang(getApplicationContext(), "ar");
    }


    public DataManager getDataManager() {
        return dataManager;
    }


    public ContextWrapper changeLang(Context context, String lang_code) {
        Locale sysLocale;

        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.getLocales().get(0);
        } else {
            sysLocale = config.locale;
        }
        if (!lang_code.equals("") && !sysLocale.getLanguage().equals(lang_code)) {
            Locale locale = new Locale(lang_code);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }

        return new ContextWrapper(context);
    }

}