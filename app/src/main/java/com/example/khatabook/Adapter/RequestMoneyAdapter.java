package com.example.khatabook.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.khatabook.Fragment.ContactItemFragment;
import com.example.khatabook.Fragment.CustomerItemFragment;
import com.example.khatabook.RequestMoney;

public class RequestMoneyAdapter extends FragmentPagerAdapter {

    int mNoOfTabs;

    public RequestMoneyAdapter(@NonNull FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }

    public RequestMoneyAdapter(RequestMoney requestMoney, FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
        this.mNoOfTabs = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {

            case 0:
                CustomerItemFragment customerItemFragment = new CustomerItemFragment();
                return customerItemFragment;
            case 1:
                ContactItemFragment contactItemFragment = new ContactItemFragment();
                return contactItemFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
