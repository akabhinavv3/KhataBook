package com.example.khatabook.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khatabook.AddBank;
import com.example.khatabook.Contact;
import com.example.khatabook.R;

public class MoneyFragment extends Fragment {
    CardView addbankCardView;

    public MoneyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money, container, false);

        addbankCardView = view.findViewById(R.id.addbankCard);

        addbankCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddBank.class);
                startActivity(intent);
            }
        });

        return view;
    }
}