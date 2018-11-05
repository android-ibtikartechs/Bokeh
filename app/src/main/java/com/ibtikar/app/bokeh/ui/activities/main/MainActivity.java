package com.ibtikar.app.bokeh.ui.activities.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.StaticValues;
import com.ibtikar.app.bokeh.ui.activities.categories_search.CategorySearchActivity;
import com.ibtikar.app.bokeh.ui.fragments.account.AccountFragment;
import com.ibtikar.app.bokeh.ui.fragments.cart.CartFragment;
import com.ibtikar.app.bokeh.ui.fragments.categories.CategoriesFragment;
import com.ibtikar.app.bokeh.ui.fragments.home.HomeFragment;
import com.ibtikar.app.bokeh.ui.fragments.shops.ShopsFragment;
import com.ibtikar.app.bokeh.ui_utilities.NonSwipeableViewPager;
import com.ibtikar.app.bokeh.ui_utilities.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.relativeLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_main)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Animation animShow, animHide;

    ViewPagerAdapter viewPagerAdapter;

    private int[] tabIcons = {R.drawable.logo_tab_layout, R.drawable.ic_shops, R.drawable.ic_categories, R.drawable.ic_account, R.drawable.ic_cart_tab_layout};
    private int[] tabIconsSelected = {R.drawable.logo_tab_layout, R.drawable.ic_shops_selected, R.drawable.ic_categories_selected, R.drawable.ic_account_selected, R.drawable.ic_cart_tab_layout_selected};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //toolbar.setLogo(R.drawable.logo_toolbar);

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

                if (tab.getPosition() == 4) {
                    tabLayout.startAnimation( animHide );
                    tabLayout.setVisibility(View.GONE);
                }

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search :
                //startActivity(ShoppingCartActivity.getStartIntent(this));
                startActivity(new Intent(MainActivity.this, CategorySearchActivity.class));
               /* FragmentManager fm = getSupportFragmentManager();
                SearchDialogFragment searchDialogFragment = new SearchDialogFragment();
                searchDialogFragment.show(fm, "alert"); */
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        if (tabLayout.getSelectedTabPosition() != 0) {
            if (tabLayout.getSelectedTabPosition() == 4)
            {

                /*tabLayout.clearAnimation();
                tabLayout.setVisibility(View.VISIBLE);
                tabLayout.startAnimation(animShow);*/

                TranslateAnimation animate = new TranslateAnimation(0,0,tabLayout.getHeight(),0);
                animate.setDuration(500);

                animate.setFillAfter(true);

                tabLayout.clearAnimation();
                tabLayout.startAnimation(animate);
                tabLayout.setVisibility(View.VISIBLE);

            /*   tabLayout.animate()
                        .translationY(tabLayout.getHeight())
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                tabLayout.clearAnimation();
                                tabLayout.setVisibility(View.VISIBLE);
                            }
                        });*/

            }
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
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(HomeFragment.newInstance("",""),"Home Fragment");
        viewPagerAdapter.addFragment(ShopsFragment.newInstance("",""), "Shops Fragment");
        viewPagerAdapter.addFragment(CategoriesFragment.newInstance("",""),"Categories Fragment");
        viewPagerAdapter.addFragment(AccountFragment.newInstance("",""), "Account Fragment");
        viewPagerAdapter.addFragment(CartFragment.newInstance("",""), "Cart Fragment");
        viewPager.setAdapter(viewPagerAdapter);
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

}
