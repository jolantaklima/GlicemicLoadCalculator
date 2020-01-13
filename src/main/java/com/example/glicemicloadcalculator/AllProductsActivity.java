package com.example.glicemicloadcalculator;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class AllProductsActivity extends AppCompatActivity implements Serializable {

    public ArrayList<Product> popupArrayList = new ArrayList<Product>();
    public ArrayList<Product> productArrayList = new ArrayList<Product>();
    RecyclerView recyclerView;
    PopupRecyclerViewAdapter adapter;
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_popup);
        toolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Lista produktów");

        setUpRecyclerView();

        Bundle bundle = getIntent().getBundleExtra( "BUNDLE" );
        if(bundle != null){
            popupArrayList = (ArrayList<Product>) bundle.getSerializable("PRODUCTS");
        }

        productArrayList = (ArrayList<Product>) getIntent().getSerializableExtra("BUNDLE");

        /*productArrayList.add(new Product("Jabłko", "10", "9", "8"));
        productArrayList.add(new Product("Pomarancza", "5", "19", "8"));
        productArrayList.add(new Product("Brzoskwinia", "7", "4", "3"));*/

        adapter = new PopupRecyclerViewAdapter(AllProductsActivity.this, productArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.popupRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
