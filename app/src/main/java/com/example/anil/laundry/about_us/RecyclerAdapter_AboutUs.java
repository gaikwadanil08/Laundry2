package com.example.anil.laundry.about_us;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anil.laundry.R;
import com.example.anil.laundry.terms_conditions.ModelClass_TermsCond;
import com.example.anil.laundry.terms_conditions.RecyclerAdapter_TermsCond;

import java.util.List;

public class RecyclerAdapter_AboutUs extends RecyclerView.Adapter<RecyclerAdapter_AboutUs.MyViewHolder> {

    private List<ModelClass_AboutUs> list;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerlist_aboutus, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    public RecyclerAdapter_AboutUs(List<ModelClass_AboutUs> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_AboutUs.MyViewHolder myViewHolder, int i) {
        ModelClass_AboutUs modelClass_aboutUs = list.get(i);

        myViewHolder.heading.setText(modelClass_aboutUs.getHeading());
        myViewHolder.desc.setText(modelClass_aboutUs.getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView heading, desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = (TextView) itemView.findViewById(R.id.tv_heading);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
