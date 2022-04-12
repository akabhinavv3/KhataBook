package com.example.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.khatabook.Fragment.HomeFragment;

public class ViewReport extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        toolbar=findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            Intent intent = new Intent(ViewReport.this, HomeFragment.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}