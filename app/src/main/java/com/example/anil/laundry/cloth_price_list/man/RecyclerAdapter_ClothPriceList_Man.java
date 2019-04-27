package com.example.anil.laundry.cloth_price_list.man;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anil.laundry.R;
import com.example.anil.laundry.about_us.RecyclerAdapter_AboutUs;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerAdapter_ClothPriceList_Man extends RecyclerView.Adapter<RecyclerAdapter_ClothPriceList_Man.MyViewHolder> {


    private Context mCtx;
    private List<ModelClass_ClothPriceList_Man> productList;

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerlist_clothprice_man, parent, false);

        return new MyViewHolder(itemView);
    }

    public RecyclerAdapter_ClothPriceList_Man(List<ModelClass_ClothPriceList_Man> list) {
        this.productList = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_ClothPriceList_Man.MyViewHolder holder, int position) {
        ModelClass_ClothPriceList_Man product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getCloth_image())
                .into(holder.iv_clothImage);

        holder.tv_clothName.setText(product.getCloth_name());
        holder.tv_ClothPrice.setText(product.getCloth_price());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_clothName, tv_ClothPrice;
        ImageView iv_clothImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_clothName = itemView.findViewById(R.id.tv_clothName);
            tv_ClothPrice = itemView.findViewById(R.id.tv_ClothPrice);
            iv_clothImage = itemView.findViewById(R.id.iv_clothImage);
        }
    }
}

