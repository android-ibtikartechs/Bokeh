package com.ibtikar.app.bokeh.ui.fragments.cart;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.adapters.AdapterCartList;
import com.ibtikar.app.bokeh.data.adapters.AdapterReciptList;
import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;
import com.ibtikar.app.bokeh.ui.activities.PaymentActivity;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends BaseFragment implements CartMvpView, AdapterCartList.ContainerCartItemsClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.progressActivity)
    ProgressLinearLayout progressLinearLayout;

    @BindView(R.id.rv_cart_items)
    RecyclerView rvCartItems;

    @BindView(R.id.rv_receipt_list)
    RecyclerView rvReceiptList;

    @BindView(R.id.btn_add_to_cart)
    Button btnBuy;

    AdapterReciptList adapterReciptList;
    ArrayList<ModelReciptList> reciptListArrayList;

    AdapterCartList adapterCartList;
    private ArrayList<ModelCartItem> arrayList;

    CartPresenter presenter;

    Handler mHandler;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        presenter = new CartPresenter(dataManager);
        presenter.onAttach(this);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, rootView);
        arrayList = new ArrayList<>();
        reciptListArrayList = new ArrayList<>();
        rvCartItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rvReceiptList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        populatRecyclerView();
        presenter.loadCartList();

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PaymentActivity.class));
            }
        });

        return rootView;
    }

    private void populatRecyclerView() {
        adapterCartList = new AdapterCartList(arrayList, getActivity());
        adapterCartList.setCustomButtonListner(this);
        rvCartItems.setAdapter(adapterCartList);
        adapterCartList.notifyDataSetChanged();

        adapterReciptList = new AdapterReciptList(reciptListArrayList, getActivity());
        rvReceiptList.setAdapter(adapterReciptList);
        adapterReciptList.notifyDataSetChanged();
    }

    @Override
    public void addMoreToCartListAdapter(final List<ModelCartItem> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterCartList.addAll(list);
            }
        });
    }

    @Override
    public void addMoreToReceiptList(List<ModelReciptList> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterReciptList.addAll(list);
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

    @Override
    public void onCartItemClickListener(ModelCartItem productItem) {

    }
}
