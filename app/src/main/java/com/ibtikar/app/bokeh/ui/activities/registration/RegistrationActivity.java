package com.ibtikar.app.bokeh.ui.activities.registration;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.ui.activities.base.BaseActivity;
import com.ibtikar.app.bokeh.ui.fragments.login.LoginFragment;
import com.ibtikar.app.bokeh.ui.fragments.signup.SignupFragment;

public class RegistrationActivity extends BaseActivity {
    Intent intent;
    int signupOrLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        intent = getIntent();

        signupOrLogin = intent.getIntExtra(StaticValues.KEY_SIGN_UP_OR_LOGIN, 0);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (signupOrLogin == StaticValues.SIGN_UP_TYPE) {
            transaction.setCustomAnimations(0, 0);
            transaction.replace(R.id.registration_fragment_container, new SignupFragment());
            transaction.commit();
        }

        else {
            transaction.setCustomAnimations(0, 0);
            transaction.replace(R.id.registration_fragment_container, new LoginFragment());
            transaction.commit();
        }


    }
}
