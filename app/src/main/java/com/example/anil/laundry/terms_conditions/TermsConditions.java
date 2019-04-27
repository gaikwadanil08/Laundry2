package com.example.anil.laundry.terms_conditions;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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
import com.example.anil.laundry.R;
import com.example.anil.laundry.about_us.AboutUs;
import com.example.anil.laundry.cloth_price_list.ClothPriceList;
import com.example.anil.laundry.contact_us.ContactUs;
import com.example.anil.laundry.download_app.DownloadApp;
import com.example.anil.laundry.how_it_works.HowItWorks;
import com.example.anil.laundry.login_page.DatabaseWorker;
import com.example.anil.laundry.login_page.Login;
import com.example.anil.laundry.our_service.OurServices;
import com.example.anil.laundry.store_locator.StoreLocator;
import com.example.anil.laundry.why_us.WhyUs;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TermsConditions extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    Intent intent;
    TextView tv_menu;
    String tv_terms, tv_desc;
    ImageView iv_back;
    RecyclerAdapter_TermsCond termsCondRecyclerAdapterClass;
    RecyclerView recyclerView;

    private List<ModelClass_TermsCond> list = new ArrayList<>();
    String json_string;
    private static final String url = "http://mdconstructionpune.com/laundrydata/get_json_data_tblTermsCond.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        popupMenu();
        backButton();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter_TermsCond termsCondRecyclerAdapterClass = new RecyclerAdapter_TermsCond(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(termsCondRecyclerAdapterClass);

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
                            JSONArray jsonArray = jsonObject.getJSONArray("server_response_TermsCond");

                            for(int i = 0; i<jsonArray.length();i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                ModelClass_TermsCond termsCondModelClass = new ModelClass_TermsCond(
                                        o.getString("terms"),
                                        o.getString("cond")
                                );
                                list.add(termsCondModelClass);
                            }

                            termsCondRecyclerAdapterClass = new RecyclerAdapter_TermsCond(list);
                            recyclerView.setAdapter(termsCondRecyclerAdapterClass);
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

    public void getData() {
        String type = "Terms_Cond";
        DatabaseWorker databaseWorker = new DatabaseWorker();
        databaseWorker.execute(type);
    }

    private void backButton() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsConditions.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void popupMenu() {
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(TermsConditions.this, v);
                popup.setOnMenuItemClickListener(TermsConditions.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.price_list:
                Intent intent = new Intent(TermsConditions.this, ClothPriceList.class);
                startActivity(intent);
                return true;
            case R.id.terms_cond:
                intent = new Intent(TermsConditions.this,TermsConditions.class);
                startActivity(intent);
                return true;
                case R.id.store_locator:
                intent = new Intent(TermsConditions.this, StoreLocator.class);
                startActivity(intent);
                return true;
            case R.id.why_us:
               intent = new Intent(TermsConditions.this, WhyUs.class);
               startActivity(intent);
                return true;
            case R.id.download_app:
                intent = new Intent(TermsConditions.this, DownloadApp.class);
                startActivity(intent);
                return true;
            case R.id.about_us:
                intent = new Intent(TermsConditions.this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.our_services:
                intent = new Intent(TermsConditions.this, OurServices.class);
                startActivity(intent);
                return true;
            case R.id.how_it_works:
                intent = new Intent(TermsConditions.this, HowItWorks.class);
                startActivity(intent);
                return true;
            case R.id.contact_us:
                intent = new Intent(TermsConditions.this, ContactUs.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }

    }
}
