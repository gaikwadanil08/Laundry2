package com.example.anil.laundry.cloth_price_list.man;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anil.laundry.R;
import com.example.anil.laundry.about_us.ModelClass_AboutUs;
import com.example.anil.laundry.about_us.RecyclerAdapter_AboutUs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ClothPriceList_Man extends Fragment {
    View v;
    int counter = 0;
    Button btn_addCloth, btn_subCloth;
    TextView tv_clothQty;

    RecyclerAdapter_ClothPriceList_Man recyclerAdapter_clothPriceList_man;
    RecyclerView recyclerView;

    private List<ModelClass_ClothPriceList_Man> list = new ArrayList<>();

    private static final String url = "http://mdconstructionpune.com/laundrydata/get_Json_Data_ClothPriceList.php";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.clothpricelist_man, container, false);

        tv_clothQty = v.findViewById(R.id.tv_clothQty);

        //spinnerService();
        //subQty();
        //addQty();

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter_ClothPriceList_Man recyclerAdapter_clothPriceList_man = new RecyclerAdapter_ClothPriceList_Man(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerAdapter_clothPriceList_man);

        loadRecyclerViewData();

        return v;

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
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
                            JSONArray jsonArray = jsonObject.getJSONArray("server_response_ClothPriceList");

                            for(int i = 0; i<jsonArray.length();i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                ModelClass_ClothPriceList_Man modelClass_clothPriceList_man = new ModelClass_ClothPriceList_Man(
                                        o.getString("cloth_image"),
                                        o.getString("cloth_name"),
                                        o.getString("cloth_price")
                                );
                                list.add(modelClass_clothPriceList_man);
                            }

                            recyclerAdapter_clothPriceList_man = new RecyclerAdapter_ClothPriceList_Man(list);
                            recyclerView.setAdapter(recyclerAdapter_clothPriceList_man);
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

        RequestQueue requestQueue = (RequestQueue) Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void spinnerService() {
        String[] values = {"Dry Clean", "Wash-Iron", "Pre-Laundry", "Steam Iron"};
        Spinner spinner = v.findViewById(R.id.spinner_ClothService);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getActivity()), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }

    private void addQty() {
        btn_addCloth = (Button) v.findViewById(R.id.btn_addCloth);
        btn_addCloth.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                counter++;
                tv_clothQty.setText(" " + counter);
            }
        });
    }

    private void subQty() {
        btn_subCloth = (Button) v.findViewById(R.id.btn_subCloth);
        btn_subCloth.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                if(counter >= 0) {
                    counter--;
                    tv_clothQty.setText(" " + counter);
                }
            }
        });

    }
}
