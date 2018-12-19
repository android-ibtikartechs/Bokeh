package com.ibtikar.app.bokeh.ui.fragments.cart;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.data.adapters.AdapterCartList;
import com.ibtikar.app.bokeh.data.adapters.AdapterReciptList;
import com.ibtikar.app.bokeh.data.models.CartFragmentRefreshTrigger;
import com.ibtikar.app.bokeh.data.models.ModelCartListItem;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;
import com.ibtikar.app.bokeh.ui.activities.payment.PaymentActivity;
import com.ibtikar.app.bokeh.ui.activities.product_details.ProductDetailsActivity;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;
import com.ibtikar.app.bokeh.utils.RxBus;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

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

    @BindView(R.id.progressLoutRecipt)
    ProgressLinearLayout progressLoutReceipt;

    @BindView(R.id.rv_cart_items)
    RecyclerView rvCartItems;

    @BindView(R.id.rv_receipt_list)
    RecyclerView rvReceiptList;

    @BindView(R.id.btn_add_to_cart)
    Button btnBuy;

    @BindView(R.id.tvOrderTotal)
    TextView tvOrderTotal;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    AdapterReciptList adapterReciptList;
    ArrayList<ModelReciptList> reciptListArrayList;

    AdapterCartList adapterCartList;
    private ArrayList<ModelCartListItem> arrayList;

    CartPresenter presenter;

    String orderTotalPrice;

    SwipeRefreshLayout.OnRefreshListener swipeRefreshListner;

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

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, rootView);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        btnBuy.setEnabled(false);
       /* arrayList = new ArrayList<>();
        reciptListArrayList = new ArrayList<>();
        rvCartItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rvReceiptList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        populatRecyclerView();*/


        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                intent.putExtra(StaticValues.KEY_ORDER_TOTAL_PRICE, orderTotalPrice);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadCartList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });*/

        swipeRefreshListner = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList = new ArrayList<>();
                reciptListArrayList = new ArrayList<>();
                rvCartItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
                rvReceiptList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
                populatRecyclerView();
                presenter.loadCartList();
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshListner);



        ((MvpApp) getActivity().getApplication())
                .bus()
                .toObservable()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof CartFragmentRefreshTrigger) {
                            //tvSearch.setText((String)object);
                            swipeRefreshLayout.post(new Runnable() {
                                @Override
                                public void run() {
                                    swipeRefreshLayout.setRefreshing(true);
                                    swipeRefreshListner.onRefresh();
                                }
                            });
                        }
                    }
                });




        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                swipeRefreshListner.onRefresh();
            }
        });
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
    public void addMoreToCartListAdapter(final List<ModelCartListItem> list) {
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
    public void setOrderTotalTxtView(String total) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                tvOrderTotal.setText(total);
                orderTotalPrice = total;
            }
        });
    }

    @Override
    public void increaseCartItemQuantity(int position, Integer currentQuantity) {
        ((ProgressBar)rvCartItems.findViewHolderForLayoutPosition(position).itemView.findViewById(R.id.progress_bar_quantity)).setVisibility(View.GONE);
        ((TextView)rvCartItems.findViewHolderForLayoutPosition(position).itemView.findViewById(R.id.tv_quantity)).setText(String.valueOf(currentQuantity+1));
    }

    @Override
    public void decreaseCartItemQuantity(int position, Integer currentQuantity) {
        ((ProgressBar)rvCartItems.findViewHolderForLayoutPosition(position).itemView.findViewById(R.id.progress_bar_quantity)).setVisibility(View.GONE);
        ((TextView)rvCartItems.findViewHolderForLayoutPosition(position).itemView.findViewById(R.id.tv_quantity)).setText(String.valueOf(currentQuantity-1));
    }

    @Override
    public void showAlertDialogExcedeMaximumQuantityOfCartItem(int itemPosition, Integer maximumQuantity, String productName) {
        ((ProgressBar)rvCartItems.findViewHolderForLayoutPosition(itemPosition).itemView.findViewById(R.id.progress_bar_quantity)).setVisibility(View.GONE);
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setMessage("Sorry ,you are only allowed to purchase a maximum " + maximumQuantity + " quantity of " + "\"" + productName + "\"" + ", during the current promotion")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void reloadOrdersInformation(Integer OrderTotal, List<ModelReciptList> reciptList) {
        adapterReciptList.clear();
        addMoreToReceiptList(reciptList);
        tvOrderTotal.setText(OrderTotal.toString());
    }

    @Override
    public void deleteItemFromCartList(int position) {
        adapterCartList.remove(position);
    }

    @Override
    public void showErrorConnectionView() {
        progressLinearLayout.showError(getResources().getDrawable(R.drawable.ic_if_icon_131_cloud_error_314829), "No Connection",
                "We could not establish a connection with our servers. Try again when you are connected to the interne.",
                "Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadCartList();
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
        btnBuy.setEnabled(true);
    }

    @Override
    public void showEmptyView() {
        progressLinearLayout.showEmpty(getResources().getDrawable(R.drawable.cart_empty),"No Items", "No Items in yor cart yet");
        btnBuy.setEnabled(false);
    }


    @Override
    public void showErrorConnectionViewOrdersInfo() {
        for (int i = 0 ; i<arrayList.size() ; i++)
        {
            ((ProgressBar)rvCartItems.findViewHolderForLayoutPosition(i).itemView.findViewById(R.id.progress_bar_quantity)).setVisibility(View.GONE);
        }
        progressLoutReceipt.showError(getResources().getDrawable(R.drawable.ic_if_icon_131_cloud_error_314829), "No Connection",
                "We could not establish a connection with our servers. Try again when you are connected to the interne.",
                "Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //presenter.loadFirstPage(locationLatLong, intent.getIntExtra(StaticValues.KEY_SHOP_OR_CATEGORY_ID, 0), false, null, intent.getIntExtra(StaticValues.KEY_LIST_TYPE,StaticValues.SHOPS_TYPE));
                        presenter.loadReceitList();
                    }
                });

    }

    @Override
    public void showLoadingViewOrdersInfo() {
        progressLoutReceipt.showLoading();
    }

    @Override
    public void showContentOrdersInfo() {
        progressLoutReceipt.showContent();
    }

    @Override
    public void onCartItemClickListener(ModelProductItem productItem) {
        RxBus.publish(productItem);
        startActivity(new Intent(getActivity(), ProductDetailsActivity.class));
    }

    @Override
    public void onDeleteItemClickListener(int cartItemId, int position) {
        presenter.deleteItemFromCartList(cartItemId, position);
    }

    @Override
    public void onIncreaseQuantity(int cartItemId, int position, Integer currentQuantity) {
        ((ProgressBar)rvCartItems.findViewHolderForLayoutPosition(position).itemView.findViewById(R.id.progress_bar_quantity)).setVisibility(View.VISIBLE);
        presenter.increaseCartItemQuantityPresenter(cartItemId, position, currentQuantity);
        //increaseCartItemQuantity(position,currentQuantity);

    }

    @Override
    public void onDecreaseQuantity(int cartItemId, int position, Integer currentQuantity) {
        if (currentQuantity>=2) {
            ((ProgressBar)rvCartItems.findViewHolderForLayoutPosition(position).itemView.findViewById(R.id.progress_bar_quantity)).setVisibility(View.VISIBLE);
            presenter.decreaseCartItemQuantityPresenter(cartItemId, position, currentQuantity);
            //decreaseCartItemQuantity(position, currentQuantity);
        }
    }
}
