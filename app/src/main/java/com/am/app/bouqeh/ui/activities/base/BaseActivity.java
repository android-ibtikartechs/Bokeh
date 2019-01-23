package com.am.app.bouqeh.ui.activities.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements MvpView {
    @Override
    protected void attachBaseContext(Context newBase) {
       /* String lang_code = "ar"; //load it from SharedPref
        Locale locale = new Locale(lang_code);
        MyContextWrapper myContextWrapper = new MyContextWrapper(newBase);
        Context context = myContextWrapper.wrap(newBase,lang_code);
        super.attachBaseContext(context); */

        //Context context = LanguageHelper.updateLanguage(newBase, "ar");
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
