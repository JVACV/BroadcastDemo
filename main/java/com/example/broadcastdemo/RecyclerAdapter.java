package com.example.broadcastdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewholder> {
    private ArrayList<IncomingNumber> arrayList;
    public RecyclerAdapter(ArrayList<IncomingNumber> arrayList){
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, final int position) {
    holder.ID.setText(Integer.toString(arrayList.get(position).getId()));
    holder.NUMBER.setText(arrayList.get(position).getNumber());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public static class MyViewholder extends RecyclerView.ViewHolder {
        TextView ID,NUMBER;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.txtId);
            NUMBER = itemView.findViewById(R.id.txtNumber);
        }
    }
}
