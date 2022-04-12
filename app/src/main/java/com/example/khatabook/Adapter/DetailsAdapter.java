package com.example.khatabook.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatabook.Model;
import com.example.khatabook.R;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Model> dataHolder;

    public DetailsAdapter(Context context, ArrayList<Model> dataHolder) {
        this.context = context;
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public DetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.MyViewHolder holder, int position) {
        holder.date.setText(dataHolder.get(position).getDate());
        holder.bal.setText(dataHolder.get(position).getAmount());
        holder.enterDetails.setText(dataHolder.get(position).getAmountDetails());
        Log.e("tag","bindviewholder");
        holder.gave.setText(dataHolder.get(position).getAmount());
        holder.got.setText(dataHolder.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date,time,bal,enterDetails,gave,got;
        public MyViewHolder( View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            bal = itemView.findViewById(R.id.totalBal);
            enterDetails = itemView.findViewById(R.id.enterDetails);
            gave = itemView.findViewById(R.id.moneyGave);
            got = itemView.findViewById(R.id.moneyGot);
        }
    }
}
