package com.ibtikar.app.bokeh.ui.activities.base;

import android.content.Context;
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
}
