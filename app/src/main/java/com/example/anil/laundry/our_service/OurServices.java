package com.example.anil.laundry.our_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.anil.laundry.about_us.AboutUs;
import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.contact_us.ContactUs;
import com.example.anil.laundry.download_app.DownloadApp;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.R;
import com.example.anil.laundry.store_locator.StoreLocator;
import com.example.anil.laundry.terms_conditions.TermsConditions;
import com.example.anil.laundry.why_us.WhyUs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.JsonArrayRequest;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class OurServices extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView iv_back;
    TextView tv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_services);

        popupMenu();
        backButton();

    }

    private void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OurServices.this, TermsConditions.class);
                startActivity(intent);
            }
        });
    }

    private void popupMenu() {
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(OurServices.this, v);
                popup.setOnMenuItemClickListener(OurServices.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.price_list:
                Intent intent = new Intent(OurServices.this, ClothPriceList.class);
                startActivity(intent);
                return true;
            case R.id.terms_cond:
                intent = new Intent(OurServices.this, TermsConditions.class);
                startActivity(intent);
                return true;
            case R.id.why_us:
                intent = new Intent(OurServices.this, WhyUs.class);
                startActivity(intent);
                return true;
            case R.id.download_app:
                intent = new Intent(OurServices.this, DownloadApp.class);
                startActivity(intent);
                return true;
            case R.id.about_us:
                intent = new Intent(OurServices.this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.our_services:
                intent = new Intent(OurServices.this, OurServices.class);
                startActivity(intent);
                return true;
            case R.id.how_it_works:
                intent = new Intent(OurServices.this, HowItWorks.class);
                startActivity(intent);
                return true;
            case R.id.contact_us:
                intent = new Intent(OurServices.this, ContactUs.class);
                startActivity(intent);
                return true;
            case R.id.store_locator:
                intent = new Intent(OurServices.this, StoreLocator.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
