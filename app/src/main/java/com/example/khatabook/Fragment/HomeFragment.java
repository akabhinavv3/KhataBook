package com.example.khatabook.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khatabook.Adapter.AddContactAdapter;
import com.example.khatabook.Adapter.DetailsAdapter;
import com.example.khatabook.AddContactModel;
import com.example.khatabook.Contact;
import com.example.khatabook.Contact2Model;
import com.example.khatabook.Customer;
import com.example.khatabook.DatabaseHelper.dbmanager;
import com.example.khatabook.MainActivity;
import com.example.khatabook.Model;
import com.example.khatabook.MyDatabaseHelper;
import com.example.khatabook.R;
import com.example.khatabook.RequestMoney;
import com.example.khatabook.ViewReport;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    Button addContact;
    TextView bottomSheet;
    TextView viewReport;
    ImageView requestMoney;
    RecyclerView Rv;
    dbmanager myDB;
    ArrayList<Contact2Model> dataHolder;
    DetailsAdapter detailsAdapter;
    private  ArrayList<Contact2Model> contactArrayList;
    private ArrayList<AddContactModel> AddContactList;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addContact = view.findViewById(R.id.add_customer);
        viewReport = view.findViewById(R.id.viewReport);
        bottomSheet = view.findViewById(R.id.bottomSheet);
        requestMoney = view.findViewById(R.id.requestMoney);
        Rv = view.findViewById(R.id.RV);
        Rv.setHasFixedSize(true);
        Log.e("tag","Rv");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Rv.setLayoutManager(layoutManager);
        Log.e("tag2","adapter");
        //Toast.makeText(getActivity(),AddContactList.size(), Toast.LENGTH_SHORT).show();
        AddContactAdapter addContactAdapter = new AddContactAdapter(getActivity(),AddContactList);
        Log.e("tag3","setadapter");
        Rv.setAdapter(addContactAdapter);

        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra("Rname");
        String phone = intent.getStringExtra("Rphone");

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact.class);
                startActivity(intent);
            }
        });

        viewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewReport.class);
                startActivity(intent);
            }
        });

        bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });

        requestMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RequestMoney.class);
                startActivity(intent);
            }
        });

        myDB = new dbmanager(getContext());

        contactArrayList = new ArrayList<>();

      /*  //storeDtaInArray();
        Rv = findViewById(R.id.recyclerView);
        Rv.setHasFixedSize(true);
        Log.e("tag","linearlayoutmanager");
        Rv.setLayoutManager(new LinearLayoutManager(Customer.this,RecyclerView.VERTICAL,false));
        Log.e("tag","cursor");
      */  List<Contact2Model> contactList = myDB.readAllData();

        for(Contact2Model contact: contactList){



            Toast.makeText(getContext(), ""+contact.getName(), Toast.LENGTH_SHORT).show();

            Log.d("tag","cursor.moveToNext"+contact.getName());
            contactArrayList.add(contact);
        }
        Log.e("tag","cursor.moveToNext");
        /*while (cursor.moveToNext()){
            Model obj = new Model(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataHolder.add(obj);
        }*/
/*
        Log.e("tag","detailadapter");
        DetailsAdapter detailsAdapter = new DetailsAdapter(Customer.this,contactArrayList);
        Log.e("tag","setadapter");
        Rv.setAdapter(detailsAdapter);
*/


        return view;
    }

    //bottom sheet
    private void showDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);
        LinearLayout buttonLayout  = dialog.findViewById(R.id.linearButton);

        buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"clicked",Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);


    }


}