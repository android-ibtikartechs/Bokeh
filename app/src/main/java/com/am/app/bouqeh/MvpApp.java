package com.am.app.bouqeh;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.SharedPreferenceHelper;
import com.am.app.bouqeh.data.db_helper.SQLiteHandler;
import com.am.app.bouqeh.utils.RxBus;
import com.crashlytics.android.Crashlytics;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;

public class MvpApp extends Application {
    DataManager dataManager;
    private RxBus bus;
    private static MvpApp instace;


    @Override
    public void onCreate() {
        super.onCreate();
        instace = this;
        SharedPreferenceHelper sharedPrefsHelper = new SharedPreferenceHelper(getApplicationContext());
        SQLiteHandler sqliteHandler = new SQLiteHandler(getApplicationContext());
        bus = new RxBus();
        dataManager = new DataManager(sqliteHandler, sharedPrefsHelper);

        //setLocale("ar");
        //changeLang(getApplicationContext(), "ar");
    }


    public DataManager getDataManager() {
        return dataManager;
    }

    public RxBus bus() {
        return bus;
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
                context = this.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }

        return new ContextWrapper(context);
    }

    public static MvpApp getInstance()
    {
        return instace;
    }

}