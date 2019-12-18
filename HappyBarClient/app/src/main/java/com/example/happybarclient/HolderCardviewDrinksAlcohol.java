package com.example.happybarclient;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HolderCardviewDrinksAlcohol extends RecyclerView.Adapter<HolderCardviewDrinksAlcohol.viewHolder> {

    Context context;
    List<ModelCardviewDrinksAlcohol> listModel;

public HolderCardviewDrinksAlcohol(Context context, List<ModelCardviewDrinksAlcohol> listModel){
    this.listModel = listModel;
    this.context = context;
}

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_drinks_alcohol, parent, false);
        final viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.nameCardview.setText(String.valueOf(listModel.get(position).getNameCardview()));
        holder.precioCardview.setText(String.valueOf(listModel.get(position).getPrecio_cardview()));
        Picasso.get().load(String.valueOf(listModel.get(position).getImage_cardview()))
                .placeholder(R.drawable.background_placeholder_cardview)
                .resize(500, 500)
                .into(holder.imageCardview);
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView nameCardview, precioCardview, comprarDrinkCardview;
        ImageView imageCardview;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameCardview = itemView.findViewById(R.id.name_cardview);
            precioCardview = itemView.findViewById(R.id.precio_cardview);
            imageCardview = itemView.findViewById(R.id.image_cardview);
            comprarDrinkCardview = itemView.findViewById(R.id.comprar_cardview);


            comprarDrinkCardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogQR dialogQR = new DialogQR(context);
                    dialogQR.generateDialog(String.valueOf(nameCardview.getText())).show();
                }
            });
        }
    }
}
