package com.example.happybarclient.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.happybarclient.HolderCardviewDrinksAlcohol;
import com.example.happybarclient.ModelCardviewDrinksAlcohol;
import com.example.happybarclient.R;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    List<ModelCardviewDrinksAlcohol> listModel;
    HolderCardviewDrinksAlcohol adapter;
    RecyclerView recyclerView;

    public InicioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewFinds(view);

        recyclerView = view.findViewById(R.id.recycler_cardview_drink_alcohol);
        //GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        initialData();

        adapter = new HolderCardviewDrinksAlcohol(getActivity(), listModel);
        recyclerView.setAdapter(adapter);

    }


    private void viewFinds(View view){
    }

    public void initialData(){
        listModel = new ArrayList<>();
        listModel.add(new ModelCardviewDrinksAlcohol("Cuba libre", "10 soles", "https://i.ibb.co/sJ5ttpz/cuba-libre.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Chilcano", "8 soles", "https://i.ibb.co/Rzqhm50/chilcano.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Grass Monkey", "15 soles", "https://i.ibb.co/DrC9ST9/brass-monkey.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Cuba libre", "10 soles", "https://i.ibb.co/sJ5ttpz/cuba-libre.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Chilcano", "8 soles", "https://i.ibb.co/Rzqhm50/chilcano.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Grass Monkey", "15 soles", "https://i.ibb.co/DrC9ST9/brass-monkey.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Cuba libre", "10 soles", "https://i.ibb.co/sJ5ttpz/cuba-libre.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Chilcano", "8 soles", "https://i.ibb.co/Rzqhm50/chilcano.jpg"));
        listModel.add(new ModelCardviewDrinksAlcohol("Grass Monkey", "15 soles", "https://i.ibb.co/DrC9ST9/brass-monkey.jpg"));
    }

}