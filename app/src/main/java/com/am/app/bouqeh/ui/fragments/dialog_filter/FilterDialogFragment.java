package com.am.app.bouqeh.ui.fragments.dialog_filter;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.FilterByPassingData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterDialogFragment extends BottomSheetDialogFragment {
    @BindView(R.id.btnClose)
    ImageView btnClose;

    @BindView(R.id.btnApply)
    Button btnApply;

    @BindView(R.id.et_price_from)
    EditText etPriceFrom;

    @BindView(R.id.et_price_to)
    EditText etPriceTo;

    private ApplyClickListener customListener;

    FilterByPassingData filterByPassingData;

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
        View contentView = View.inflate(getContext(), R.layout.view_filter_dialog, null);
        dialog.setContentView(contentView);
        ButterKnife.bind(this, contentView);

        filterByPassingData = new FilterByPassingData();



        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPriceFrom.getText().toString().isEmpty() || etPriceTo.getText().toString().isEmpty())
                    Toast.makeText(getActivity(), R.string.please_enter_price_alert, Toast.LENGTH_SHORT).show();
                else {
                    filterByPassingData.setPriceFrom(Integer.valueOf(etPriceFrom.getText().toString()));
                    filterByPassingData.setPriceTo(Integer.valueOf(etPriceTo.getText().toString()));
                    customListener.onApplyClickListener(filterByPassingData);
                    dismiss();
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

    public interface ApplyClickListener {
        public void onApplyClickListener(FilterByPassingData filterByPassingData);
    }
    public void setCustomButtonListner(ApplyClickListener listener) {
        this.customListener = listener;
    }



}
