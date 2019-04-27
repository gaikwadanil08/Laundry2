package com.example.anil.laundry.download_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anil.laundry.R;
import com.example.anil.laundry.store_locator.StoreLocator;
import com.example.anil.laundry.terms_conditions.TermsConditions;
import com.example.anil.laundry.about_us.AboutUs;
import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.contact_us.ContactUs;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.our_service.OurServices;
import com.example.anil.laundry.why_us.WhyUs;

public class DownloadApp extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ImageView iv_back;
    TextView tv_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_app);

        popupMenu();
        backButton();

    }

    private void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloadApp.this, TermsConditions.class);
                startActivity(intent);
            }
        });
    }

    private void popupMenu() {
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(DownloadApp.this, v);
                popup.setOnMenuItemClickListener(DownloadApp.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.price_list:
                Intent intent = new Intent(DownloadApp.this, ClothPriceList.class);
                startActivity(intent);
                return true;
            case R.id.terms_cond:
                intent = new Intent(DownloadApp.this,TermsConditions.class);
                startActivity(intent);
                return true;
            case R.id.store_locator:
                intent = new Intent(DownloadApp.this, StoreLocator.class);
                startActivity(intent);
                return true;
            case R.id.why_us:
                intent = new Intent(DownloadApp.this, WhyUs.class);
                startActivity(intent);
                return true;
            case R.id.download_app:
                intent = new Intent(DownloadApp.this,DownloadApp.class);
                startActivity(intent);
                return true;
            case R.id.about_us:
                intent = new Intent(DownloadApp.this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.our_services:
                intent = new Intent(DownloadApp.this, OurServices.class);
                startActivity(intent);
                return true;
            case R.id.how_it_works:
                intent = new Intent(DownloadApp.this, HowItWorks.class);
                startActivity(intent);
                return true;
            case R.id.contact_us:
                intent = new Intent(DownloadApp.this, ContactUs.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
