package com.example.glicemicloadcalculator;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PopupRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView popUpNameRV, popUpGlycemicIndexRV, popUpCarbohydratesRV, popUpFiberRV;

    public PopupRecyclerViewHolder(View itemView) {
        super(itemView);

        popUpNameRV = itemView.findViewById(R.id.popUpNameRV);
        popUpGlycemicIndexRV = itemView.findViewById(R.id.popUpGlycemicIndexRV);
        popUpCarbohydratesRV = itemView.findViewById(R.id.popUpCarbohydratesRV);
        popUpFiberRV = itemView.findViewById(R.id.popUpFiberRV);
    }

    @Override
    public void onClick(View view) {

    }
}
