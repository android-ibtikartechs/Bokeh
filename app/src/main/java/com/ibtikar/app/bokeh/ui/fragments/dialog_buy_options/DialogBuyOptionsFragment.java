package com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.ui.fragments.dialog_after_add_to_cart.DialogAfterBuyFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogBuyOptionsFragment extends BottomSheetDialogFragment {

    @BindView(R.id.tab_layout_date)
    TabLayout tabLayout;

    @BindView(R.id.btnApply)
    Button btnApply;

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

        setupTabs();
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                DialogAfterBuyFragment dialogAfterBuyFragment = new DialogAfterBuyFragment();
                dialogAfterBuyFragment.show(getFragmentManager(), "Bottom Sheet after buy Dialog Fragment");
            }
        });

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
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
            if (i==0)
            {
                tabLayout.getTabAt(i).select();
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(), tab.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public static List<Date> getDatesBetween() {

        Date startDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
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

}
