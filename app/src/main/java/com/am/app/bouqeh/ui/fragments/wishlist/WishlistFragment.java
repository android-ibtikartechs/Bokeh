package com.am.app.bouqeh.ui.fragments.wishlist;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.adapters.AdapterProductsList;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.ui.activities.product_details.ProductDetailsActivity;
import com.am.app.bouqeh.ui.fragments.base.BaseFragment;
import com.am.app.bouqeh.ui_utilities.CustomRecyclerView;
import com.am.app.bouqeh.ui_utilities.paginationStaggardScrollListener;
import com.am.app.bouqeh.utils.PaginationAdapterCallback;
import com.am.app.bouqeh.utils.RxBus;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends BaseFragment implements WishListMvpView, AdapterProductsList.ContainerProductsItemsClickListener, PaginationAdapterCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    AdapterProductsList adapterProductsList;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @BindView(R.id.progressActivity)
    ProgressLinearLayout progressLinearLayout;

    @BindView(R.id.rv_products)
    CustomRecyclerView rvProductsList;



    private static final int PAGE_START = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 20;
    private Integer currentPage = PAGE_START;

    private ArrayList<ModelProductItem> arrayList;
    private Handler mHandler;


    Intent intent;
    WishListPresenter presenter;



    public WishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishlistFragment newInstance(String param1, String param2) {
        WishlistFragment fragment = new WishlistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        ButterKnife.bind(this, view);
        mHandler = new Handler(Looper.getMainLooper());
        arrayList = new ArrayList<>();
        populatRecyclerView();
        implementScrolListener();

        currentPage = PAGE_START;

        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new WishListPresenter(dataManager);
        presenter.onAttach(this);


        presenter.loadFirstPage();


        return view;
    }


    private void populatRecyclerView() {

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rvProductsList.setLayoutManager(staggeredGridLayoutManager);
        rvProductsList.setHasFixedSize(true);

        rvProductsList.setItemViewCacheSize(20);
        rvProductsList.setDrawingCacheEnabled(true);
        rvProductsList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        adapterProductsList = new AdapterProductsList(getContext(), arrayList);
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
    public void onItemClickListener(ModelProductItem modelProductItem) {
        RxBus.publish(modelProductItem);
        startActivity(new Intent(getActivity(), ProductDetailsActivity.class));
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void retryPageLoad() {

    }

    @Override
    public void setLastPageTrue() {

    }

    @Override
    public void addMoreToAdapter(List<ModelProductItem> list) {
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
        progressLinearLayout.showError(getResources().getDrawable(R.drawable.ic_if_icon_131_cloud_error_314829), getString(R.string.no_connection_title),
                getString(R.string.connection_error_message),
                getString(R.string.try_again_button), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadFirstPage();
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

    @Override
    public void showEmptyView() {
        progressLinearLayout.showEmpty(R.drawable.wishlist_empty, getString(R.string.no_items_yet), getString(R.string.no_items_wishlist));
    }
}
