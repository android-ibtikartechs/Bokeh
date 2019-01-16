package com.ibtikar.app.bokeh.ui.activities.payment;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.ui.activities.Success.SuccessActivity;
import com.ibtikar.app.bokeh.ui.activities.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends BaseActivity implements PaymentMvpView {

    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;

    @BindView(R.id.btn_checkout)
    LinearLayout btnCheckout;

    @BindView(R.id.progress_bar)
    ProgressBar progressBarChekout;

    @BindView(R.id.radio_group_pay_type)
    RadioGroup radioGroupPayType;

    int pType = 1;





    private Handler mHandler;

    PaymentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        mHandler = new Handler(Looper.getMainLooper());
        ButterKnife.bind(this);

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new PaymentPresenter(dataManager);
        presenter.onAttach(this);

        Intent intent = getIntent();
        String totalPrice = intent.getStringExtra(StaticValues.KEY_ORDER_TOTAL_PRICE);
        tvTotalPrice.setText(totalPrice);

        setupRadioGroup();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checout(pType);
            }
        });



    }

    private void setupRadioGroup() {
        radioGroupPayType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbtn_cash)
                    pType = 1;

                else if (checkedId == R.id.rbtn_payment_online)
                    pType = 2;

            }
        });
    }

    @Override
    public void showLoading() {
        progressBarChekout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBarChekout.setVisibility(View.GONE);
    }

    @Override
    public void transitToSuccessActivity() {
        startActivity(new Intent(PaymentActivity.this, SuccessActivity.class));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
