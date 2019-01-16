package com.ibtikar.app.bokeh.ui.fragments.signup;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.main.MainActivity;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;
import com.ibtikar.app.bokeh.ui.fragments.login.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends BaseFragment implements SignupMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.btn_login)
    TextView btnLogin;

    @BindView(R.id.btn_sign_up)
    Button btnSignup;

    @BindView(R.id.et_firstName)
    EditText etFirstName;

    @BindView(R.id.et_lastName)
    EditText ryLastName;

    @BindView(R.id.et_email_address)
    EditText etEmailAddress;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;

    @BindView(R.id.etMobNum)
    EditText etMobNumber;

    @BindView(R.id.et_country_key)
    EditText etCountryKey;

    ProgressDialog progressDialog;

    Handler mHandler;
    SignUpPresenter presenter;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        presenter = new SignUpPresenter(dataManager);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signup(etFirstName.getText().toString(),ryLastName.getText().toString(),etCountryKey.getText().toString()+etMobNumber.getText().toString(), etEmailAddress.getText().toString(), etPassword.getText().toString(), etConfirmPassword.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(0, 0);
                transaction.replace(R.id.registration_fragment_container, new LoginFragment());
                transaction.commit();
            }
        });
        super.onViewCreated(view, savedInstanceState);
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

        //System.out.println("hid p dialog");

    }

    @Override
    public void afterSignUpSuccess() {
       /* Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_down);*/

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setMessage("Success registration, activation link have sent to your email, please active your account then login")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(0, 0);
                        transaction.replace(R.id.registration_fragment_container, new LoginFragment());
                        transaction.commit();
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public void showDialogRequestActivation() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setMessage("your account is not activated, do you want to resend activation link on your email")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        presenter.resendActivation(etEmailAddress.getText().toString());
                    }
                })

                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public void showDialogThisUserExistedAlredy() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setMessage("this user existed already, do you want to login")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(0, 0);
                        transaction.replace(R.id.registration_fragment_container, new LoginFragment());
                        transaction.commit();
                    }
                })
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void showDialogStatusOfSendingActivation(String msg) {
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
