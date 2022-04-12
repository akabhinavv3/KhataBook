package com.example.khatabook.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.khatabook.Contact2Model;
import com.example.khatabook.Model;

import java.util.ArrayList;
import java.util.List;

public class dbmanager extends SQLiteOpenHelper {
    private static final String dbname="dbcontact";
    public dbmanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table tbl_contact( id integer primary key autoincrement, name text, contact text)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry = "DROP TABLE IF EXISTS tbl_contact";
        db.execSQL(qry);
        onCreate(db);
    }

    public String addrecord(String name,String contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("contact",contact);
        float res=db.insert("tbl_contact",null,cv);

        if (res==-1)
            return "failed";
        else
            return "Successfully insert";
    }

    /*public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_contact order by id desc";
        Cursor cursor = db.rawQuery(qry,null);
        return cursor;

    }*/

   public List<Contact2Model> readAllData(){

        String query = "SELECT * FROM " + "tbl_contact";
        SQLiteDatabase db  = this.getReadableDatabase();

/*
        Cursor cursor = null;
        if (db !=null){
            db.rawQuery(query,null);

        }
        return cursor;
*/



        List<Contact2Model> contactList = new ArrayList<>();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + "tbl_contact";
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Contact2Model contact = new Contact2Model();

                contact.setName((cursor.getString(1)));
                contact.setPhone(cursor.getString(2));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }
}
