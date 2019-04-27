package com.example.anil.laundry.store_locator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anil.laundry.R;
import com.example.anil.laundry.terms_conditions.TermsConditions;
import com.example.anil.laundry.about_us.AboutUs;
import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.contact_us.ContactUs;
import com.example.anil.laundry.download_app.DownloadApp;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.our_service.OurServices;
import com.example.anil.laundry.why_us.WhyUs;

import java.util.ArrayList;
import java.util.List;

public class StoreLocator extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    ImageView iv_back;
    TextView tv_menu;

    Spinner spinnerStore;
    Button btn_submit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_locator);

        findAllIds();
        backButton();
        popupMenu();
        submitButton();

        spinnerStore.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add("Select Store");
        list.add("Karvenagar");
        list.add("Swargate");
        list.add("Katraj");
        list.add("Deccan");
        list.add("Hadapsar");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStore.setAdapter(adapter);
    }

    private void submitButton() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerStore.getSelectedItem().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Select Store ", Toast.LENGTH_LONG).show();
                }else{
                    intent = new Intent(StoreLocator.this, StoreLocationOnMap.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void findAllIds() {
        spinnerStore = (Spinner) findViewById(R.id.spinner_store);
        btn_submit = (Button) findViewById(R.id.btn_submit);
    }

    private void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreLocator.this, TermsConditions.class);
                startActivity(intent);
            }
        });
    }

    private void popupMenu() {
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(StoreLocator.this, v);
                popup.setOnMenuItemClickListener(StoreLocator.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.store_locator:
                Intent intent = new Intent(StoreLocator.this, StoreLocator.class);
                startActivity(intent);
                return true;
            case R.id.price_list:
                intent = new Intent(StoreLocator.this, ClothPriceList.class);
                startActivity(intent);
                return true;
            case R.id.terms_cond:
                intent = new Intent(StoreLocator.this, TermsConditions.class);
                startActivity(intent);
                return true;
            case R.id.why_us:
                intent = new Intent(StoreLocator.this, WhyUs.class);
                startActivity(intent);
                return true;
            case R.id.download_app:
                intent = new Intent(StoreLocator.this, DownloadApp.class);
                startActivity(intent);
                return true;
            case R.id.about_us:
                intent = new Intent(StoreLocator.this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.our_services:
                intent = new Intent(StoreLocator.this, OurServices.class);
                startActivity(intent);
                return true;
            case R.id.how_it_works:
                intent = new Intent(StoreLocator.this, HowItWorks.class);
                startActivity(intent);
                return true;
            case R.id.contact_us:
                intent = new Intent(StoreLocator.this, ContactUs.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
