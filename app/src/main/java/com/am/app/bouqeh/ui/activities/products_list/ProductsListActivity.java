package com.am.app.bouqeh.ui.activities.products_list;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.FilterByPassingData;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.data.adapters.AdapterProductsList;
import com.am.app.bouqeh.data.models.LocationLatLong;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.data.models.SortByBottomSheetPassingData;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;
import com.am.app.bouqeh.ui.activities.product_details.ProductDetailsActivity;
import com.am.app.bouqeh.ui.fragments.dialog_filter.FilterDialogFragment;
import com.am.app.bouqeh.ui.fragments.dialog_sort_by.SortByDialogFragment;
import com.am.app.bouqeh.ui_utilities.CustomRecyclerView;
import com.am.app.bouqeh.ui_utilities.paginationStaggardScrollListener;
import com.am.app.bouqeh.utils.NetworkChangeReceiver;
import com.am.app.bouqeh.utils.PaginationAdapterCallback;
import com.am.app.bouqeh.utils.RxBus;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListActivity extends BaseActivity implements ProductsListMvpView, AdapterProductsList.ContainerProductsItemsClickListener, PaginationAdapterCallback, SortByDialogFragment.ApplyClickListener, FilterDialogFragment.ApplyClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_filter)
    LinearLayout btnFilter;

    @BindView(R.id.progressActivity)
    ProgressLinearLayout progressLinearLayout;


    NetworkChangeReceiver networkChangeReceiver;

    AdapterProductsList adapterProductsList;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @BindView(R.id.rv_products)
    CustomRecyclerView rvProductsList;

    @BindView(R.id.btn_sort)
    LinearLayout btnSortBy;

    ProductsListPresenter presenter;


    private static final int PAGE_START = 1;

    SortByBottomSheetPassingData mSortByBottomSheetPassingData;
    boolean isSorteRequired;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 20;
    private Integer currentPage = PAGE_START;

    private ArrayList<ModelProductItem> arrayList;
    private Handler mHandler;

    LocationLatLong locationLatLong;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);
        intent = getIntent();
        locationLatLong = new LocationLatLong(30.0659632,31.2021518);
        mHandler = new Handler(Looper.getMainLooper());

        setupActionBar(intent.getStringExtra(StaticValues.KEY_SHOP_OR_CATEGORY_TITLE));

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
                filterDialogFragment.setCustomButtonListner(ProductsListActivity.this::onApplyClickListener);
                filterDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });

        btnSortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortByDialogFragment sortByDialogFragment = new SortByDialogFragment();
                sortByDialogFragment.setCustomButtonListner(ProductsListActivity.this);
                sortByDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet sort Dialog Fragment");
            }
        });




        arrayList = new ArrayList<>();
        populatRecyclerView();
        implementScrolListener();

        currentPage = PAGE_START;





        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new ProductsListPresenter(dataManager);
        presenter.onAttach(this);


        presenter.loadFirstPage(locationLatLong, intent.getIntExtra(StaticValues.KEY_SHOP_OR_CATEGORY_ID, 0),false,null, intent.getIntExtra(StaticValues.KEY_LIST_TYPE,StaticValues.SHOPS_TYPE) );

    }


    private void populatRecyclerView() {

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rvProductsList.setLayoutManager(staggeredGridLayoutManager);
        rvProductsList.setHasFixedSize(true);

        rvProductsList.setItemViewCacheSize(20);
        rvProductsList.setDrawingCacheEnabled(true);
        rvProductsList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        adapterProductsList = new AdapterProductsList(this, arrayList);
        adapterProductsList.setCustomButtonListner(this);
        adapterProductsList.setPagingAdapterCallback(this);
        rvProductsList.setAdapter(adapterProductsList);
        adapterProductsList.notifyDataSetChanged();

    }

    private void implementScrolListener()
    {
        rvProductsList.addOnScrollListener(new paginationStaggardScrollListener(staggeredGridLayoutManager) {
            @Override
            protected void hideCatList() {

            }

            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                presenter.loadNextPage(currentPage, intent.getIntExtra(StaticValues.KEY_SHOP_OR_CATEGORY_ID, 0),isSorteRequired,mSortByBottomSheetPassingData, intent.getIntExtra(StaticValues.KEY_LIST_TYPE,StaticValues.SHOPS_TYPE));
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
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
    public void onItemClickListener(ModelProductItem modelProductItem) {
        RxBus.publish(modelProductItem);
        startActivity(new Intent(ProductsListActivity.this, ProductDetailsActivity.class));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void retryPageLoad() {

    }

    @Override
    public void setLastPageTrue() {
        isLastPage = true;
    }

    @Override
    public void addMoreToAdapter(final List<ModelProductItem> list) {
        arrayList =  new ArrayList<>(list);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterProductsList.addAll(list);
            }
        });
    }

    @Override
    public void addLoadingFooter() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterProductsList.addLoadingFooter();
            }
        });
    }

    @Override
    public void removeLoadingFooter() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterProductsList.removeLoadingFooter();
            }
        });
    }

    @Override
    public void showRetryAdapter() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterProductsList.showRetry(true);
            }
        });
    }

    @Override
    public void setIsLoadingFalse() {
        isLoading = false;
    }

    @Override
    public void showErrorConnectionView() {
        progressLinearLayout.showError(getResources().getDrawable(R.drawable.ic_if_icon_131_cloud_error_314829), "No Connection",
                "We could not establish a connection with our servers. Try again when you are connected to the interne.",
                "Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadFirstPage(locationLatLong, intent.getIntExtra(StaticValues.KEY_SHOP_OR_CATEGORY_ID, 0), false, null, intent.getIntExtra(StaticValues.KEY_LIST_TYPE,StaticValues.SHOPS_TYPE));
                        btnFilter.setEnabled(true);
                        btnSortBy.setEnabled(true);
                    }
                });

        btnFilter.setEnabled(false);
        btnSortBy.setEnabled(false);

    }

    @Override
    public void showLoadingView() {
        progressLinearLayout.showLoading();
        btnFilter.setEnabled(false);
        btnSortBy.setEnabled(false);
    }

    @Override
    public void showContent() {
        progressLinearLayout.showContent();
        btnFilter.setEnabled(true);
        btnSortBy.setEnabled(true);
    }

    @Override
    public void showEmptyView(int categoryOrShop) {
        if (categoryOrShop == StaticValues.CATEGORY_TYPE)
            progressLinearLayout.showEmpty(R.drawable.category_empty_icon, "No items yet", "No Items in this category yet.");
        else
            progressLinearLayout.showEmpty(R.drawable.coming_soon_icon, "No items yet", "this shop have no items yet.");

        btnFilter.setEnabled(false);
        btnSortBy.setEnabled(false);
    }

    @Override
    public void onApplyClickListener(SortByBottomSheetPassingData sortByBottomSheetPassingData) {
        adapterProductsList.clear();
        currentPage=1;
        mSortByBottomSheetPassingData = sortByBottomSheetPassingData;
        isSorteRequired = true;
        presenter.loadFirstPage(locationLatLong, intent.getIntExtra(StaticValues.KEY_SHOP_OR_CATEGORY_ID, 0), true, sortByBottomSheetPassingData, intent.getIntExtra(StaticValues.KEY_LIST_TYPE,StaticValues.SHOPS_TYPE));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public void onApplyClickListener(FilterByPassingData filterByPassingData) {
        //Toast.makeText(this, "filter done", Toast.LENGTH_SHORT).show();
        adapterProductsList.clear();
        presenter.loadFilteredData(arrayList,filterByPassingData.getPriceFrom(), filterByPassingData.getPriceTo());
    }
}
