package com.ibtikar.app.bokeh.ui.fragments.account_content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.ui.fragments.account.AccountFragment;
import com.ibtikar.app.bokeh.ui.fragments.my_orders.MyOrdersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAccountContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccountContent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    @BindView(R.id.lout_my_orders)
    LinearLayout btnMyOrders;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_content, container, false);
        ButterKnife.bind(this,view);

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

        return view;
    }

}
