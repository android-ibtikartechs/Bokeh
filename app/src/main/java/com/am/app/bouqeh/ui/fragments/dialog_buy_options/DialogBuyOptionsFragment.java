package com.am.app.bouqeh.ui.fragments.dialog_buy_options;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.adapters.AdapterCitySpinner;
import com.am.app.bouqeh.data.adapters.AdapterAreaSpinner;
import com.am.app.bouqeh.data.models.CartFragmentRefreshTrigger;
import com.am.app.bouqeh.data.models.ModelCity;
import com.am.app.bouqeh.data.models.ModelArea;
import com.am.app.bouqeh.ui.fragments.dialog_after_add_to_cart.DialogAfterBuyFragment;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogBuyOptionsFragment extends BottomSheetDialogFragment implements DialogBuyOptionsMvpView {

    @BindView(R.id.tab_layout_date)
    TabLayout tabLayout;

    @BindView(R.id.btnClose)
    ImageView btnClose;

    @BindView(R.id.tv_alert_address_empty)
    TextView tvAlertAddressEmpty;


    @BindView(R.id.btnApply)
    Button btnApply;

    @BindView(R.id.area_spinner)
    Spinner areaSpinner;

    @BindView(R.id.city_spinner)
    Spinner citySpinner;

    @BindView(R.id.rgroup_delivery)
    RadioGroup radioGroupDelivery;

    @BindView(R.id.rbtn_pickup)
    RadioButton rbtnPickup;

    @BindView(R.id.rbtn_Delivery)
    RadioButton rbtnDelivery;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.delivery_lout)
    LinearLayout loutDelivery;

    @BindView(R.id.radioGroupTime)
    RadioGroup radioGroupTime;

    @BindView(R.id.radio_time_one)
    RadioButton radioButtonTimeOne;

    @BindView(R.id.radio_time_two)
    RadioButton radioButtonTimeTwo;

    @BindView(R.id.et_text_address)
    EditText editTextAddress;

    @BindView(R.id.tv_city)
    TextView tvCity;

    @BindView(R.id.tv_area)
    TextView tvArea;

    @BindView(R.id.tv_btn_change_city_area)
    TextView btnChangeCityArea;

    @BindView(R.id.lout_spinner_city_area)
    LinearLayout loutSpinnerCityArea;

    private boolean isCityAreaChanged;

    DialogBuyOptionsPresenter presenter;

    Handler mHandler;

    String selectedDate;
    Integer selectedTime = 1;
    Integer deliveryOrPickup = 1;
    Integer selectedAreaId;
    Integer selectedCityId;


    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }


    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        //super.setupDialog(dialog, style);
        //Get the content View
        View contentView = View.inflate(getContext(), R.layout.view_buy_options_fragment, null);
        dialog.setContentView(contentView);
        ButterKnife.bind(this,contentView);

        if (progressBar != null) {
            progressBar.setIndeterminate(true);
            progressBar.getIndeterminateDrawable().setColorFilter(getActivity().getResources().getColor(R.color.ColorFoshiac), android.graphics.PorterDuff.Mode.MULTIPLY);
            progressBar.setVisibility(View.GONE);
        }

        btnChangeCityArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCityArea();
            }
        });

        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new DialogBuyOptionsPresenter(dataManager);
        presenter.onAttach(this);
        mHandler = new Handler(Looper.getMainLooper());

        //setupAreasSpinner();
        presenter.loadAreasSpinner();
        setupRadioGroupTime();
        setupRadioGroup();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setupTabs();

        selectedCityId = presenter.getSelectedAreaId();
        selectedAreaId = presenter.getSelectedCityId();

        tvArea.setText(presenter.getSelectedAreaName());
        tvCity.setText(presenter.getSelectedCityName());

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.submitAndAddItem();

                RxTextView.textChanges(editTextAddress).subscribe(text -> {
                    ((MvpApp) getActivity().getApplication())
                            .bus()
                            .send(new String(text.toString()));

                    if (text.toString().isEmpty())
                        tvAlertAddressEmpty.setVisibility(View.VISIBLE);
                    else
                        tvAlertAddressEmpty.setVisibility(View.GONE);
                });

                if (editTextAddress.getText().toString().isEmpty())
                    tvAlertAddressEmpty.setVisibility(View.VISIBLE);
                else {
                    tvAlertAddressEmpty.setVisibility(View.GONE);
                    presenter.submitAndAddItem(deliveryOrPickup, selectedAreaId, selectedCityId, editTextAddress.getText().toString(), selectedDate, selectedTime);
                }
            }
        });






        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    private void changeCityArea() {
        loutSpinnerCityArea.setVisibility(View.VISIBLE);
        isCityAreaChanged = true;
    }

    private void setupRadioGroupTime()
    {
        radioGroupTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_time_one)
                    selectedTime = 1;

                else if (checkedId == R.id.radio_time_two)
                    selectedTime = 2;
            }
        });
    }

    private void setupRadioGroup() {
        radioGroupDelivery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbtn_Delivery) {
                    enableDeliverySpinners();
                    deliveryOrPickup = 1;
                }


                else if (checkedId == R.id.rbtn_pickup) {
                    disableDeliverySpinners();
                    deliveryOrPickup = 2;
                }
            }
        });
    }

    private void disableDeliverySpinners() {
        areaSpinner.setEnabled(false);
        citySpinner.setEnabled(false);
        loutDelivery.setVisibility(View.GONE);

    }

    private void enableDeliverySpinners() {
        areaSpinner.setEnabled(true);
        citySpinner.setEnabled(true);
        loutDelivery.setVisibility(View.VISIBLE);
    }

    private void setupAreasSpinner() {
        List<ModelCity> list = new ArrayList<>();
        List<ModelArea> cairoCities = new ArrayList<>();

        cairoCities.add(new ModelArea(1, "Nasr City"));
        cairoCities.add(new ModelArea(2, "El-Manial"));
        cairoCities.add(new ModelArea(3, "Alf Maskan"));


        List<ModelArea> gizaCities = new ArrayList<>();

        gizaCities.add(new ModelArea(1, "6th of October"));
        gizaCities.add(new ModelArea(2, "Maady"));
        gizaCities.add(new ModelArea(3, "Mohandseen"));


        List<ModelArea> alexandriaCities = new ArrayList<>();

        alexandriaCities.add(new ModelArea(1, "El-Agamy"));
        alexandriaCities.add(new ModelArea(2, "Sedy Beshr"));
        alexandriaCities.add(new ModelArea(3, "Al-Asafra"));

        list.add(new ModelCity(1, "Cairo", cairoCities));
        list.add(new ModelCity(2, "Giza", gizaCities));
        list.add(new ModelCity(3, "Alexandria", alexandriaCities));

        AdapterCitySpinner adapterAreaSpinner = new AdapterCitySpinner(getContext(), 0, list);
        areaSpinner.setAdapter(adapterAreaSpinner);

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupCitiesSpinner(list.get(position).getAreas());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupCitiesSpinner(List<ModelArea> list) {

        AdapterAreaSpinner adapterAreaSpinner = new AdapterAreaSpinner(getContext(), 0, list);
        citySpinner.setAdapter(adapterAreaSpinner);



        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), list.get(position).getName(), Toast.LENGTH_SHORT).show();
                selectedCityId = list.get(position).getId();
                tvArea.setText(list.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citySpinner.setSelection(getPositionAreaById(list, presenter.getSelectedAreaId()));
    }

    private int getPositionAreaById(List<ModelArea> list, int selectedAreaId) {
        for (int i = 0; i<list.size(); i++)
        {
            if (selectedAreaId == list.get(i).getId()) {
                return i;
            }
        }
        return 0;

    }

    private int getPositionCityById(List<ModelCity> list, int selectedAreaId) {
        for (int i = 0; i<list.size(); i++)
        {
            if (selectedAreaId == list.get(i).getId()) {
                return i;
            }
        }
        return 0;

    }


    private void setupTabs() {
        List<Date> dates = getDatesBetween();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
        SimpleDateFormat daymonthsimpleDataFormat = new SimpleDateFormat("d");

        for (int i = 0; i<dates.size(); i++)
        {
            tabLayout.addTab(tabLayout.newTab());
            calendar.setTime(dates.get(i));
            tabLayout.getTabAt(i).setText(simpleDateformat.format(dates.get(i)) + "\n" + daymonthsimpleDataFormat.format(dates.get(i)));
            /*if (i==0)
            {
                tabLayout.getTabAt(i).select();
            }*/
        }
        SimpleDateFormat simpleDateformatForDate = new SimpleDateFormat("y-MM-d");
        //Toast.makeText(getActivity(), simpleDateformatForDate.format(dates.get(0)), Toast.LENGTH_SHORT).show();

        selectedDate = simpleDateformatForDate.format(dates.get(0));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Toast.makeText(getActivity(), simpleDateformatForDate.format(dates.get(tab.getPosition())), Toast.LENGTH_SHORT).show();
                //selectedDate = dates.get(tab.getPosition())
                selectedDate = simpleDateformatForDate.format(dates.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                SimpleDateFormat simpleDateformat = new SimpleDateFormat("y-MM-d");
                //Toast.makeText(getActivity(), simpleDateformat.format(dates.get(tab.getPosition())), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static List<Date> getDatesBetween() {

        Date startDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, 1);
        startDate = c.getTime();

        Date endDate = new Date();
        c.setTime(endDate);
        c.add(Calendar.DATE, 10);
        endDate = c.getTime();


        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    @Override
    public void showAreaListSpinner(List<ModelCity> list) {

    }

    @Override
    public void showCityListSpinner(List<ModelArea> list) {

    }

    @Override
    public void setaupAreasSpinner(List<ModelCity> list) {
        AdapterCitySpinner adapterCitySpinner = new AdapterCitySpinner(getContext(), 0, list);
        areaSpinner.setAdapter(adapterCitySpinner);



        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupCitiesSpinner(list.get(position).getAreas());
                selectedAreaId = list.get(position).getId();
                tvCity.setText(list.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        areaSpinner.setSelection(getPositionCityById(list, presenter.getSelectedCityId()));
    }

    @Override
    public void showLoadingProgress() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                btnApply.setVisibility(View.GONE);
               progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideLoadingProgress() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                btnApply.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void finishSubmitting(String message, String productName) {
        ((MvpApp) getActivity().getApplication())
                .bus()
                .send(new CartFragmentRefreshTrigger(1));
        dismiss();
        DialogAfterBuyFragment dialogAfterBuyFragment = DialogAfterBuyFragment.newInstance(message, productName);
        dialogAfterBuyFragment.show(getFragmentManager(), "Bottom Sheet after buy Dialog Fragment");
    }


    @Override
    public void onDestroy() {
        presenter.disposeRxSubscriber();
        super.onDestroy();
    }
}
