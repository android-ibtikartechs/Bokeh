package com.ibtikar.app.bokeh.ui.activities.country;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.ui.activities.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountrySelectionActivity extends AppCompatActivity {

    @BindView(R.id.btn_continue)
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);
        ButterKnife.bind(this);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CountrySelectionActivity.this, MainActivity.class));
            }
        });
    }
}
