package com.ibtikar.app.bokeh.ui.activities.product_details;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.adapters.AdapterSliderGalleryProduct;
import com.ibtikar.app.bokeh.data.adapters.AdapterSliderHome;
import com.ibtikar.app.bokeh.data.models.GalleryProductImage;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options.DialogBuyOptionsFragment;
import com.ibtikar.app.bokeh.utils.RxBus;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsMvpView {

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
                RxBus.publish(modelProductItem);
                DialogBuyOptionsFragment dialogBuyOptionsFragment = new DialogBuyOptionsFragment();
                dialogBuyOptionsFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
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
        this.modelProductItem = modelProductItem;
        setupActionBar(modelProductItem.getName());
        if (modelProductItem.getGallery().size() == 0 || modelProductItem.getGallery() == null) {
            List<GalleryProductImage> list = new List<GalleryProductImage>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @NonNull
                @Override
                public Iterator<GalleryProductImage> iterator() {
                    return null;
                }

                @NonNull
                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @NonNull
                @Override
                public <T> T[] toArray(@NonNull T[] a) {
                    return null;
                }

                @Override
                public boolean add(GalleryProductImage galleryProductImage) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(@NonNull Collection<? extends GalleryProductImage> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, @NonNull Collection<? extends GalleryProductImage> c) {
                    return false;
                }

                @Override
                public boolean removeAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public GalleryProductImage get(int index) {
                    return null;
                }

                @Override
                public GalleryProductImage set(int index, GalleryProductImage element) {
                    return null;
                }

                @Override
                public void add(int index, GalleryProductImage element) {

                }

                @Override
                public GalleryProductImage remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @NonNull
                @Override
                public ListIterator<GalleryProductImage> listIterator() {
                    return null;
                }

                @NonNull
                @Override
                public ListIterator<GalleryProductImage> listIterator(int index) {
                    return null;
                }

                @NonNull
                @Override
                public List<GalleryProductImage> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };
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

        if (modelProductItem.getIsLiked()) {
            likeSwitch = true;
            btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_liked));
        }
            else {
                likeSwitch = false;
                btnLike.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_like_normal));
        }



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

}
