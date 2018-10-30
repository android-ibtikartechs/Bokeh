package com.ibtikar.app.bokeh.ui.activities.products_list;

import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.adapters.AdapterProductsList;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.BaseActivity;
import com.ibtikar.app.bokeh.ui.fragments.dialog_filter.FilterDialogFragment;
import com.ibtikar.app.bokeh.ui.fragments.dialog_sort_by.SortByDialogFragment;
import com.ibtikar.app.bokeh.ui_utilities.CustomRecyclerView;
import com.ibtikar.app.bokeh.ui_utilities.paginationStaggardScrollListener;
import com.ibtikar.app.bokeh.utils.NetworkChangeReceiver;
import com.ibtikar.app.bokeh.utils.PaginationAdapterCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class ProductsListActivity extends BaseActivity implements ProductsListMvpView, AdapterProductsList.ContainerProductsItemsClickListener, PaginationAdapterCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_filter)
    LinearLayout btnFilter;


    NetworkChangeReceiver networkChangeReceiver;

    AdapterProductsList adapterProductsList;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @BindView(R.id.rv_products)
    CustomRecyclerView rvProductsList;

    @BindView(R.id.btn_sort)
    LinearLayout btnSortBy;

    ProductsListPresenter presenter;


    private static final int PAGE_START = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 20;
    private Integer currentPage = PAGE_START;

    private ArrayList<ModelProductItem> arrayList;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);
        mHandler = new Handler(Looper.getMainLooper());
        setupActionBar();
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
                filterDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });

        btnSortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortByDialogFragment sortByDialogFragment = new SortByDialogFragment();
                sortByDialogFragment.show(getSupportFragmentManager(), "Bottom Sheet sort Dialog Fragment");
            }
        });


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.setPriority(100);
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);


        arrayList = new ArrayList<>();
        populatRecyclerView();
        implementScrolListener();

        currentPage = PAGE_START;





        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new ProductsListPresenter(dataManager);
        presenter.onAttach(this);

        presenter.loadFirstPage();

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

                presenter.loadNextPage(currentPage);
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

    public void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setLogo(R.drawable.logo_toolbar);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        // TODO : Dummies data
        actionBar.setTitle("Category Name");
    }

    @Override
    public void onItemClickListener(Integer id, String title, String imUrl, Integer price, boolean isSameDayDelivery, String sellerName, boolean isLiked, String description) {

    }

    @Override
    public void retryPageLoad() {

    }

    @Override
    public void setLastPageTrue() {
        isLastPage = true;
    }

    @Override
    public void addMoreToAdapter(final ArrayList<ModelProductItem> list) {
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
}
