package com.example.anil.laundry.cloth_price_list;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.anil.laundry.pickup_delivery.PickupDelivery;
import com.example.anil.laundry.R;
import com.example.anil.laundry.dashboard.Dashboard;
import com.example.anil.laundry.pickup_delivery.TabsAdapter;

public class ClothPriceList extends AppCompatActivity {

    Intent intent;
    TextView tv_next;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_price_list);

        backButton();
        nextButton();
        tabLayout();

    }

    private void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClothPriceList.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }

    private void nextButton() {
        tv_next = (TextView) findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ClothPriceList.this, PickupDelivery.class);
                startActivity(intent);
            }
        });
    }

    public void tabLayout(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("MAN"));
        tabLayout.addTab(tabLayout.newTab().setText("WOMAN"));
        tabLayout.addTab(tabLayout.newTab().setText("KIDS"));
        tabLayout.addTab(tabLayout.newTab().setText("OTHER"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ClothPriceListAdapter clothPriceListAdapter = new ClothPriceListAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(clothPriceListAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
