package com.ibtikar.app.bokeh.ui.fragments.home;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.ibtikar.app.bokeh.data.models.CategoryItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeMvpView, AdapterCategoriesList.ContainerClickListener, AdapterFeaturedItems.ContainerFeaturedItemsClickListener {
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

    AdapterCategoriesList adapterCategoriesList;

    AdapterFeaturedItems adapterFeaturedItems;

    private ArrayList<CategoryItemModel> arrayList;

    private ArrayList<ModelProductItem> productItemArrayList;

    HomePresenter presenter;
    Handler mHandler;



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
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rvFeaturedItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        populatRecyclerView();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.loadCategories();
        presenter.loadFeaturedItems();
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
    public void onItemClickListener(String id, String title) {

    }

    @Override
    public void onItemClickListener(String id, String title, String imUrl, String price, boolean isSameDayDelivery, String sellerName, boolean isLiked, String description) {

    }
}
