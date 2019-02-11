package com.am.app.bouqeh.ui.activities.country;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.adapters.AdapterAreaSpinner;
import com.am.app.bouqeh.data.adapters.AdapterCitySpinner;
import com.am.app.bouqeh.data.models.ModelArea;
import com.am.app.bouqeh.data.models.ModelCity;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;
import com.am.app.bouqeh.ui.activities.main.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountrySelectionActivity extends BaseActivity implements CountrySelectionMvpView {

    @BindView(R.id.btn_continue)
    Button btnContinue;

    @BindView(R.id.country_spinner)
    Spinner spinnerCountry;

    @BindView(R.id.load_progress_bar)
    ProgressBar loadingProgressBar;


    @BindView(R.id.cities_spinner)
    Spinner citiesSpinner;

    @BindView(R.id.areas_spinner)
    Spinner areasSpinner;

    Integer selectedAreaId;
    Integer selectedCityId;





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
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        //setupCountrySpinner();

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new CountrySelectionPresenter(dataManager);
        presenter.onAttach(this);
        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionNumber = pinfo.versionCode;
        presenter.checkUpdateStatus(versionNumber);
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
    public void populateCitiesListSpinner(List<ModelCity> citiesList) {
     /*   AdapterCountrySpinner adapterCountrySpinner = new AdapterCountrySpinner(this, 0, countriesList);
        spinnerCountry.setAdapter(adapterCountrySpinner);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setSelectedArea(countriesList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        AdapterCitySpinner adapterCitySpinner = new AdapterCitySpinner(this, 0, citiesList);
        citiesSpinner.setAdapter(adapterCitySpinner);

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupAreasSpinner(citiesList.get(position).getAreas());
                selectedCityId = citiesList.get(position).getId();
                presenter.setSelectedCity(selectedCityId, citiesList.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void setupAreasSpinner(List<ModelArea> list) {

        AdapterAreaSpinner adapterAreaSpinner = new AdapterAreaSpinner(this, 0, list);
        areasSpinner.setAdapter(adapterAreaSpinner);


        areasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), list.get(position).getName(), Toast.LENGTH_SHORT).show();
                selectedAreaId = list.get(position).getId();
                presenter.setSelectedArea(list.get(position).getId(), list.get(position).getName());
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

    @Override
    public void showUpdateStaus(boolean isAppUpdated, boolean isForceUpdateRequired) {
        if (isForceUpdateRequired)
        {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setMessage(R.string.force_update_message)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }


                            finishAffinity();
                        }
                    })
                    .setNegativeButton(R.string.close_app, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                finishAndRemoveTask();
                            }
                            else
                                finishAffinity();
                        }
                    })
                    .setCancelable(false)

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        else if (! isAppUpdated)
        {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setMessage(R.string.update_available)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }


                                finishAffinity();
                        }
                    })
                    .setNegativeButton(R.string.update_litter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.loadCitiesList();
                        }
                    })
                    .setCancelable(false)


                    .show();
        }

        else
            presenter.loadCitiesList();

    }

    @Override
    public void showLoadingProgress() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        btnContinue.setEnabled(false);
    }

    @Override
    public void hideLoadingProgress(boolean isAllDone) {
        loadingProgressBar.setVisibility(View.GONE);
        if (isAllDone)
            btnContinue.setEnabled(true);
    }
}
