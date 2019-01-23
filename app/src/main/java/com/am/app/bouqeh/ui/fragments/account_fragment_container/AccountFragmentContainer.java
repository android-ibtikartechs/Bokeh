package com.am.app.bouqeh.ui.fragments.account_fragment_container;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.ui.fragments.account.AccountFragment;
import com.am.app.bouqeh.ui.fragments.account_content.FragmentAccountContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragmentContainer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragmentContainer extends Fragment implements AccountContainerMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    AccountContainerPresenter presenter;
    Handler mHandler;


    public AccountFragmentContainer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragmentContainer.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragmentContainer newInstance(String param1, String param2) {
        AccountFragmentContainer fragment = new AccountFragmentContainer();
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
        presenter = new AccountContainerPresenter(dataManager);
        presenter.onAttach(this);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_fragment_container, container, false);

        presenter.checkLoginStatus();

        return view;
    }

    @Override
    public void showLoginOrAccountFragment(int accountOrLoginFlag) {
        if (accountOrLoginFlag == StaticValues.FLAG_ACCOUNT_SCREEN)
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.account_fragment_container, new FragmentAccountContent(),"FragmentAccountContent");
            transaction.commit();
        }

        else if (accountOrLoginFlag == StaticValues.FLAG_LOGIN_SCREEN)
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.account_fragment_container, new AccountFragment(),"FragmentLogin");
            transaction.commit();
        }
    }


    public interface OnViewSelected {
        public void onViewSelected(int viewId);
    }


}
