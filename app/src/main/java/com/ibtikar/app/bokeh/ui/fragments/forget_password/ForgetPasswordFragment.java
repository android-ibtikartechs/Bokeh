package com.ibtikar.app.bokeh.ui.fragments.forget_password;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgetPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgetPasswordFragment extends BaseFragment implements ForgetPasswordMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressDialog progressDialog;
    @BindView(R.id.et_email_address)
    EditText etEmail;

    @BindView(R.id.btn_resend_password)
    Button btnResendPassword;

    ForgetPasswordPresenter presenter;



    public ForgetPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgetPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForgetPasswordFragment newInstance(String param1, String param2) {
        ForgetPasswordFragment fragment = new ForgetPasswordFragment();
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
        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new ForgetPasswordPresenter(dataManager);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnResendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.resendPassword(etEmail.getText().toString());
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
        progressDialog.dismiss();
    }

    @Override
    public void afterResendSuccess() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setMessage("password has sent to your email")

                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().onBackPressed();
                    }
                })


                .show();
    }

    @Override
    public void showDialogInvalidData() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setMessage("Invalid data")

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
