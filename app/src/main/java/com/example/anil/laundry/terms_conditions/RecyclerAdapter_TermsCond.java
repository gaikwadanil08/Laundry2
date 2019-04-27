package com.example.anil.laundry.terms_conditions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anil.laundry.R;

import java.util.List;

public class RecyclerAdapter_TermsCond extends RecyclerView.Adapter<RecyclerAdapter_TermsCond.MyViewHolder> {

    private List<ModelClass_TermsCond> list;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerlist_terms_cond, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    public RecyclerAdapter_TermsCond(List<ModelClass_TermsCond> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ModelClass_TermsCond modelClass_termsCond = list.get(i);
        myViewHolder.terms.setText(modelClass_termsCond.getTerms());
        myViewHolder.desc.setText(modelClass_termsCond.getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView terms, desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            terms = (TextView) itemView.findViewById(R.id.tv_terms);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
