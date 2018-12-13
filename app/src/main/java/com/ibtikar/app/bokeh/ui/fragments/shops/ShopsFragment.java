package com.ibtikar.app.bokeh.ui.fragments.shops;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.data.adapters.AdapterProductsList;
import com.ibtikar.app.bokeh.data.adapters.AdapterShopsList;
import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.ui.activities.products_list.ProductsListActivity;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;
import com.ibtikar.app.bokeh.ui_utilities.CustomRecyclerView;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopsFragment extends BaseFragment implements ShopsMvpView, AdapterShopsList.ContainerClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_shops)
    CustomRecyclerView rvShops;

    @BindView(R.id.progressActivity)
    ProgressLinearLayout progressLinearLayout;

    AdapterShopsList adapterShopsList;
    LinearLayoutManager linearLayoutManager;

    ShopsPresenter presenter;

    private ArrayList<ModelShopItem> arrayList;
    private Handler mHandler;


    public ShopsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopsFragment newInstance(String param1, String param2) {
        ShopsFragment fragment = new ShopsFragment();
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
        mHandler = new Handler(Looper.getMainLooper());
        arrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populatRecyclerView();
        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new ShopsPresenter(dataManager);
        presenter.onAttach(this);


        presenter.loadShops();

    }

    private void populatRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext());

        rvShops.setLayoutManager(linearLayoutManager);
        rvShops.setHasFixedSize(true);

        rvShops.setItemViewCacheSize(20);
        rvShops.setDrawingCacheEnabled(true);
        rvShops.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        adapterShopsList = new AdapterShopsList(arrayList, getContext(), true);
        adapterShopsList.setCustomButtonListner(this);
        rvShops.setAdapter(adapterShopsList);
        adapterShopsList.notifyDataSetChanged();
    }

    @Override
    public void addMoreToAdapter(List<ModelShopItem> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterShopsList.addAll(list);
            }
        });
    }

    @Override
    public void showErrorConnectionView() {
        progressLinearLayout.showError(getResources().getDrawable(R.drawable.ic_if_icon_131_cloud_error_314829), "No Connection",
                "We could not establish a connection with our servers. Try again when you are connected to the interne.",
                "Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadShops();
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
    public void onItemShopClickListener(Integer id, String title) {
        Intent intent = new Intent(getActivity(), ProductsListActivity.class);
        intent.putExtra(StaticValues.KEY_LIST_TYPE, StaticValues.SHOPS_TYPE);
        intent.putExtra(StaticValues.KEY_SHOP_OR_CATEGORY_ID, id);
        intent.putExtra(StaticValues.KEY_SHOP_OR_CATEGORY_TITLE, title);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
