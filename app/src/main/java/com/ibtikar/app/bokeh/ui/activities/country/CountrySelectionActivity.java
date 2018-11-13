package com.ibtikar.app.bokeh.ui.activities.country;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.adapters.AdapterCountrySpinner;
import com.ibtikar.app.bokeh.data.models.CountryModel;
import com.ibtikar.app.bokeh.ui.activities.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountrySelectionActivity extends AppCompatActivity {

    @BindView(R.id.btn_continue)
    Button btnContinue;

    @BindView(R.id.country_spinner)
    Spinner spinnerCountry;

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

        setupCountrySpinner();
    }

    private void setupCountrySpinner() {
        List<CountryModel> list = new ArrayList<>();

        list.add(new CountryModel(1, "Egypt", "https://cdn.countryflags.com/thumbs/egypt/flag-square-250.png"));
        list.add(new CountryModel(2, "Kuwait", "https://cdn.countryflags.com/thumbs/kuwait/flag-square-250.png"));
        list.add(new CountryModel(3, "Saudi Arabia", "https://cdn.countryflags.com/thumbs/saudi-arabia/flag-400.png"));

        AdapterCountrySpinner adapterCountrySpinner = new AdapterCountrySpinner(this, 0, list);
        spinnerCountry.setAdapter(adapterCountrySpinner);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CountrySelectionActivity.this, list.get(position).getName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
