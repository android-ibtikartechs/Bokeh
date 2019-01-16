package com.ibtikar.app.bokeh.ui.activities.Success;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.ui.activities.base.BaseActivity;
import com.ibtikar.app.bokeh.ui.activities.country.CountrySelectionActivity;
import com.ibtikar.app.bokeh.ui.activities.main.MainActivity;
import com.ibtikar.app.bokeh.ui.activities.splash.SplashActivity;

public class SuccessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_success);


        init();

    }

    private void init() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_down);
            }
        }, 3000);
    }
}
