package com.example.khatabook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatabook.AddContactModel;
import com.example.khatabook.ConatctModel;
import com.example.khatabook.Contact2Model;
import com.example.khatabook.Model;
import com.example.khatabook.R;

import java.util.ArrayList;

public class AddContactAdapter extends RecyclerView.Adapter<AddContactAdapter.MyViewHolder> {

    Context context;
    private ArrayList<AddContactModel> AddContactList;


    public AddContactAdapter(Context context, ArrayList<AddContactModel> addContactList) {
        this.context = context;
        AddContactList = addContactList;
    }

    public AddContactAdapter(FragmentActivity activity, ArrayList<Contact2Model> contactArrayList) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.contactName.setText(AddContactList.get(position).getName());
        holder.money.setText(AddContactList.get(position).getMoney());

    }

    @Override
    public int getItemCount() {
        //Toast.makeText(context, AddContactList.size(), Toast.LENGTH_SHORT).show();
        return AddContactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView contactName,money;

        public MyViewHolder( View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.contactName);
            money = itemView.findViewById(R.id.money);
        }
    }
}
