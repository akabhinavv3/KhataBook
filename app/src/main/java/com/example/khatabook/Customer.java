package com.example.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.khatabook.Adapter.DetailsAdapter;
import com.example.khatabook.Fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class Customer extends AppCompatActivity {

    Toolbar toolbar;
    Button youGave,youGot;
    RecyclerView Rv;
    MyDatabaseHelper myDB;
    ArrayList<Model> dataHolder;
    DetailsAdapter detailsAdapter;
    private  ArrayList<Model> contactArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        youGave = findViewById(R.id.btn);
        youGot = findViewById(R.id.btn1);


        youGave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer.this,YouGave.class);
                startActivity(intent);
            }
        });

        youGot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Customer.this,YouGot.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(Customer.this);

        contactArrayList = new ArrayList<>();

        //storeDtaInArray();
        Rv = findViewById(R.id.recyclerView);
        Rv.setHasFixedSize(true);
        Log.e("tag","linearlayoutmanager");
        Rv.setLayoutManager(new LinearLayoutManager(Customer.this,RecyclerView.VERTICAL,false));
        Log.e("tag","cursor");
        List<Model> contactList = myDB.readAllData();

        for(Model contact: contactList){

            Toast.makeText(this, ""+contact.getAmount(), Toast.LENGTH_SHORT).show();


            contactArrayList.add(contact);
        }
        Log.e("tag","cursor.moveToNext");
        /*while (cursor.moveToNext()){
            Model obj = new Model(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataHolder.add(obj);
        }*/
        Log.e("tag","detailadapter");
        DetailsAdapter detailsAdapter = new DetailsAdapter(Customer.this,contactArrayList);
        Log.e("tag","setadapter");
        Rv.setAdapter(detailsAdapter);


    }

//    void storeDtaInArray(){
//        Cursor cursor = myDB.readAllData();
//        if (cursor.getCount() == 0){
//            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
//        }else {
//            while (cursor.moveToNext()){
//                Model obj = new Model(cursor.getString(1),cursor.getString(2),cursor.getString(3));
//                dataHolder.add(obj);
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            Intent intent = new Intent(Customer.this, HomeFragment.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}