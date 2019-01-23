package com.am.app.bouqeh.ui.fragments.account_content;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.models.BusAccountFragmentBackStack;
import com.am.app.bouqeh.ui.activities.country.CountrySelectionActivity;
import com.am.app.bouqeh.ui.fragments.base.BaseFragment;
import com.am.app.bouqeh.ui.fragments.edit_profile.EditProfileFragment;
import com.am.app.bouqeh.ui.fragments.my_orders.MyOrdersFragment;
import com.am.app.bouqeh.ui.fragments.wishlist.WishlistFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAccountContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccountContent extends BaseFragment implements AccountContentMvpView {
    // TODO: Rename parameter arguments, choose names that match


    @BindView(R.id.lout_my_orders)
    LinearLayout btnMyOrders;

    @BindView(R.id.lout_edit)
    LinearLayout btnEdit;

    @BindView(R.id.lout_wish_list)
    LinearLayout btnWishList;

    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @BindView(R.id.tv_email)
    TextView tvEmail;

    @BindView(R.id.tv_mob_num)
    TextView tvMobNum;

    AccountContentPresenter presenter;
    Handler mHandler;

    @BindView(R.id.btn_logout)
    Button btnLogout;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentAccountContent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAccountContent.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAccountContent newInstance(String param1, String param2) {
        FragmentAccountContent fragment = new FragmentAccountContent();
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
        presenter = new AccountContentPresenter(dataManager);
        presenter.onAttach(this);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_content, container, false);
        ButterKnife.bind(this,view);
        presenter.getProfileData();


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
                trans.replace(R.id.account_fragment_container, new EditProfileFragment());

                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });


        btnMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
                trans.replace(R.id.account_fragment_container, new MyOrdersFragment());

                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        btnWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
                trans.replace(R.id.account_fragment_container, new WishlistFragment());

                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
               /* Fragment currentBackStackFragment = getFragmentManager().findFragmentByTag("visible_fragment");
                if(currentBackStackFragment instanceof TopFragment){
//Add Code
                }*/


                ((MvpApp) getActivity().getApplication())
                        .bus()
                        .send(new BusAccountFragmentBackStack(getFragmentManager().getBackStackEntryCount()));

                Log.d("", "onBackStackChanged: " + getFragmentManager().getBackStackEntryCount());
            }
        });

        return view;
    }

    @Override
    public void setProfileData(String Name, String email, String mobileNumber) {
        tvUserName.setText(Name);
        tvEmail.setText(email);
        tvMobNum.setText(mobileNumber);
    }

    @Override
    public void afterLogout() {
        Intent intent = new Intent(getActivity().getApplicationContext(), CountrySelectionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
