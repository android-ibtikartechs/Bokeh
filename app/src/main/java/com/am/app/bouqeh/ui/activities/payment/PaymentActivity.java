package com.am.app.bouqeh.ui.activities.payment;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.ui.activities.Success.SuccessActivity;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paysky.data.model.SuccessfulCardTransaction;
import io.paysky.data.model.SuccessfulWalletTransaction;
import io.paysky.exception.TransactionException;
import io.paysky.ui.PayButton;

public class PaymentActivity extends BaseActivity implements PaymentMvpView {

    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;

    @BindView(R.id.btn_checkout)
    LinearLayout btnCheckout;

    @BindView(R.id.progress_bar)
    ProgressBar progressBarChekout;

    @BindView(R.id.radio_group_pay_type)
    RadioGroup radioGroupPayType;

    @BindView(R.id.btn_pay)
    Button btnPay;

    int pType = 1;

    PayButton payButton = new PayButton(this);





    private Handler mHandler;

    PaymentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        mHandler = new Handler(Looper.getMainLooper());
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String totalPrice = intent.getStringExtra(StaticValues.KEY_ORDER_TOTAL_PRICE);
        payButton.setProduction(false);
        payButton.setMerchantId("42143");
        payButton.setTerminalId("73299056");
        payButton.setAmount(Double.valueOf(totalPrice)); // Amount
        payButton.setCurrencyCode(818); // Currency Code [Optional]
        payButton.setMerchantSecureHash("63616133323632652D636439312D346435312D623832312D643665666539653633626638"); // Merchant secure hash
        payButton.setTransactionReferenceNumber("1");




        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                payButton.createTransaction(new PayButton.PaymentTransactionCallback() {


                    @Override
                    public void onCardTransactionSuccess(SuccessfulCardTransaction cardTransaction) {
                        Log.d("", "onCardTransactionSuccess: " + cardTransaction.SystemReference);

                    }

                    @Override
                    public void onWalletTransactionSuccess(SuccessfulWalletTransaction walletTransaction) {

                    }

                    @Override
                    public void onError(TransactionException error) {

                    }
                });

            }
        });



        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new PaymentPresenter(dataManager);
        presenter.onAttach(this);



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
                if (checkedId == R.id.rbtn_cash) {
                    pType = 1;
                    btnPay.setVisibility(View.GONE);
                }

                else if (checkedId == R.id.rbtn_payment_online) {
                    pType = 2;
                    btnPay.setVisibility(View.VISIBLE);
                }

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
