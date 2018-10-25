package com.ibtikar.app.bokeh.ui.fragments.home;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.adapters.AdapterCategoriesList;
import com.ibtikar.app.bokeh.data.adapters.AdapterFeaturedItems;
import com.ibtikar.app.bokeh.data.adapters.AdapterShopsList;
import com.ibtikar.app.bokeh.data.adapters.AdapterSliderHome;
import com.ibtikar.app.bokeh.data.models.CategoryItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.ui.activities.products_list.ProductsListActivity;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeMvpView, AdapterCategoriesList.ContainerClickListener, AdapterFeaturedItems.ContainerFeaturedItemsClickListener, AdapterShopsList.ContainerClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;

    @BindView(R.id.rv_featured_items)
    RecyclerView rvFeaturedItems;

    @BindView(R.id.rv_shops)
    RecyclerView rvShopsItem;

    AdapterCategoriesList adapterCategoriesList;

    AdapterFeaturedItems adapterFeaturedItems;

    AdapterShopsList adapterShopsList;

    private ArrayList<CategoryItemModel> arrayList;

    private ArrayList<ModelProductItem> productItemArrayList;

    private ArrayList<ModelShopItem> shopItemArrayList;

    HomePresenter presenter;
    Handler mHandler;

    AdapterSliderHome sliderPagerAdapter;
    @BindView(R.id.image_page_slider)
    ViewPager images_slider;

    @BindView(R.id.image_page_dots)
    TabLayout pages_dots;

    int page_position = 0, slider_size = 0;
    Timer timer;
    CountDownTimer cdt;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new HomePresenter(dataManager);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mHandler = new Handler(Looper.getMainLooper());
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        arrayList = new ArrayList<>();
        productItemArrayList = new ArrayList<>();
        shopItemArrayList = new ArrayList<>();
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rvFeaturedItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rvShopsItem.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        populatRecyclerView();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        images_slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //cancelSlidingforSomeTime();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        timer = new Timer();
        scheduleSlider();

        presenter.loadSlider();
        presenter.loadCategories();
        presenter.loadFeaturedItems();
        presenter.loadShopsItems();
        super.onViewCreated(view, savedInstanceState);
    }

    private void populatRecyclerView() {
        adapterCategoriesList = new AdapterCategoriesList(arrayList, getActivity());
        adapterCategoriesList.setCustomButtonListner(this);
        rvCategories.setAdapter(adapterCategoriesList);
        adapterCategoriesList.notifyDataSetChanged();

        adapterFeaturedItems = new AdapterFeaturedItems(productItemArrayList,getActivity());
        adapterFeaturedItems.setCustomButtonListner(this);
        rvFeaturedItems.setAdapter(adapterFeaturedItems);
        adapterFeaturedItems.notifyDataSetChanged();

        adapterShopsList = new AdapterShopsList(shopItemArrayList,getActivity());
        adapterShopsList.setCustomButtonListner(this);
        rvShopsItem.setAdapter(adapterShopsList);
        adapterShopsList.notifyDataSetChanged();


    }

    public void scheduleSlider() {

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == slider_size) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                images_slider.setCurrentItem(page_position, true);
            }
        };

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 3000);
    }


    @Override
    public void onPause() {
        timer.cancel();
        super.onPause();
    }


    @Override
    public void addMoreToCategoryAdapter(final ArrayList<CategoryItemModel> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterCategoriesList.addAll(list);
            }
        });
    }

    @Override
    public void addMoreToFeaturedItemAdapter(final ArrayList<ModelProductItem> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterFeaturedItems.addAll(list);
            }
        });
    }

    @Override
    public void addMoreToShopsAdapter(final ArrayList<ModelShopItem> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterShopsList.addAll(list);
            }
        });
    }

    @Override
    public void addListToSlider(List<ModelProductItem> products) {
        sliderPagerAdapter = new AdapterSliderHome(getActivity(), products);
        images_slider.setAdapter(sliderPagerAdapter);
       /* images_slider.setClipToPadding(false);
        images_slider.setPadding(60, 0, 60, 0);
        images_slider.setPageMargin(24);*/
        pages_dots.setupWithViewPager(images_slider);
        slider_size = products.size();
    }

    @Override
    public void onItemClickListener(String id, String title) {
        startActivity(new Intent(getActivity(), ProductsListActivity.class));
    }

    @Override
    public void onItemClickListener(String id, String title, String imUrl, Integer price, boolean isSameDayDelivery, String sellerName, boolean isLiked, String description) {

    }

    @Override
    public void onItemShopClickListener(String id, String title) {

    }
}
