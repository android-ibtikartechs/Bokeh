package com.am.app.bouqeh.ui.activities.about_application;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutApplicationActivity extends BaseActivity implements AboutApplicationMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressActivity)
    ProgressLinearLayout progressLinearLayout;
    @BindView(R.id.web_view_content)
    WebView webViewContent;

    AboutApplicationPresenter presenter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);
        ButterKnife.bind(this);

        intent = getIntent();
        setupActionBar(intent.getStringExtra(StaticValues.KEY_TITLE_ABOUT_APPLICATION_ACTIVITY));

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new AboutApplicationPresenter(dataManager);
        presenter.onAttach(this);

        presenter.loadContent(intent.getIntExtra(StaticValues.KEY_TYPE_ABOUT_APPLICATION_ACTIVITY_CONTENT, StaticValues.FLAG_ABOUT_US_CONTENT));

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

    public void setupActionBar(String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setTitle(title);
    }

    @Override
    public void populateContent(String content) {
        webViewContent.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        WebSettings webSettings2 = webViewContent.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        webViewContent.loadDataWithBaseURL("", content, "text/html", "utf-8", "");

    }

    @Override
    public void showErrorConnectionView() {
        progressLinearLayout.showError(getResources().getDrawable(R.drawable.ic_if_icon_131_cloud_error_314829), "No Connection",
                getString(R.string.connection_error_message),
                getString(R.string.try_again_button), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadContent(intent.getIntExtra(StaticValues.KEY_TYPE_ABOUT_APPLICATION_ACTIVITY_CONTENT, StaticValues.FLAG_ABOUT_US_CONTENT));
                    }
                });
    }

    @Override
    public void showLoadingView() {
        progressLinearLayout.showLoading();
    }

    @Override
    public void showContent() {
        progressLinearLayout.showContent();
    }
}
