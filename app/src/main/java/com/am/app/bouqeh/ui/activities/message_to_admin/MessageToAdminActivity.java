package com.am.app.bouqeh.ui.activities.message_to_admin;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageToAdminActivity extends BaseActivity implements MessageToAdminMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_email_address)
    EditText etEmailAddress;

    @BindView(R.id.et_message)
    EditText etMessage;

    @BindView(R.id.lout_btn_send_message)
    LinearLayout btnSendMessage;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.main_lout)
    ConstraintLayout mainLout;

    MessageToAdminPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_to_admin);

        ButterKnife.bind(this);

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new MessageToAdminPresenter(dataManager);
        presenter.onAttach(this);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendMessage(etName.getText().toString(), etEmailAddress.getText().toString(), etMessage.getText().toString());
            }
        });


        setupActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setTitle("Help");

    }

    @Override
    public void showSnacBarSuccessSent() {
        Snackbar snackbar = Snackbar
                .make(mainLout, "Message sent, Thank you for sending us", Snackbar.LENGTH_LONG);
        snackbar.show();

        snackbar.addCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                //see Snackbar.Callback docs for event details
                onBackPressed();
            }
        });
    }

    @Override
    public void showSnacBarFailedSent() {
        Snackbar snackbar = Snackbar
                .make(mainLout, "Error Connection", Snackbar.LENGTH_LONG)
                .setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.sendMessage(etName.getText().toString(), etEmailAddress.getText().toString(), etMessage.getText().toString());
                    }
                });

        snackbar.show();
    }

    @Override
    public void showLoadStatus() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadStatus() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
