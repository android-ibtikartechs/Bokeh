package com.ibtikar.app.bokeh.ui.fragments.dialog_sort_by;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.data.models.SortByBottomSheetPassingData;
import com.ibtikar.app.bokeh.utils.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortByDialogFragment extends BottomSheetDialogFragment {

    @BindView(R.id.radioGroupAscendDeascend)
    RadioGroup radioGroupAscendDeascend;

    @BindView(R.id.radioAscending)
    RadioButton radioAscending;

    @BindView(R.id.radiodeascending)
    RadioButton radioDeascending;

    @BindView(R.id.radioGroupSortType)
    RadioGroup radioGroupSortType;

    @BindView(R.id.radio_title)
    RadioButton radioButtonTitle;

    @BindView(R.id.radio_price)
    RadioButton radioButtonPrice;

    @BindView(R.id.radio_newest)
    RadioButton radioButtonNewest;

    @BindView(R.id.btnApply)
    Button btnApply;

    private ApplyClickListener customListener;

    SortByBottomSheetPassingData sortByBottomSheetPassingData;

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
        View contentView = View.inflate(getContext(), R.layout.view_sort_dialog, null);
        dialog.setContentView(contentView);
        ButterKnife.bind(this, contentView);

        sortByBottomSheetPassingData = new SortByBottomSheetPassingData(StaticValues.SORT_ASCEND,StaticValues.SORT_TYPE_TITLE);

        radioGroupAscendDeascend.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioAscending)
                    sortByBottomSheetPassingData.setAscendingDeascending(StaticValues.SORT_ASCEND);

                else if (checkedId == R.id.radiodeascending)
                    sortByBottomSheetPassingData.setAscendingDeascending(StaticValues.SORT_DEASCEND);
            }
        });

        radioGroupSortType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_title)
                    sortByBottomSheetPassingData.setSortByType(StaticValues.SORT_TYPE_TITLE);

                else if (checkedId == R.id.radio_price)
                    sortByBottomSheetPassingData.setSortByType(StaticValues.SORT_TYPE_PRICE);

                else if (checkedId == R.id.radio_newest)
                    sortByBottomSheetPassingData.setSortByType(StaticValues.SORT_TYPE_NEWEST);
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                customListener.onApplyClickListener(sortByBottomSheetPassingData);

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
        public void onApplyClickListener(SortByBottomSheetPassingData sortByBottomSheetPassingData);
    }
    public void setCustomButtonListner(ApplyClickListener listener) {
        this.customListener = listener;
    }

}
