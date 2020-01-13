package com.example.glicemicloadcalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    Tab2Fragment tab2Fragment;
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    OnItemClickListener onItemClickListener;

    public MyRecyclerViewAdapter(Tab2Fragment tab2Fragment, ArrayList<Product> productArrayList){//, OnItemClickListener onItemClickListener) {
        this.tab2Fragment = tab2Fragment;
        this.productArrayList = productArrayList;
        //this.onItemClickListener = onItemClickListener;
    }

    public MyRecyclerViewAdapter(FragmentActivity activity, ArrayList<Product> productArrayList) {
    }


    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(tab2Fragment.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, viewGroup, false);




        return new MyRecyclerViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder myRecyclerViewHolder, int i) {

        myRecyclerViewHolder.nameRV.setText(productArrayList.get(i).getName());
        myRecyclerViewHolder.glycemicIndexRV.setText(productArrayList.get(i).getGlycemicIndex());
        myRecyclerViewHolder.carbohydratesRV.setText(String.valueOf(productArrayList.get(i).getCarbohydrates()));
        myRecyclerViewHolder.fiberRV.setText(String.valueOf(productArrayList.get(i).getFiber()));

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
