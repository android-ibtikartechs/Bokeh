package com.am.app.bouqeh.ui.fragments.dialog_after_add_to_cart;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.am.app.bouqeh.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DialogAfterBuyFragment extends DialogFragment {

    private static final String ARG_Message = "param1";
    private static final String ARG_PRODUCTNAME = "param2";

    @BindView(R.id.tv_message)
    TextView tvMessage;



    @BindView(R.id.tv_product_name)
    TextView tvProductName;

    @BindView(R.id.btnContinue)
    Button btnOk;

    private String mMessage;
    private String mProductName;



    public static DialogAfterBuyFragment newInstance(String message, String productName) {
        DialogAfterBuyFragment fragment = new DialogAfterBuyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Message, message);
        args.putString(ARG_PRODUCTNAME, productName);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            mMessage = getArguments().getString(ARG_Message);
            mProductName = getArguments().getString(ARG_PRODUCTNAME);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_dialog_go_to_cart, container, false);
        ButterKnife.bind(this, rootView);
        tvMessage.setText(mMessage);
        tvProductName.setText(mProductName);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setGravity(Gravity.CENTER);
        //ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

        return rootView;
    }
}
