package com.example.glicemicloadcalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopupRecyclerViewAdapter extends RecyclerView.Adapter<PopupRecyclerViewHolder>{

    AllProductsActivity allProductsActivity;
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    AdapterView.OnItemClickListener onItemClickListener;


    public PopupRecyclerViewAdapter(AllProductsActivity allProductsActivity, ArrayList<Product> productArrayList){//, OnItemClickListener onItemClickListener) {
        this.allProductsActivity = allProductsActivity;
        this.productArrayList = productArrayList;
        //this.onItemClickListener = onItemClickListener;
    }

    public PopupRecyclerViewAdapter(FragmentActivity activity, ArrayList<Product> productArrayList) {
    }


    @NonNull
    @Override
    public PopupRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(allProductsActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.custom_single_row, parent, false);




        return new PopupRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopupRecyclerViewHolder holder, int i) {
        holder.popUpNameRV.setText(productArrayList.get(i).getName());
        holder.popUpGlycemicIndexRV.setText(productArrayList.get(i).getGlycemicIndex());
        holder.popUpCarbohydratesRV.setText(String.valueOf(productArrayList.get(i).getCarbohydrates()));
        holder.popUpFiberRV.setText(String.valueOf(productArrayList.get(i).getFiber()));
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
}
