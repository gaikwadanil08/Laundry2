package com.example.anil.laundry.about_us;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.contact_us.ContactUs;
import com.example.anil.laundry.download_app.DownloadApp;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.our_service.OurServices;
import com.example.anil.laundry.R;
import com.example.anil.laundry.store_locator.StoreLocator;
import com.example.anil.laundry.terms_conditions.TermsConditions;
import com.example.anil.laundry.why_us.ModelClass_WhyUs;
import com.example.anil.laundry.why_us.RecyclerAdapter_WhyUs;
import com.example.anil.laundry.why_us.WhyUs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AboutUs extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView iv_back;
    TextView tv_menu;
    RecyclerAdapter_AboutUs recyclerAdapter_aboutUs;
    RecyclerView recyclerView;

    private List<ModelClass_AboutUs> list = new ArrayList<>();

    private static final String url = "http://mdconstructionpune.com/laundrydata/get_json_data_tblAboutUs.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        popupMenu();
        backButton();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter_AboutUs recyclerAdapter_aboutUs = new RecyclerAdapter_AboutUs(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(recyclerAdapter_aboutUs);

        loadRecyclerViewData();

    }
    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Data Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("server_response_AboutUs");

                            for(int i = 0; i<jsonArray.length();i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                ModelClass_AboutUs modelClass_aboutUs = new ModelClass_AboutUs(
                                        o.getString("heading"),
                                        o.getString("desc")
                                );
                                list.add(modelClass_aboutUs);
                            }

                            recyclerAdapter_aboutUs = new RecyclerAdapter_AboutUs(list);
                            recyclerView.setAdapter(recyclerAdapter_aboutUs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void popupMenu() {
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(AboutUs.this, v);
                popup.setOnMenuItemClickListener(AboutUs.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    public void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUs.this, TermsConditions.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.price_list:
                Intent intent = new Intent(AboutUs.this, ClothPriceList.class);
                startActivity(intent);
                return true;
            case R.id.store_locator:
                intent = new Intent(AboutUs.this, StoreLocator.class);
                startActivity(intent);
                return true;
            case R.id.terms_cond:
                intent = new Intent(AboutUs.this, TermsConditions.class);
                startActivity(intent);
                return true;
            case R.id.why_us:
                intent = new Intent(AboutUs.this, WhyUs.class);
                startActivity(intent);
                return true;
            case R.id.download_app:
                intent = new Intent(AboutUs.this, DownloadApp.class);
                startActivity(intent);
                return true;
            case R.id.about_us:
                intent = new Intent(AboutUs.this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.our_services:
                intent = new Intent(AboutUs.this, OurServices.class);
                startActivity(intent);
                return true;
            case R.id.how_it_works:
                intent = new Intent(AboutUs.this, HowItWorks.class);
                startActivity(intent);
                return true;
            case R.id.contact_us:
                intent = new Intent(AboutUs.this, ContactUs.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

}
