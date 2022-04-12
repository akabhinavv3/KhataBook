package com.example.khatabook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatabook.ConatctModel;
import com.example.khatabook.Customer;
import com.example.khatabook.DatabaseHelper.dbmanager;
import com.example.khatabook.Fragment.HomeFragment;
import com.example.khatabook.MainActivity;
import com.example.khatabook.MyDatabaseHelper;
import com.example.khatabook.R;
import com.example.khatabook.YouGave;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    ArrayList<ConatctModel> arrayList;
    Context context1;

    public ContactAdapter(Context context1) {
        this.context1 = context1;
    }

    public ContactAdapter(Context context, ArrayList<ConatctModel> arrayList) {
        this.context1 = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ConatctModel model = arrayList.get(position);

        holder.name.setText(model.getName());
        holder.number.setText(model.getNumber());
        Log.d("tag","onBindviewholder");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context1, "Clicked", Toast.LENGTH_SHORT).show();


                dbmanager myDB = new dbmanager(context1);
                myDB.addrecord(model.getName().toString(),model.getNumber().toString()
                );
                context1.startActivity(new Intent(context1, Customer.class));

            }
        });

       // holder.cardView.setTag(position);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,number;
        CardView cardView;
        public MyViewHolder( View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.contactName);
            number=itemView.findViewById(R.id.mobileNo);
            cardView=itemView.findViewById(R.id.card);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            ConatctModel conatctModel = arrayList.get(position);
            String name = conatctModel.getName();
            String phone = conatctModel.getNumber();
            Toast.makeText(context1, "The position is " + String.valueOf(position) +
                    " Name: " + name + ", Phone:" + phone, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context1, MainActivity.class);
            intent.putExtra("Rname", name);
            intent.putExtra("Rphone", phone);
            context1.startActivity(intent);


        }
    }
}
