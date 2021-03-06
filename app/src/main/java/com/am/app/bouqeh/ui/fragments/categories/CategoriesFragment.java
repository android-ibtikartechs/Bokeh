package com.am.app.bouqeh.ui.fragments.categories;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.data.adapters.AdapterCategoriesList;
import com.am.app.bouqeh.data.models.OccasionItemModel;
import com.am.app.bouqeh.ui.activities.products_list.ProductsListActivity;
import com.am.app.bouqeh.ui.fragments.base.BaseFragment;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends BaseFragment implements CategoriesMvpView, AdapterCategoriesList.ContainerClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;

    @BindView(R.id.progressActivity)
    ProgressLinearLayout progressLinearLayout;

    AdapterCategoriesList adapterCategoriesList;

    private ArrayList<OccasionItemModel> arrayList;

    CategoriesPresenter presenter;
    Handler mHandler;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
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
        presenter = new CategoriesPresenter(dataManager);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mHandler = new Handler(Looper.getMainLooper());
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);
        arrayList = new ArrayList<>();
        rvCategories.setLayoutManager(new GridLayoutManager(getContext(), 3));
        populatRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.loadCategories();
        super.onViewCreated(view, savedInstanceState);
    }

    private void populatRecyclerView() {
        adapterCategoriesList = new AdapterCategoriesList(arrayList, getActivity(), true);
        adapterCategoriesList.setCustomButtonListner(this);
        rvCategories.setAdapter(adapterCategoriesList);
        adapterCategoriesList.notifyDataSetChanged();
    }

    @Override
    public void addMoreToCategoryAdapter(final List<OccasionItemModel> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterCategoriesList.addAll(list);
            }
        });
    }

    @Override
    public void showErrorConnectionView() {
        progressLinearLayout.showError(getActivity().getResources().getDrawable(R.drawable.ic_if_icon_131_cloud_error_314829), getString(R.string.no_connection_title),
                getString(R.string.connection_error_message),
                getString(R.string.try_again_button), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadCategories();
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
    public void onItemClickListener(Integer id, String title) {
        Intent intent = new Intent(getActivity(), ProductsListActivity.class);
        intent.putExtra(StaticValues.KEY_LIST_TYPE, StaticValues.CATEGORY_TYPE);
        intent.putExtra(StaticValues.KEY_SHOP_OR_CATEGORY_ID, id);
        intent.putExtra(StaticValues.KEY_SHOP_OR_CATEGORY_TITLE, title);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
