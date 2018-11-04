package com.ibtikar.app.bokeh.ui.fragments.search;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.data.adapters.AdapterSearchResultList;
import com.ibtikar.app.bokeh.data.models.ModelSearchResultItem;
import com.ibtikar.app.bokeh.ui.fragments.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends BaseFragment implements SearchMvpView, AdapterSearchResultList.CustomeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.lv_search_result)
    ListView lvCartList;

    Handler mHandler;

    ArrayList<ModelSearchResultItem> searchResultItemArrayList;
    AdapterSearchResultList adapterSearchResultList;

    SearchPresenter presenter;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        searchResultItemArrayList = new ArrayList<>();
        adapterSearchResultList = new AdapterSearchResultList(getContext(),searchResultItemArrayList);
        mHandler = new Handler(Looper.getMainLooper());


        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new SearchPresenter(dataManager);
        presenter.onAttach(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        ((MvpApp) getActivity().getApplication())
                .bus()
                .toObservable()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof String) {
                            //tvSearch.setText((String)object);
                            presenter.loadSearchResult((String) object);
                        }
                    }
                });


        adapterSearchResultList.setCustomButtonListner(this);
        lvCartList.setAdapter(adapterSearchResultList);
        adapterSearchResultList.notifyDataSetChanged();



        return view;

    }



    @Override
    public void addMoreToSearchResultAdapter(final List<ModelSearchResultItem> list) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                adapterSearchResultList.addAll(list);
            }
        });
    }

    @Override
    public void onItemClickListener(Integer productId) {

    }
}
