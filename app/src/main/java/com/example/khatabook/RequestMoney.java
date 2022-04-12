package com.example.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.khatabook.Adapter.RequestMoneyAdapter;
import com.example.khatabook.Fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

public class RequestMoney extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_money);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Customer"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final RequestMoneyAdapter requestMoneyAdapter = new RequestMoneyAdapter(this,getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(requestMoneyAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            Intent intent = new Intent(RequestMoney.this, HomeFragment.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}