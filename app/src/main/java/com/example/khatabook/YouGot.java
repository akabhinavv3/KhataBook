package com.example.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class YouGot extends AppCompatActivity {

    Toolbar toolbar;
    TextView title;
    EditText amount,amountDetails;
    CardView cardView;
    Button btnSave,btnCalender;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_got);

        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        title=findViewById(R.id.textTitle);
        amount = findViewById(R.id.amount);
        amountDetails= findViewById(R.id.details);
        btnSave = findViewById(R.id.save);
        btnCalender = findViewById(R.id.calender);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YouGot.this,Customer.class);
                startActivity(intent);
                Log.e("tag","MyDatabasehelper");
                MyDatabaseHelper myDB = new MyDatabaseHelper(YouGot.this);
                myDB.addDetails(btnCalender.getText().toString().trim(),
                        Integer.parseInt(amount.getText().toString().trim()),
                        amountDetails.getText().toString().trim()
                );
                Log.e("tag","MyDatabasehelper");
            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String date= simpleDateFormat.format(Calendar.getInstance().getTime());
        //btnCalender.setText(date);

        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        YouGot.this
                        , android.R.style.Theme_DeviceDefault_Dialog_NoActionBar
                        ,dateSetListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                String date = day + "/" + month + "/" + year;
                btnCalender.setText(date);

            }
        };

        cardView=findViewById(R.id.amountcard);


        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                cardView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (amount.getText().toString().isEmpty()){
                    cardView.setVisibility(View.GONE);
                    btnCalender.setVisibility(View.GONE);
                    title.setText("You Gave Rs 0 to 1");
                }else {
                    cardView.setVisibility(View.VISIBLE);
                    btnCalender.setVisibility(View.VISIBLE);
                    title.setText("You Gave Rs " + amount.getText().toString() +" to 1");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            Intent intent = new Intent(YouGot.this, Customer.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}