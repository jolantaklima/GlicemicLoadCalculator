package com.example.glicemicloadcalculator;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tab1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText productNameET, glycemicIndexET, carbohydratesET, fiberET;
    Button calculateBtn, showProducts;
    private FirebaseFirestore db;
    Dialog listDialog;
    public ArrayList<Product> popupArrayList = new ArrayList<Product>();


    double glycemicIndex = 0, carbohydrates = 0, fiber = 0;
    double avg;

    private OnFragmentInteractionListener mListener;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1Fragment newInstance(String param1, String param2) {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        listDialog = new Dialog(getContext());
        productNameET = view.findViewById(R.id.productNameET);
        glycemicIndexET = view.findViewById(R.id.glycemicIndexET);
        carbohydratesET = view.findViewById(R.id.carbohydratesET);
        fiberET = view.findViewById(R.id.fiberET);
        calculateBtn = view.findViewById(R.id.calculateBtn);
        showProducts = view.findViewById(R.id.showProducts);


        setUpFirebase();

        //TODO dodać zabezpieczenie przed pustymi EditTextami
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                glycemicIndex = Double.valueOf(glycemicIndexET.getText().toString());
                carbohydrates = Double.valueOf(carbohydratesET.getText().toString());
                fiber = Double.valueOf(fiberET.getText().toString());

                //avg = glycemicIndex - carbohydrates;

                if(fiber > carbohydrates){
                    Toast.makeText(getContext(), "Podano błędne dane", Toast.LENGTH_SHORT).show();
                } else {
                    final Map<String, String> dataMap = new HashMap<>();
                    dataMap.put("name", (String) productNameET.getText().toString());
                    dataMap.put("glycemicIndex", (String) glycemicIndexET.getText().toString());
                    dataMap.put("carbohydrates", (String) carbohydratesET.getText().toString());
                    dataMap.put("fiber", (String) fiberET.getText().toString());

                    popupArrayList.add(new Product(productNameET.getText().toString(),
                            glycemicIndexET.getText().toString(),
                            carbohydratesET.getText().toString(),
                            fiberET.getText().toString()));

                    db.collection("products")
                            //.document((String) productNameET.getText().toString())
                            .add(dataMap)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getActivity(), "Dobrze", Toast.LENGTH_SHORT).show();
                                    //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Źle", Toast.LENGTH_SHORT).show();
                                    //Log.d(TAG, "Błąd zapisu");
                                }
                            });

                    Toast.makeText(getActivity(), "Obliczono", Toast.LENGTH_SHORT).show();
                }

            }
        });

        showProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getActivity(), AllProductsActivity.class);

                intent.putExtra("BUNDLE",popupArrayList);

                startActivity(intent);
            }
        });


        return view;
    }

    private void setUpFirebase() {
        FirebaseApp.initializeApp(getContext());
        db = FirebaseFirestore.getInstance();
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}