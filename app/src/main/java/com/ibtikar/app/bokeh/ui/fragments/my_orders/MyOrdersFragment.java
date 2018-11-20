package com.ibtikar.app.bokeh.ui.fragments.my_orders;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.adapters.AdapterLastOrdersList;
import com.ibtikar.app.bokeh.data.models.ModelLastOrdersListItem;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrdersFragment extends BaseFragment implements MyOrdersMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.progressActivity)
    ProgressLinearLayout progressLinearLayout;

    @BindView(R.id.rv_my_orders_list)
    RecyclerView rvOrdersList;

    AdapterLastOrdersList adapterOrdersList;
    private ArrayList<ModelLastOrdersListItem> arrayList;

    MyOrdersPresenter presenter;

    Handler mHandler;


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyOrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyOrdersFragment newInstance(String param1, String param2) {
        MyOrdersFragment fragment = new MyOrdersFragment();
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
        presenter = new MyOrdersPresenter(dataManager);
        presenter.onAttach(this);
        mHandler = new Handler(Looper.getMainLooper());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        ButterKnife.bind(this,view);
        arrayList = new ArrayList<>();
        rvOrdersList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        populatRecyclerView();
        presenter.loadOrdersList();
        return view;
    }

    private void populatRecyclerView() {
        adapterOrdersList = new AdapterLastOrdersList(arrayList, getActivity());
        rvOrdersList.setAdapter(adapterOrdersList);
        adapterOrdersList.notifyDataSetChanged();
    }

    @Override
    public void addMoreToOrdresListAdapter(List<ModelLastOrdersListItem> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterOrdersList.addAll(list);
            }
        });
    }

    @Override
    public void showErrorConnectionView() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void showContent() {

    }
}
