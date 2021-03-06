package com.am.app.bouqeh.ui.activities.main;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.am.app.bouqeh.MvpApp;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.DataManager;
import com.am.app.bouqeh.data.StaticValues;
import com.am.app.bouqeh.data.models.BusAccountFragmentBackStack;
import com.am.app.bouqeh.ui.activities.about_application.AboutApplicationActivity;
import com.am.app.bouqeh.ui.activities.base.BaseActivity;
import com.am.app.bouqeh.ui.activities.categories_search.CategorySearchActivity;
import com.am.app.bouqeh.ui.fragments.account_fragment_container.AccountFragmentContainer;
import com.am.app.bouqeh.ui.fragments.cart.CartFragment;
import com.am.app.bouqeh.ui.fragments.categories.CategoriesFragment;
import com.am.app.bouqeh.ui.fragments.home.HomeFragment;
import com.am.app.bouqeh.ui.fragments.shops.ShopsFragment;
import com.am.app.bouqeh.ui_utilities.NonSwipeableViewPager;
import com.am.app.bouqeh.ui_utilities.ViewPagerAdapter;
import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.relativeLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_main)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    int accountFragmentBackStackCount;

    AccountFragmentContainer accountFragmentContainer =AccountFragmentContainer.newInstance("","");

    private Animation animShow, animHide;
    private Handler mHandler;

    MainPresenter presenter;


    ViewPagerAdapter viewPagerAdapter;

    private int[] tabIcons = {R.drawable.logo_tab_layout, R.drawable.ic_shops, R.drawable.ic_categories, R.drawable.ic_account, R.drawable.ic_cart_tab_layout};
    private int[] tabIconsSelected = {R.drawable.logo_tab_layout, R.drawable.ic_shops_selected, R.drawable.ic_categories_selected, R.drawable.ic_account_selected, R.drawable.ic_cart_tab_layout_selected};

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler(Looper.getMainLooper());
        ButterKnife.bind(this);
        Fabric.with(this, new Crashlytics());



        //toolbar.setLogo(R.drawable.logo_toolbar);

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new MainPresenter(dataManager);
        presenter.onAttach(this);

        setupActionBar();
        initAnimation();

        viewPager.setOffscreenPageLimit(5);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < 5; i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.view_custom_tab, null);

            switch (i)
            {
                case 0 :
                    //tab.setText("الرئيسية");
                    ((TextView)tab.findViewById(R.id.tab)).setText(getString(R.string.home));
                    //((TextView)tab.findViewById(R.id.tab)).setTextColor(getResources().getColor(R.color.ColorFoshiac));
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.logo_tab_layout);


                    break;
                case 1 :
                    //tab.setText("حسابي");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.myacoount1, 0, 0);
                    ((TextView)tab.findViewById(R.id.tab)).setText("Shops");
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_shops);
                    break;
                case 2 :
                    //tab.setText("اشتراكات");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.coupon1, 0, 0);
                    ((TextView)tab.findViewById(R.id.tab)).setText("Categories");
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_categories);
                    break;
                case 3 :
                    //tab.setText("طلباتي");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cart1, 0, 0);
                    ((TextView)tab.findViewById(R.id.tab)).setText("Account");
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_account);
                    break;

                case 4 :
                    //tab.setText("طلباتي");
                    //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cart1, 0, 0);
                    ((TextView)tab.findViewById(R.id.tab)).setText("Cart");
                    ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_cart_tab_layout);
                    ((TextView)tab.findViewById(R.id.badge)).setVisibility(View.VISIBLE);
                    break;

            }

            tabLayout.getTabAt(i).setCustomView(tab);
            ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i).setSoundEffectsEnabled(false);
            ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i).setBackground(getResources().getDrawable(R.drawable.container_dropshadow));


        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


              /*  if (tab.getPosition()==1 || tab.getPosition() == 3 || tab.getPosition() == 2)
                    startActivity(new Intent(MainActivity.this, RegisterationActivity.class)); */
                // else {
                //((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[tab.getPosition()], 0, 0);
                ((ImageView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab_icon)).setImageResource(tabIconsSelected[tab.getPosition()]);
                //((TextView)((ViewGroup)tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setTextColor(getResources().getColor(R.color.ColorFoshiac));
                //((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(tabIconsSelected[tab.getPosition()]);
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0)
                {

                }



              /*  if (tab.getPosition() == 4) {
                    //tabLayout.startAnimation( animHide );
                    tabLayout.setVisibility(View.GONE);
                }*/

            }
            //}

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //((CustomFontTextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[tab.getPosition()], 0, 0);
                ((ImageView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab_icon)).setImageResource(tabIcons[tab.getPosition()]);
                //((TextView)((ViewGroup)tabLayout.getChildAt(0)).getChildAt(tab.getPosition()).findViewById(R.id.tab)).setTextColor(getResources().getColor(R.color.ColorTextBlack));
                if (tab.getPosition() == 0)
                {

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ((TextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(4).findViewById(R.id.badge)).setText(presenter.getCartItem().toString());

        ((MvpApp) getApplication())
                .bus()
                .toObservable()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof Integer) {
                            //tvSearch.setText((String)object);
                            ((TextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(4).findViewById(R.id.badge)).setText(((Integer)object).toString());
                        }
                    }
                });

        ((MvpApp) getApplication())
                .bus()
                .toObservable()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof BusAccountFragmentBackStack) {
                            //tvSearch.setText((String)object);
                            accountFragmentBackStackCount = ((BusAccountFragmentBackStack) object).getTrigger();
                        }
                    }
                });

        presenter.refreshCartItemCount();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_search :
                startActivity(new Intent(MainActivity.this, CategorySearchActivity.class));
                break;
            case R.id.action_about_us:
                intent = new Intent(MainActivity.this, AboutApplicationActivity.class);
                intent.putExtra(StaticValues.KEY_TITLE_ABOUT_APPLICATION_ACTIVITY, item.getTitle());
                intent.putExtra(StaticValues.KEY_TYPE_ABOUT_APPLICATION_ACTIVITY_CONTENT, StaticValues.FLAG_ABOUT_US_CONTENT);
                startActivity(intent);
                break;
            case R.id.action_privacy_policy:
                intent = new Intent(MainActivity.this, AboutApplicationActivity.class);
                intent.putExtra(StaticValues.KEY_TITLE_ABOUT_APPLICATION_ACTIVITY, item.getTitle());
                intent.putExtra(StaticValues.KEY_TYPE_ABOUT_APPLICATION_ACTIVITY_CONTENT, StaticValues.FLAG_PRIVACY_POLICY_CONTENT);
                startActivity(intent);
                break;

            case R.id.action_terms_of_use:
                intent = new Intent(MainActivity.this, AboutApplicationActivity.class);
                intent.putExtra(StaticValues.KEY_TITLE_ABOUT_APPLICATION_ACTIVITY, item.getTitle());
                intent.putExtra(StaticValues.KEY_TYPE_ABOUT_APPLICATION_ACTIVITY_CONTENT, StaticValues.FLAG_TERMS_INFO_CONTENT);
                startActivity(intent);
                break;
            case R.id.action_contact_us:
                intent = new Intent(MainActivity.this, AboutApplicationActivity.class);
                intent.putExtra(StaticValues.KEY_TITLE_ABOUT_APPLICATION_ACTIVITY, item.getTitle());
                intent.putExtra(StaticValues.KEY_TYPE_ABOUT_APPLICATION_ACTIVITY_CONTENT, StaticValues.FLAG_CONTACT_CONTENT);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (tabLayout.getSelectedTabPosition() != 0 && accountFragmentBackStackCount == 0) {
            viewPager.setCurrentItem(0);
        } else
            super.onBackPressed();
    }

    public void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.logo_toolbar);
    }

    private void setupViewPager(NonSwipeableViewPager viewPager) {
       /* viewPagerAdapter.getItem(3).getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment currentBackStackFragment = getSupportFragmentManager().findFragmentByTag("FragmentAccountContent");
                if(currentBackStackFragment instanceof FragmentAccountContent){
                    MainActivity.super.onBackPressed();
                    Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(HomeFragment.newInstance("",""),"Home Fragment");
        viewPagerAdapter.addFragment(ShopsFragment.newInstance("",""), "Shops Fragment");
        viewPagerAdapter.addFragment(CategoriesFragment.newInstance("",""),"Categories Fragment");
        viewPagerAdapter.addFragment(accountFragmentContainer, "Account Fragment");
        viewPagerAdapter.addFragment(CartFragment.newInstance("",""), "Cart Fragment");
        viewPager.setAdapter(viewPagerAdapter);

/*
        viewPagerAdapter.getItem(3).getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment currentBackStackFragment = getSupportFragmentManager().findFragmentByTag("FragmentAccountContent");
                if(currentBackStackFragment instanceof FragmentAccountContent){
                    MainActivity.super.onBackPressed();
                    Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        //StaticValues.changeLang(getApplicationContext(),"ar");
    }

    public void slideToBottom(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    public void slideToTop(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,-view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    private void initAnimation()
    {
        animShow = AnimationUtils.loadAnimation( this, R.anim.slide_up_tab_layout);
        animHide = AnimationUtils.loadAnimation( this, R.anim.slide_down_tab_layout);
    }

    @Override
    public void refreshCartCount(Integer cartItemsCount) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ((TextView)((ViewGroup) tabLayout.getChildAt(0)).getChildAt(4).findViewById(R.id.badge)).setText(cartItemsCount.toString());
            }
        });
    }
}
