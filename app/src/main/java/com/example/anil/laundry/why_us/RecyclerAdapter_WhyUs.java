package com.example.anil.laundry.why_us;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anil.laundry.R;

import java.util.List;

public class RecyclerAdapter_WhyUs extends RecyclerView.Adapter<RecyclerAdapter_WhyUs.MyViewHolder> {
    private List<ModelClass_WhyUs> list;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerlist_whyus, viewGroup, false);

        return new RecyclerAdapter_WhyUs.MyViewHolder(itemView);
    }

    public RecyclerAdapter_WhyUs(List<ModelClass_WhyUs> list) {
        this.list = list;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ModelClass_WhyUs modelClass_whyUs = list.get(i);
        myViewHolder.heading.setText(modelClass_whyUs.getHeading());
        myViewHolder.desc.setText(modelClass_whyUs.getDesc());

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
