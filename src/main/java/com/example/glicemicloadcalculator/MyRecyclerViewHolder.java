package com.example.glicemicloadcalculator;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView nameRV, glycemicIndexRV, carbohydratesRV, fiberRV;
    MyRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public MyRecyclerViewHolder(View itemView, MyRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        nameRV = itemView.findViewById(R.id.nameRV);
        glycemicIndexRV = itemView.findViewById(R.id.glycemicIndexRV);
        carbohydratesRV = itemView.findViewById(R.id.carbohydratesRV);
        fiberRV = itemView.findViewById(R.id.fiberRV);
        this.onItemClickListener = onItemClickListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(getAdapterPosition());
    }
}
