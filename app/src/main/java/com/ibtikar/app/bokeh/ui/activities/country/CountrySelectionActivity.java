package com.ibtikar.app.bokeh.ui.activities.country;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.data.adapters.AdapterCountrySpinner;
import com.ibtikar.app.bokeh.data.models.ModelCountry;
import com.ibtikar.app.bokeh.ui.activities.base.BaseActivity;
import com.ibtikar.app.bokeh.ui.activities.main.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountrySelectionActivity extends BaseActivity implements CountrySelectionMvpView {

    @BindView(R.id.btn_continue)
    Button btnContinue;

    @BindView(R.id.country_spinner)
    Spinner spinnerCountry;

    CountrySelectionPresenter presenter;



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

        //setupCountrySpinner();

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new CountrySelectionPresenter(dataManager);
        presenter.onAttach(this);
        presenter.loadCountriesList();
    }

    private void setupCountrySpinner() {
     /*   List<ModelCountry> list = new ArrayList<>();

        list.add(new ModelCountry(1, "Egypt", "https://cdn.countryflags.com/thumbs/egypt/flag-square-250.png"));
        list.add(new ModelCountry(2, "Kuwait", "https://cdn.countryflags.com/thumbs/kuwait/flag-square-250.png"));
        list.add(new ModelCountry(3, "Saudi Arabia", "https://cdn.countryflags.com/thumbs/saudi-arabia/flag-400.png"));

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
        });*/

    }

    @Override
    public void populateCountriesListSpinner(List<ModelCountry> countriesList) {
        AdapterCountrySpinner adapterCountrySpinner = new AdapterCountrySpinner(this, 0, countriesList);
        spinnerCountry.setAdapter(adapterCountrySpinner);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setSelectedCountry(countriesList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void ToastOffline() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.view_custom_toast_offline,
                (ViewGroup) findViewById(R.id.lot_toast));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
