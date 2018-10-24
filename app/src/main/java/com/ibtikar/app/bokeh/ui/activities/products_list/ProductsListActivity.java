package com.ibtikar.app.bokeh.ui.activities.products_list;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.ui.activities.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListActivity extends BaseActivity implements ProductsListMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);
        setupActionBar();
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
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setLogo(R.drawable.logo_toolbar);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        // TODO : Dummies data
        actionBar.setTitle("Category Name");
    }
}
