package com.example.anil.laundry.why_us;

import android.app.ProgressDialog;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anil.laundry.about_us.AboutUs;
import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.contact_us.ContactUs;
import com.example.anil.laundry.download_app.DownloadApp;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.our_service.OurServices;
import com.example.anil.laundry.R;
import com.example.anil.laundry.store_locator.StoreLocator;
import com.example.anil.laundry.terms_conditions.TermsConditions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WhyUs extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView tv_menu;
    ImageView iv_back;
    RecyclerAdapter_WhyUs recyclerAdapter_whyUs;
    RecyclerView recyclerView;

    private List<ModelClass_WhyUs> list = new ArrayList<>();
    String json_string;
    private static final String url = "http://mdconstructionpune.com/laundrydata/get_json_data_tblWhyUs.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why_us);

        popupMenu();
        backButton();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter_WhyUs recyclerAdapter_whyUs = new RecyclerAdapter_WhyUs(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(recyclerAdapter_whyUs);

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
                            JSONArray jsonArray = jsonObject.getJSONArray("server_response_WhyUs");

                            for(int i = 0; i<jsonArray.length();i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                ModelClass_WhyUs modelClass_whyUs = new ModelClass_WhyUs(
                                        o.getString("heading"),
                                        o.getString("desc")
                                );
                                list.add(modelClass_whyUs);
                            }

                            recyclerAdapter_whyUs = new RecyclerAdapter_WhyUs(list);
                            recyclerView.setAdapter(recyclerAdapter_whyUs);
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

    public void popupMenu(){
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(WhyUs.this, v);
                popup.setOnMenuItemClickListener(WhyUs.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }
    public void backButton(){
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhyUs.this, TermsConditions.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.price_list:
                Intent intent = new Intent(WhyUs.this, ClothPriceList.class);
                startActivity(intent);
                return true;
            case R.id.terms_cond:
                intent = new Intent(WhyUs.this,TermsConditions.class);
                startActivity(intent);
                return true;
            case R.id.store_locator:
                intent = new Intent(WhyUs.this, StoreLocator.class);
                startActivity(intent);
                return true;
            case R.id.why_us:
                intent = new Intent(WhyUs.this,WhyUs.class);
                startActivity(intent);
                return true;
            case R.id.download_app:
                intent = new Intent(WhyUs.this, DownloadApp.class);
                startActivity(intent);
                return true;
            case R.id.about_us:
                intent = new Intent(WhyUs.this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.our_services:
                intent = new Intent(WhyUs.this, OurServices.class);
                startActivity(intent);
                return true;
            case R.id.how_it_works:
                intent = new Intent(WhyUs.this, HowItWorks.class);
                startActivity(intent);
                return true;
            case R.id.contact_us:
                intent = new Intent(WhyUs.this, ContactUs.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
