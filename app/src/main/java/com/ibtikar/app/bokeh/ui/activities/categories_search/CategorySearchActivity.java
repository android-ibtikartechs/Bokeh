package com.ibtikar.app.bokeh.ui.activities.categories_search;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibtikar.app.bokeh.MvpApp;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.ui.fragments.categories.CategoriesFragment;
import com.ibtikar.app.bokeh.ui.fragments.search.SearchFragment;
import com.ibtikar.app.bokeh.utils.RxBus;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategorySearchActivity extends AppCompatActivity {

    @BindView(R.id.lout_search)
    LinearLayout loutSearch;

    @BindView(R.id.acomp_tv_search)
    EditText aCompTvSearch;

    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        setupActionBar();

        //aCompTvSearch.setHint("\uD83D\uDD0D   Search on Bokeh");
        setupRxListener();

        aCompTvSearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {




                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_NEXT ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN ||
                        keyEvent.getAction() == KeyEvent.KEYCODE_ENTER)
                {
                    ((MvpApp) getApplication())
                            .bus()
                            .send(new String(aCompTvSearch.getText().toString()));
                    return true;
                }


                return false;
            }
        });

/*
        aCompTvSearch.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View arg0, boolean gotfocus)
            {
                // TODO Auto-generated method stub
                if(gotfocus)
                {
                    aCompTvSearch.setCompoundDrawables(null, null, null, null);
                }
                else if(!gotfocus)
                {
                    if(aCompTvSearch.getText().length()==0)
                        aCompTvSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search, 0, 0, 0);
                }
            }
        });*/

        aCompTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment test = (SearchFragment) getSupportFragmentManager().findFragmentByTag("search_fragment");
                if (test != null && test.isVisible()) {
                    //DO STUFF

                }
                else {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(0,0);
                    transaction.replace(R.id.categories_search_fragment_container, new SearchFragment(),"search_fragment");
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    aCompTvSearch.setFocusableInTouchMode(true);
                    if(aCompTvSearch.requestFocus()) {
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInputFromWindow(aCompTvSearch.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
                    }
                }
            }
        });



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        /*
         * When this container fragment is created, we fill it with our first
         * "real" fragment
         */
        transaction.setCustomAnimations(0,0);
        transaction.replace(R.id.categories_search_fragment_container, new CategoriesFragment());
        transaction.commit();


        loutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                /*
                 * When this container fragment is created, we fill it with our first
                 * "real" fragment
                 */
                aCompTvSearch.setEnabled(true);
                transaction.setCustomAnimations(0,0);
                transaction.replace(R.id.categories_search_fragment_container, new SearchFragment(),"search_fragment");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d("", "onBackStackChanged: ");
                int backCount = getSupportFragmentManager().getBackStackEntryCount();
                if (backCount == 0){
                    hideKeyboard();
                    aCompTvSearch.setFocusableInTouchMode(false);
                    aCompTvSearch.clearFocus();
                    aCompTvSearch.getText().clear();
                    Log.d("", "onBackStackChanged: ");
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setLogo(R.drawable.logo_toolbar);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        // TODO : Dummies data
    }

    public void hideKeyboard() {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void setupRxListener() {
        RxTextView.textChanges(aCompTvSearch).subscribe(text -> {
            ((MvpApp) getApplication())
                    .bus()
                    .send(new String(text.toString()));
        });
    }

    private void setupSimpleListener() {
        aCompTvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ((MvpApp) getApplication())
                        .bus()
                        .send(new String(s.toString()));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
