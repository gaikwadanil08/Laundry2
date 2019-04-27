package com.example.anil.laundry.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.donate_cloth.DonateCloth;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.our_service.OurServices;
import com.example.anil.laundry.profile.Profile;
import com.example.anil.laundry.R;
import com.example.anil.laundry.terms_conditions.TermsConditions;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;
    Button btn_book_pickup;
    ImageView iv_menu;

    private ViewPager mPager;
    private static int currentPage = 0;

    private static int NUM_PAGES = 0;
    private ArrayList<ImageModelSliding> imageModelArrayList;

    private int[] myImageList = new int[]{
            R.drawable.dry_cleaning,
            R.drawable.steam_ironing,
            R.drawable.premium_laundry,
            R.drawable.iron1,
            R.drawable.premium1,
            R.drawable.premium2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        navigationView();
        bookPickup();
        drawerLayout();
        init();
        menuDrawer();

    }

    private void menuDrawer() {
        iv_menu = (ImageView) findViewById(R.id.iv_menu);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                // If navigation drawer is not open yet open it else close it.
                if (!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(Gravity.START);

            }
        });
    }

    private void navigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        Intent intent = new Intent(Dashboard.this, Dashboard.class);
                        startActivity(intent);
                        return true;
                    case R.id.myOrders:
                        intent = new Intent(Dashboard.this, TermsConditions.class);
                        startActivity(intent);
                        return true;
                    case R.id.editProfile:
                        intent = new Intent(Dashboard.this, Profile.class);
                        startActivity(intent);
                        return true;
                    case R.id.rateCard:
                        intent = new Intent(Dashboard.this, ClothPriceList.class);
                        startActivity(intent);
                        return true;
                    case R.id.donateCloth:
                        intent = new Intent(Dashboard.this, DonateCloth.class);
                        startActivity(intent);
                        return true;
                    case R.id.other:
                        intent = new Intent(Dashboard.this, OurServices.class);
                        startActivity(intent);
                        return true;
                    case R.id.support:
                        intent = new Intent(Dashboard.this, HowItWorks.class);
                        startActivity(intent);
                        return true;
                    case R.id.termsOfUse:
                        intent = new Intent(Dashboard.this, TermsConditions.class);
                        startActivity(intent);
                        return true;
                    case R.id.logout:

                        return true;
                    default:
                        return false;
                }

            }
        });
    }

    public void bookPickup() {

        btn_book_pickup = (Button) findViewById(R.id.btn_book_pickup);
        btn_book_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, ClothPriceList.class);
                startActivity(intent);
            }
        });
    }

    public void drawerLayout() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<ImageModelSliding> populateList() {

        ArrayList<ImageModelSliding> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ImageModelSliding imageModel = new ImageModelSliding();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new DashAdapter(Dashboard.this, imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}

