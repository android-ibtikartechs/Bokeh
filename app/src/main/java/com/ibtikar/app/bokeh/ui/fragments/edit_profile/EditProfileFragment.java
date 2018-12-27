package com.ibtikar.app.bokeh.ui.fragments.edit_profile;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.adapters.AdapterGenderSpinner;
import com.ibtikar.app.bokeh.data.models.ModelGender;
import com.ibtikar.app.bokeh.ui_utilities.DatePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment implements EditProfileMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.gender_spinner)
    Spinner spinnerGender;

    @BindView(R.id.btn_update)
    Button btnUpdate;

    @BindView(R.id.et_date_birth)
    EditText etDateOfBirth;

    @BindView(R.id.et_firstName)
    EditText etFirstName;

    @BindView(R.id.et_last_name)
    EditText etLastName;

    @BindView(R.id.et_email_address)
    EditText etEmailAddress;

    @BindView(R.id.et_mob_num)
    EditText etMobileNumber;

    int selectedGender = 0;

    final int DATE_DIALOG_ID = 999;
    int year, month, day;
    Calendar c;

    Handler mHandler;
    EditProfilePresenter presenter;
    ProgressDialog progressDialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mHandler = new Handler(Looper.getMainLooper());
        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new EditProfilePresenter<>(dataManager);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupGinderSpinner();
        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ubdateData(etFirstName.getText().toString(), etLastName.getText().toString(), etMobileNumber.getText().toString(), etDateOfBirth.getText().toString(), selectedGender);
            }
        });

        etDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });

        presenter.getUserLocalData();

    }


    private void setupGinderSpinner() {
        List<ModelGender> list = new ArrayList<>();

        list.add(new ModelGender(0, "Gender" ));
        list.add(new ModelGender(1, "Male"));
        list.add(new ModelGender(2, "Female"));

        AdapterGenderSpinner adapterGenderSpinner = new AdapterGenderSpinner(getContext(), 0, list);
        spinnerGender.setAdapter(adapterGenderSpinner);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                selectedGender = list.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void showProgressDialog(String title) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(title);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void afterUbdateSuccess() {

    }

    @Override
    public void showDialogStatusOfUpdateProfile(String msg) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setMessage(msg)
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void populateUserData(String emailAddress, String firstName, String lastName, String mobNum, String birthDate, Integer gender) {
        if (!birthDate.equals("0000-00-00"))
            etDateOfBirth.setText(birthDate);
        etEmailAddress.setText(emailAddress);
        etFirstName.setText(firstName);
        etLastName.setText(lastName);
        etMobileNumber.setText(mobNum);
        spinnerGender.setSelection(gender);
    }


/*
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }

        return super.onCreateDialog(id);    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            tvDateOfBirth.setText(new StringBuilder().append(year)
                    .append("-").append(month+1).append("-").append(day));
        }
    };
    */

}
