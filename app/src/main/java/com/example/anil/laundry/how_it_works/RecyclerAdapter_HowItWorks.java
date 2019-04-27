package com.example.anil.laundry.how_it_works;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anil.laundry.R;

import java.util.List;

public class RecyclerAdapter_HowItWorks extends RecyclerView.Adapter<RecyclerAdapter_HowItWorks.MyViewHolder> {

    private List<ModelClass_HowItWorks> list;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.recyclerlist_howitworks,viewGroup,false);

        return new MyViewHolder(itemView);
    }
    public RecyclerAdapter_HowItWorks(List<ModelClass_HowItWorks> list) {
        this.list = list;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ModelClass_HowItWorks howItWorks_modelClass = list.get(i);
        myViewHolder.heading.setText(howItWorks_modelClass.getHeading());
        myViewHolder.desc.setText(howItWorks_modelClass.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView heading, desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = (TextView) itemView.findViewById(R.id.tv_terms);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
