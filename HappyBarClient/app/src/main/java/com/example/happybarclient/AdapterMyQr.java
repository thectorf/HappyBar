package com.example.happybarclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMyQr extends RecyclerView.Adapter<AdapterMyQr.viewHolder> {

    List<ModelMyQR> model;

    public AdapterMyQr(List<ModelMyQR> model) {
        this.model = model;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_my_qrs, parent, false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.nameDrink.setText(String.valueOf(model.get(position).getNameDrink()));
        holder.used.setText("Usado: ");
        holder.used.append(String.valueOf(model.get(position).getUsed()));

        Picasso.get().load(model.get(position).getImage64())
                .placeholder(R.drawable.background_placeholder_cardview_3)
                .into(holder.image64);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView nameDrink, used;
        ImageView image64;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameDrink = itemView.findViewById(R.id.name_cardview_my_qr);
            used = itemView.findViewById(R.id.used_cardview_my_qr);
            image64 = itemView.findViewById(R.id.image64_cardview_my_qr);
        }
    }
}
