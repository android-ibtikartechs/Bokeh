package com.am.app.bouqeh.ui.activities.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;
import com.am.app.bouqeh.ui.activities.country.CountrySelectionActivity;
import com.am.app.bouqeh.ui.activities.products_list.ProductsListActivity;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.Set;

public class SplashActivity extends BaseActivity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

/*
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            String categoryId = deepLink.getLastPathSegment();


                            Toast.makeText(SplashActivity.this, "Deep Link", Toast.LENGTH_SHORT).show();
                        }




                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "getDynamicLink:onFailure", e);

                    }
                });

*/

        init();


        /*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
            } else {
                init();
            }
        } else {
            init();
        }
*/
    }


    private void init() {
        new Handler().postDelayed(new Runnable() {
            public void run() {

                startActivity(new Intent(SplashActivity.this, CountrySelectionActivity.class));
                SplashActivity.this.finish();
            }
        }, 2000);
    }

    /*

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    init();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashActivity.this, CountrySelectionActivity.class));
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    */

}
