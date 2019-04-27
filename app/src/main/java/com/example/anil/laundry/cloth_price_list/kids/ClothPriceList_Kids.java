package com.example.anil.laundry.cloth_price_list.kids;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anil.laundry.R;

import java.util.Objects;

public class ClothPriceList_Kids extends Fragment {
    View v;
    int counter = 0;
    Button btn_addCloth, btn_subCloth;
    TextView tv_clothQty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.clothpricelist_kids,container,false);

        tv_clothQty = (TextView)v.findViewById(R.id.tv_clothQty);
        spinner();
        subQty();
        addQty();

        return v;
    }

    private void spinner(){
        String [] values = {"Dry Clean", "Wash-Iron", "Pre-Laundry", "Steam Iron"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_ClothService);
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
        btn_subCloth = (Button)v.findViewById(R.id.btn_subCloth);
        btn_subCloth.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                counter--;
                tv_clothQty.setText(" " + counter);
            }
        });

    }
}
