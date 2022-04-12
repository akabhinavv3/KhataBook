package com.example.khatabook.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khatabook.Adapter.ContactAdapter;
import com.example.khatabook.ConatctModel;
import com.example.khatabook.R;

import java.util.ArrayList;

public class ContactItemFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ConatctModel> arrayList = new ArrayList<ConatctModel>();
    ContactAdapter adapter;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    Cursor phones;

    public ContactItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_item, container, false);

        recyclerView = view.findViewById(R.id.Rv);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<ConatctModel>();
        showContacts();

        return view;
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
            ContactItemFragment.LoadContact loadContact = new ContactItemFragment.LoadContact();
            loadContact.execute();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(getActivity(), "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LoadContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get Contact list from Phone

            if (phones != null) {
                Log.e("count", "" + phones.getCount());
                if (phones.getCount() == 0) {

                }

                while (phones.moveToNext()) {
                    Bitmap bit_thumb = null;
                    String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                    ConatctModel selectUser = new ConatctModel();
                    selectUser.setName(name);
                    selectUser.setNumber(phoneNumber);
                    arrayList.add(selectUser);


                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // sortContacts();
            int count=arrayList.size();
            ArrayList<ConatctModel> removed=new ArrayList<>();
            ArrayList<ConatctModel> contacts=new ArrayList<>();
            for(int i=0;i<arrayList.size();i++){
                ConatctModel inviteFriendsProjo = arrayList.get(i);

                if(inviteFriendsProjo.getName().matches("\\d+(?:\\.\\d+)?")||inviteFriendsProjo.getName().trim().length()==0){
                    removed.add(inviteFriendsProjo);

                }else{
                    contacts.add(inviteFriendsProjo);
                }
            }
            contacts.addAll(removed);
            arrayList=contacts;
            adapter = new ContactAdapter(getActivity(), arrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);

        }
    }
}