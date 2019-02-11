package com.am.app.bouqeh.ui.activities.product_details;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.data.adapters.AdapterSliderGalleryProduct;
import com.am.app.bouqeh.data.models.GalleryProductImage;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;
import com.am.app.bouqeh.ui.activities.registration.RegistrationActivity;
import com.am.app.bouqeh.ui.fragments.dialog_buy_options.DialogBuyOptionsFragment;
import com.am.app.bouqeh.utils.RxBus;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    Disposable disposable;
    ModelProductItem productDetails;

    AdapterSliderGalleryProduct sliderPagerAdapter;
    @BindView(R.id.image_page_slider)
    ViewPager images_slider;

    @BindView(R.id.image_page_dots)
    TabLayout pages_dots;

    @BindView(R.id.tv_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.im_like)
    ImageView btnLike;

    @BindView(R.id.webView_description)
    WebView webViewDescription;

    @BindView(R.id.btn_add_to_cart)
    Button btnAddToCart;

    boolean likeSwitch;

    ProductDetailsPresenter presenter;


    int page_position = 0, slider_size = 0;
    ModelProductItem modelProductItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new ProductDetailsPresenter(dataManager);
        presenter.onAttach(this);

        presenter.loadData();

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToCart();
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.changeLikeStaus(modelProductItem.getId());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupActionBar(String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setLogo(R.drawable.logo_toolbar);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        // TODO : Dummies data
        actionBar.setTitle(title);
    }



    @Override
    public void populateData(ModelProductItem modelProductItem) {
        presenter.isProductLiked(modelProductItem.getId());
        this.modelProductItem = modelProductItem;
        setupActionBar(modelProductItem.getName());
        if (modelProductItem.getGallery().size() == 0 || modelProductItem.getGallery() == null) {
            List<GalleryProductImage> list = new ArrayList<>();
            list.add(new GalleryProductImage(modelProductItem.getImage()));
            addListToSlider(list);
        }
        else
            addListToSlider(modelProductItem.getGallery());

        tvShopName.setText(modelProductItem.getSellername());
        String description;
        byte[] dataF = Base64.decode(modelProductItem.getDetails(), Base64.DEFAULT);
        try {
            description = new String(dataF, "UTF-8");
            populateWebViewDescription(description);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        tvPrice.setText(String.format(Locale.UK,"%d",modelProductItem.getPrice()));
/*
        if (modelProductItem.getIsLiked()) {
            likeSwitch = true;
            btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_liked));
        }
            else {
                likeSwitch = false;
                btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_normal));
        }

*/

    }

    @Override
    public void changeBtnLikeStatus() {
        if (likeSwitch)
        {
            likeSwitch = false;
            btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_normal));
        }
        else
        {
            likeSwitch = true;
            btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_liked));
        }
    }

    @Override
    public void showYouAreNotLoggedInAlert() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar );
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setMessage(R.string.not_loged_in_yet)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        Intent intent = new Intent(ProductDetailsActivity.this, RegistrationActivity.class);
                        intent.putExtra(StaticValues.KEY_SIGN_UP_OR_LOGIN, StaticValues.LOGIN_TYPE);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_from_down, R.anim.slide_to_top);
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
    public void showDialogBuyOptions() {
        RxBus.publish(modelProductItem);
        DialogBuyOptionsFragment dialogBuyOptionsFragment = new DialogBuyOptionsFragment();
        dialogBuyOptionsFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
    }

    @Override
    public void showLikeStatus(boolean likeStatus) {
        likeSwitch = likeStatus;
        if (!likeStatus)
        {
            btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_normal));
        }
        else
        {
            btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_liked));
        }
    }


    public void populateWebViewDescription(String description)
    {
        webViewDescription.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub

                return false;
            }
        });
        WebSettings webSettings2 = webViewDescription.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        webViewDescription.loadDataWithBaseURL("", description, "text/html", "utf-8", "");


    }

    public void addListToSlider(List<GalleryProductImage> products) {
        Log.d("", "addListToSlider: " + products.size());
        sliderPagerAdapter = new AdapterSliderGalleryProduct(this, products);
        images_slider.setAdapter(sliderPagerAdapter);
        pages_dots.setupWithViewPager(images_slider);
        slider_size = products.size();
    }

    private String buildHtml (String text)
    {
        String resultHtml = "<html dir=\"rtl\" lang=\"ar\">\n" +
                "  <head>\n" +
                "    <link rel=\"stylesheet\"\n" +
                "          href=\"https://fonts.googleapis.com/css?family=Cairo\">\n" +
                "    <style>\n" +
                "      body {\n" +
                "        font-family: 'Cairo', sans-serif;\n" +

                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body bgcolor=\"#ffffff\">\n" +
                "    <div>" + text + "</div>\n" +
                "  </body>\n" +
                "</html>";
        return resultHtml;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
