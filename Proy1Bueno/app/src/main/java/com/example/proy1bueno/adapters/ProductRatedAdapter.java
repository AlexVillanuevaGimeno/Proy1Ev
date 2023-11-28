package com.example.proy1bueno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proy1bueno.R;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.beans.Valoracion;

import java.util.ArrayList;

public class ProductRatedAdapter extends RecyclerView.Adapter<ProductRatedAdapter.ProductRatedViewHolder>{

    ArrayList<Valoracion> lstValoracion;
    Context context;
    public ProductRatedAdapter(ArrayList<Valoracion> lstValoracion) {
        this.lstValoracion = lstValoracion;
    }

    @NonNull
    @Override
    public ProductRatedAdapter.ProductRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_rated,parent,false);
        return new ProductRatedAdapter.ProductRatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRatedAdapter.ProductRatedViewHolder holder, int position) {
            holder.nombreProductAdapter.setText(lstValoracion.get(position).getNombreProducto());
            holder.valoracionMediaAdapter.setText("Puntuación media: " + lstValoracion.get(position).getPromedioValoracion());
    }



    @Override
    public int getItemCount() {
       return lstValoracion.size();
    }

    public class ProductRatedViewHolder extends RecyclerView.ViewHolder{
        TextView nombreProductAdapter;
        TextView valoracionMediaAdapter;
        public ProductRatedViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProductAdapter = itemView.findViewById(R.id.nombreProductAdapter);
            valoracionMediaAdapter = itemView.findViewById(R.id.valoracionMediaAdapter);


        }
    }
}
